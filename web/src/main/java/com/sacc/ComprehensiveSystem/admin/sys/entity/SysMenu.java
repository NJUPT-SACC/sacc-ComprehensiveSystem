package com.sacc.ComprehensiveSystem.admin.sys.entity;

import com.sacc.ComprehensiveSystem.admin.sys.entity.auto._SysMenu;
import com.sacc.ComprehensiveSystem.common.utils.JSONUtils;

public class SysMenu extends _SysMenu {
    @Override
    public String toString() {
        return JSONUtils.toJson(this);
    }
}
