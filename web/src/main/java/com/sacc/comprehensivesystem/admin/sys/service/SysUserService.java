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
     *
     * @param loginName
     * @return
     */
    public SysUser findByLoginName(String loginName){
        return sysUserDao.findByLoginName(loginName);
    }
    /**
     * 用户登录
     * <ul>
     *     <li>1. 根据用户名获取用户信息</li>
     *     <li>2. 验证密码</li>
     *     <li>3. 获取role</li>
     *     <li>4. 获取menu</li>
     *     <li>5. 获取permission</li>
     *     <li>6. update用户信息</li>
     * </ul>
     * @param username
     * @param password
     * @return
     */
    @Transactional(readOnly = false)
    public SysUser login(String username, String password) {
        SysUser user = sysUserDao.findByLoginName(username);

        //System.out.println(PasswordUtils.generateHash(password));
        if(user != null && PasswordUtils.checkHash(password,user.getPassword())) {
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
        return  sysUserDao.findPermissionByUserId(id);
    }

    public List<SysUser> findUserNotAdmin(SysUser sysUser) {
        return  sysUserDao.findUserNotAdmin(sysUser);
    }

    public int signUp(SysUser sysUser){
        int result=0;

        String name=sysUser.getLoginName();
        String email= sysUser.getEmail();
        SysUser previousSysUser = sysUserDao.findByLoginName(name);
        SysUser PrevirousSysUser = sysUserDao.findByEmail(email);

        if(previousSysUser!=null&&PrevirousSysUser!=null) {
            result=3;
        } else {
            try {
                insert(sysUser);
                System.out.println(1/0);
                result = 1;
                SysUserRole sysUserRole = sysUserRoleService.userRoleService(sysUser);
                Long id = sysUserDao.findIdByLoginName(sysUser.getLoginName());
                sysUserRole.setUserId(id);
                sysUserRole.setCreateBy(id);
                sysUserRole.setUpdateBy(id);
                sysUserRoleDao.insert_role(sysUserRole);
            } catch (Exception e) {
                logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
                result = 2;
                throw new RuntimeException();
            }
        }

        return result;
    }

    public Long CheckBase64(String base64)
    {
        Long id= Long.valueOf(Base64.Base64UnDecoder(base64));
        System.out.println(id);
        return id;
    }
    public int CheckMd5(Long id,String md5)
    {
        SysUser sysUser = sysUserDao.get(id);

        String mD5=MD5Utils.MD5Encode(String.valueOf(sysUser.getCreateDate()),"utf8");

        System.out.println(mD5);
        System.out.println(md5);
        if(mD5.equals(md5))
        {

            SysUserRole sysUserRole = sysUserRoleService.userRoleService(sysUser);
            sysUserRoleDao.update_role(sysUserRole);
            return 1;
        }else {
            return 0;
        }
    }
}
