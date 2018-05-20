/**
 * Acestek.com.cn Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.neo.core.annotation;


import com.neo.model.sys.generator.SysLog;
import com.neo.model.sys.generator.UserInfo;
import com.neo.sevice.SysLogService;
import com.neo.util.LogUtil;
import com.neo.util.WebUtil;
import org.apache.log4j.Logger;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.security.auth.login.LoginException;
import java.lang.reflect.Method;
import java.text.MessageFormat;

/**
 * 总体说明
 *
 * <p>具体说明</p>
 *
 * @author ddxu
 * @version
 */
@Aspect
@Component
public class sysLogAspect  {

	private final static Logger logger = Logger.getLogger(sysLogAspect.class);
	@Autowired
	private SysLogService sysLogService;


	//Controller层切点
	@Pointcut("@annotation(com.neo.core.annotation.SystemControllerLog)")
	public void controllerAspect() {
	}

	//Service层切点
	@Pointcut("@annotation(SystemServiceLog)")
	public void serviceAspect() {
	}

	/**
	* 前置通知 用于拦截Controller层记录用户的操作
	*
	* @param joinPoint 切点
	*/
	@After("controllerAspect()")
	public void controllerAfter(JoinPoint joinPoint) {
		try {
			logger.info("===========Controller After日志开始=============");
			SysLog log = getControllerMethodDescription(joinPoint);
			//保存数据库
			sysLogService.insert(log);
		} catch (Exception e) {
			//记录本地异常日志
			logger.error("=================Controller After日志记录异常：" + e.getMessage(),e);
		}
	}

	/**
	* 异常通知 用于拦截controller层记录异常日志
	*
	* @param joinPoint
	* @param e
	*/
	@AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
	public void controllerAfterThrowing(JoinPoint joinPoint, Throwable e) {
		try {
			logger.info("===========Controller AfterThrowing日志开始=============");
			SysLog log = getControllerMethodDescription(joinPoint);
			if(!e.getClass().isInstance(new LoginException())) {
				String exCode = "  异常类型:" + e.getClass().getName();
				String msg = log.getLogMsg();
				log.setLogMsg(msg + exCode);
				//保存数据库
				sysLogService.insert(log);
			}
		} catch (Exception ex) {
			//记录本地异常日志
			logger.error("=======Controller AfterThrowing日志记录异常===========");
			logger.error(ex.getMessage());
		}
	}

	/**
	 * 获取当前登录用户ID
	 * @return
	 */
	public static Integer getUserId() {
		Integer userId=-1;
		try{
//			userId= WebUtil.getCurrentUserSession().getId();
			Subject subject =  SecurityUtils.getSubject();
			if(subject != null ){
				UserInfo userInfo = (UserInfo) subject;
				userId = userInfo.getUid();
			}
		}catch (Exception ex){
			logger.error("用户ID获取失败",ex);
		}
		return userId;
	}
	/**
	* 获取注解中对方法的描述信息 用于Controller层注解
	*
	* @param joinPoint 切点
	* @return 方法描述
	* @throws Exception
	*/
	public static SysLog getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(SystemControllerLog.class).description();
					break;
				}
			}
		}
		SysLog log = new SysLog();
		log.setBelongClass(targetName);
		log.setMethod(methodName);
		log.setLogType(getType(methodName));
		log.setUserId(getUserId());
		log.setLogMsg(MessageFormat.format(description, getParamToStr()));
		return log;
	}

	@After("serviceAspect()")
	public void serviceAfter(JoinPoint joinPoint) {
		try {
			logger.info("===========Service After日志开始=============");
			SysLog log = getServiceMthodDescription(joinPoint);
			//保存数据库
			sysLogService.insert(log);
		} catch (Exception e) {
			//记录本地异常日志
			logger.info("=================Service After日志记录异常：" + e.getMessage());
		}
	}

	/**
	* 异常通知 用于拦截service层记录异常日志
	*
	* @param joinPoint
	* @param e
	*/
	@AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
	public void serviceAfterThrowing(JoinPoint joinPoint, Throwable e) {
		//获取用户请求方法的参数并序列化为JSON格式字符串
		try {
			logger.info("===========Service AfterThrowing日志开始=============");
			SysLog log = getServiceMthodDescription(joinPoint);
			String exCode = "  异常类型:" + e.getClass().getName();
			String msg = log.getLogMsg();
			log.setLogMsg(msg + exCode);
			//保存数据库
			sysLogService.insert(log);
		} catch (Exception ex) {
			//记录本地异常日志
			logger.error("=======Service AfterThrowing日志记录异常===========");
			logger.error(ex.getMessage());
		}
	}

	/**
	 * 获取注解中对方法的描述信息 用于service层注解
	 *
	 * @param joinPoint 切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public static SysLog getServiceMthodDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(SystemServiceLog.class).description();
					break;
				}
			}
		}
		SysLog log = new SysLog();
		log.setBelongClass(targetName);
		log.setMethod(methodName);
		log.setLogType(getType(methodName));
		log.setUserId(getUserId());
//		log.setLogMsg(MessageFormat.format(description, getParamToStr()));
		return log;
	}

	//处理数组，将数组拼接成字符串
	public static Object[] getParamToStr() {
		Object[] obj = LogUtil.getParam();
		String temp = "";
		for (int i = 0; i < obj.length; i++) {
			if (obj[i].toString().contains("[Ljava.lang.Long")) {
				Long[] lo = (Long[]) obj[i];
				for (Long l : lo) {
					temp += l + ",";
				}
				obj[i] = temp;
			}
		}
		return obj;
	}

	public static String getType(String method) {
		String type = "";

		if (method.startsWith("doLogin")) {
			type = "0";
		} else if (method.startsWith("add") || method.startsWith("insert")) {
			type = "1";
		} else if (method.startsWith("edit") || method.startsWith("update")) {
			type = "2";
		} else if (method.startsWith("del") || method.startsWith("delete")) {
			type = "3";
		} else if (method.startsWith("get") || method.startsWith("select")
				|| method.startsWith("query")) {
			type = "4";
		}
		return type;
	}
}
