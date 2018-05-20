package com.neo.dao.sys.generator;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.neo.model.sys.generator.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {


    List<SysRole> selectRoleByUserId(Integer userId);
}
