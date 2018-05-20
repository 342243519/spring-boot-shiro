package com.neo.sevice.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.neo.dao.sys.generator.SysLogMapper;
import com.neo.model.sys.generator.SysLog;
import com.neo.sevice.SysLogService;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {
}
