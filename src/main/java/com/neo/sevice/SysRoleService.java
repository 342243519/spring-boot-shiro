package com.neo.sevice;



import com.baomidou.mybatisplus.service.IService;
import com.neo.model.sys.generator.SysRole;

import java.util.List;

public interface SysRoleService extends IService<SysRole> {

    /**
     * @param userId
     * @return
     */
    List<SysRole> selectRoleByUserId(Integer userId);
}
