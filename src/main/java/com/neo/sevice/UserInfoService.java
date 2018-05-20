package com.neo.sevice;


import com.baomidou.mybatisplus.service.IService;
import com.neo.model.sys.generator.UserInfo;

public interface UserInfoService extends IService<UserInfo> {


    /**
     * @param username
     * @return
     */
    UserInfo findByUsername(String username);
}