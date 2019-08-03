package com.sacc.comprehensivesystem.admin.shrio.tools;

import com.sacc.comprehensivesystem.common.utils.JSONUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SACToken implements AuthenticationToken {
    static Logger logger = LoggerFactory.getLogger(SACToken.class);

    // 密钥
    private String authKey;

    public SACToken(String authKey) {
        this.authKey = authKey;
    }

    @Override
    public Object getPrincipal() {
        return authKey;
    }

    @Override
    public Object getCredentials() {
        return authKey;
    }

    @Override
    public String toString() {
        return JSONUtils.toJson(this);
    }
}
