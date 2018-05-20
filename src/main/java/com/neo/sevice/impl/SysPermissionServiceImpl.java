package com.neo.sevice.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.neo.dao.sys.generator.SysPermissionMapper;
import com.neo.model.sys.generator.SysPermission;
import com.neo.model.sys.generator.UserInfo;
import com.neo.sevice.SysPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {
    @Override
    public List<SysPermission> selectPermByUser(UserInfo userInfo) {
        return baseMapper.selectPermByUser(userInfo);
    }
}
