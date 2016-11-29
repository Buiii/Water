package com.water.Api.controller;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.water.Api.entity.UserBean;

public class TestShiroConterller extends Controller {

 private static Logger log = Logger.getLogger(TestShiroConterller.class);

 /**
  * 测试上传
  */
 @ActionKey("login")
 public void login(UserBean userBean) {
  // 创建验证工厂类
  Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
  SecurityManager manager = factory.getInstance();
  SecurityUtils.setSecurityManager(manager);
  Subject subject = SecurityUtils.getSubject();
  UsernamePasswordToken token = new UsernamePasswordToken(userBean.getUid(), userBean.getPwd());
  try {
   // 4、登录，即身份验证
   subject.login(token);
  } catch (AuthenticationException e) {
   // 5、身份验证失败
   log.info(e.getMessage());
  }
 }
}
