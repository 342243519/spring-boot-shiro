package com.neo.sevice.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.neo.dao.sys.generator.SysRoleMapper;
import com.neo.model.sys.generator.SysRole;
import com.neo.sevice.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> selectRoleByUserId(Integer userId) {
        return baseMapper.selectRoleByUserId(userId);
    }
}
