package com.sacc.comprehensivesystem.admin.sys.entity;

import com.sacc.comprehensivesystem.admin.sys.entity.auto._SysMenu;
import com.sacc.comprehensivesystem.common.utils.JSONUtils;

public class SysMenu extends _SysMenu {
    @Override
    public String toString() {
        return JSONUtils.toJson(this);
    }
}
