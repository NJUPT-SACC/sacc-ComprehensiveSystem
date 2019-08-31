package com.sacc.comprehensivesystem.admin.sys.service;

import com.sacc.comprehensivesystem.admin.sys.dao.SysUserDao;
import com.sacc.comprehensivesystem.admin.sys.dao.SysUserRoleDao;
import com.sacc.comprehensivesystem.admin.sys.entity.SysMenu;
import com.sacc.comprehensivesystem.admin.sys.entity.SysUser;
import com.sacc.comprehensivesystem.admin.sys.entity.SysUserRole;
import com.sacc.comprehensivesystem.common.service.BasicService;
import com.sacc.comprehensivesystem.common.utils.Base64;
import com.sacc.comprehensivesystem.common.utils.MD5Utils;
import com.sacc.comprehensivesystem.common.utils.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysUserService extends BasicService<SysUser> {

    private static Logger logger = LoggerFactory.getLogger(SysUserService.class);

    @Autowired
    SysUserDao sysUserDao;

    @Autowired
    SysUserRoleDao sysUserRoleDao;


    @Autowired
    SysUserRoleService sysUserRoleService;

    // TODO
    /**
     * 用于表名业务主键，用于确认是否重复，如果不需要检查重复项，则无需修改
     * 注意：需要是_SysUser的属性名，而不是字段名
     */
    private String[] natureKeys = {};

    @Override
    public String[] getNatureKeys() {
        return natureKeys;
    }

    /**
     * @param loginName
     * @return
     */
    public List<SysUser> findByLoginName(String loginName, String email) {
        return sysUserDao.findByLoginName(loginName, email);
    }

    public SysUser findUserByLoginName(String loginName) {
        return sysUserDao.findUserByLoginName(loginName);
    }

    /**
     * 用户登录
     * <ul>
     * <li>1. 根据用户名获取用户信息</li>
     * <li>2. 验证密码</li>
     * <li>3. 获取role</li>
     * <li>4. 获取menu</li>
     * <li>5. 获取permission</li>
     * <li>6. update用户信息</li>
     * </ul>
     *
     * @param username
     * @param password
     * @return
     */
    @Transactional(readOnly = false)
    public SysUser login(String username, String password) {
        SysUser user = sysUserDao.findUserByLoginName(username);

        //System.out.println(PasswordUtils.generateHash(password));
        if (user != null && PasswordUtils.checkHash(password, user.getPassword())) {
            Date d = new Date();
            user.setUpdateDate(d);
            user.setUpdateBy(user.getId());
            save(user);
            return user;
        }
        return null;
    }

    @Override
    public void customInit(SysUser entity) {
        //TODO
    }

    public List<SysMenu> findMenuByUserId(Long id) {
        return sysUserDao.findMenuByUserId(id);
    }

    public List<String> findRoleByUserId(Long id) {
        return sysUserDao.findRoleByUserId(id);
    }

    public List<String> findPermissionByUserId(Long id) {
        return sysUserDao.findPermissionByUserId(id);
    }

    public List<SysUser> findUserNotAdmin(SysUser sysUser) {
        return sysUserDao.findUserNotAdmin(sysUser);
    }

    public int signUp(SysUser sysUser) {
        int result = 2;

        String name = sysUser.getLoginName();
        String email = sysUser.getEmail();

        // sysUser.setPassword(PasswordUtils.generateHash(sysUser.getPassword()));
        List<SysUser> list = sysUserDao.findByLoginName(name, email);
        if (list.size() == 2) {
            result = 3;
        } else if (list.size() == 1) {
            SysUser user = list.get(0);
            if (name.equals(user.getLoginName()) && email.equals(user.getEmail())) {
                result = 3;
            } else if (name.equals(user.getLoginName())) {
                result = 4;
            } else if (email.equals(user.getEmail())) {
                result = 5;
            }
        } else try {
            insert(sysUser);
            result = 1;
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(4L);
            Long id = sysUserDao.findIdByLoginName(sysUser.getLoginName());
            sysUserRole.setUserId(id);
            sysUserRole.setCreateBy(id);
            sysUserRole.setUpdateBy(id);
            sysUserRoleDao.insertRole(sysUserRole);
        } catch (Exception e) {
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
            result = 2;
            throw new RuntimeException();
        }


        System.out.println(result + "aaaaaaaaaaaaaaaaaaa");
        return result;
    }

    public Long checkBase64(String base64) {
        Long id = Long.valueOf(Base64.Base64UnDecoder(base64));

        return id;
    }

    public int checkMd5(Long id, String md5) {
        SysUser sysUser = sysUserDao.get(id);

        String mD5 = MD5Utils.MD5Encode(String.valueOf(sysUser.getCreateDate()), "utf8");

        if (mD5.equals(md5)) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(4L);
            sysUserRole.setUserId(sysUser.getId());
            sysUserRoleDao.updateRole(sysUserRole);
            return 1;
        } else {
            return 0;
        }
    }
}
