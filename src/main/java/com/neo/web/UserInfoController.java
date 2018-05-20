package com.neo.web;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    /**
     * 用户查询.
     * @return
     */
    @RequestMapping("/userList")
    @RequiresPermissions("userInfo:view")
    @ResponseBody
    public String userInfo(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", "ddd");
        jsonObject.put("msg", "登录成功");
        return jsonObject.toString();
    }

    /**
     * 用户添加;
     * @return
     */
    @RequestMapping("/userAdd")
    @ResponseBody
//    @RequiresPermissions("userInfo:add")//权限管理;
    public String userInfoAdd(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", "ddd");
        jsonObject.put("msg", "登录成功");
        return jsonObject.toString();
    }

    /**
     * 用户删除;
     * @return
     */
    @RequestMapping("/userDel")
    @RequiresPermissions("userInfo:del")//权限管理;
    public String userDel(){
        return "userInfoDel";
    }
}