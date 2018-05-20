package com.neo.dao.sys.generator;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.neo.model.sys.generator.SysPermission;
import com.neo.model.sys.generator.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    List<SysPermission> selectPermByUser(UserInfo userInfo);
}
