package com.sacc.comprehensivesystem.admin.shrio.service;

import com.sacc.comprehensivesystem.admin.Utils.CacheUtils;
import com.sacc.comprehensivesystem.admin.shrio.entity.UserSimpleAuthorizationInfo;
import com.sacc.comprehensivesystem.admin.shrio.tools.SACToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SACRealm extends AuthorizingRealm {
    static Logger logger = LoggerFactory.getLogger(SACRealm.class);


    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof SACToken;
    }

    /**
     * 验证用户的权限
     * @param principalCollection shiro传入的principal，实际为authKey
     * @return 用户的角色和权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.debug("doGetAuthorizationInfo: {}", principalCollection);
        String authKey = principalCollection.toString();
        //UserSimpleAuthorizationInfo userSimpleAuthorizationInfo = cacheService.getUserByAuthKey(authKey);
        UserSimpleAuthorizationInfo userSimpleAuthorizationInfo =
                (UserSimpleAuthorizationInfo) CacheUtils.getUserCache(authKey);
        return userSimpleAuthorizationInfo;
    }

    /**
     * 验证用户是否已经登陆，主要检验Header中的authKey在cache中是否存在
     * @param authenticationToken 要验证的token
     * @return 验证的结果
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.debug("doGetAuthenticationInfo: {}", authenticationToken);
        String authKey = (String)authenticationToken.getCredentials();

        if (CacheUtils.getUserCache(authKey) == null/*cacheService.getUserByAuthKey(authKey) == null*/){ //未登录
            throw new AuthenticationException("token invalid");
        }
        return new SimpleAuthenticationInfo(authKey, authKey, getName());
    }


}
