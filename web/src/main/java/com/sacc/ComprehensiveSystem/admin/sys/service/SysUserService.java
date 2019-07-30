package com.sacc.ComprehensiveSystem.admin.sys.service;

import com.sacc.ComprehensiveSystem.admin.sys.dao.SysUserDao;
import com.sacc.ComprehensiveSystem.admin.sys.entity.SysMenu;
import com.sacc.ComprehensiveSystem.admin.sys.entity.SysUser;
import com.sacc.ComprehensiveSystem.common.service.BasicService;
import com.sacc.ComprehensiveSystem.common.utils.PasswordUtils;
import com.sacc.ComprehensiveSystem.common.utils.RestResult;
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
    public String findByUserName(String name){
        return sysUserDao.findByUserName(name);
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

    public List<SysMenu> findMenuByUserId(Integer id) {
        return sysUserDao.findMenuByUserId(id);
    }

    public List<String> findRoleByUserId(Integer id) {
        return sysUserDao.findRoleByUserId(id);
    }

    public List<String> findPermissionByUserId(Integer id) {
        return  sysUserDao.findPermissionByUserId(id);
    }

    public List<SysUser> findUserNotAdmin(SysUser sysUser) {
        return  sysUserDao.findUserNotAdmin(sysUser);
    }

    public int signUp(SysUser sysUser){
        int result=0;

        String name=sysUser.getLoginName();
        SysUser previousSysUser = sysUserDao.findByLoginName(name);

        if(previousSysUser!=null) {
            result=3;
        } else {
            try {
                insert(sysUser);
                result = 1;
            } catch (Exception e) {
                logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
                result = 2;
            }
        }
        return result;
    }

}
