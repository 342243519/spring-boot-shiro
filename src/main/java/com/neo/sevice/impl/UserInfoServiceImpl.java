package com.neo.sevice.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.neo.dao.sys.generator.UserInfoMapper;
import com.neo.model.sys.generator.UserInfo;
import com.neo.sevice.UserInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {


    @Override
    public UserInfo findByUsername(String username) {
        return baseMapper.findByUsername(username);
    }
}