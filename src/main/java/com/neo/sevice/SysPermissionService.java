package com.neo.sevice;


import com.baomidou.mybatisplus.service.IService;
import com.neo.model.sys.generator.SysPermission;
import com.neo.model.sys.generator.UserInfo;

import java.util.List;

public interface SysPermissionService extends IService<SysPermission> {

    List<SysPermission> selectPermByUser(UserInfo userInfo);
}
