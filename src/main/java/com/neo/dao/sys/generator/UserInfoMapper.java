package com.neo.dao.sys.generator;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.neo.model.sys.generator.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {


    /**
     * @param username
     * @return
     */
    UserInfo findByUsername(@Param("username") String username);
}

