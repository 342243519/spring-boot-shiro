package com.neo.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.neo.common.core.Resources;
import com.neo.core.annotation.SystemControllerLog;
import com.neo.util.LogUtil;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


@Controller
public class LoginController {

    Logger logger = Logger.getLogger(LoginController.class);

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest req, Model model) {
        return "login";
    }

    /**
     * 登录方法
     * @param
     * @return
     */
    @RequestMapping(value = "/doLogin")
    @SystemControllerLog(description = "用户登录,登录用户账号为:{0},{1}")
    @ResponseBody
    public Object doLogin(@RequestParam(value = "username", required = false) String username,
                          @RequestParam(value = "password", required = false) String password, HttpServletRequest request, HttpServletResponse response){
        logger.info("用户登陆");
        logger.debug("用户登陆");
        logger.warn("用户登陆");
        logger.error("用户登陆");
        logger.fatal("用户登陆");
        JSONObject jsonObject = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
//            LogUtil.putParam(username, Resources.getMessage("LOGIN_SUCCESS"));
            jsonObject.put("token", subject.getSession().getId());
            jsonObject.put("msg", "登录成功");
        } catch (IncorrectCredentialsException e) {
            jsonObject.put("msg", "密码错误");
        } catch (LockedAccountException e) {
            jsonObject.put("msg", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            jsonObject.put("msg", "该用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * @return
     */
    @RequestMapping(value = "/unauth")
    @ResponseBody
    public Object unauth() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "1000000");
        map.put("msg", "未登录");
        return map;
    }

}
