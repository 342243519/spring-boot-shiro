package com.neo.dao.sys.generator;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.neo.model.sys.generator.SysLog;

public interface SysLogMapper extends BaseMapper<SysLog> {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);
}