package com.water.config;


import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.render.ViewType;
import com.water.Api.controller.TestConteroller;

public class WaterFilter extends JFinalConfig {

 /**
  * 配置常量
  */
 @Override
 public void configConstant(Constants me) {
  loadPropertyFile("config.properties");
  // TODO Auto-generated method stub
  me.setDevMode(true); // 设置是否是开发模式
  me.setViewType(ViewType.JSP); // 设置试图
 }

 /**
  * 配置处理器
  */
 @Override
 public void configHandler(Handlers ha) {
  // 前期可不配。后期完善
 }

 /**
  * 配置拦截器
  */
 @Override
 public void configInterceptor(Interceptors arg0) {
  // 前期可不配。后期完善

 }

 /**
  * 配置插件
  */
 @Override
 public void configPlugin(Plugins me) {
  // 简单配置redis
  RedisPlugin redisplugin = new RedisPlugin(this.getProperty("redisName"), this.getProperty("redisHost"));
  me.add(redisplugin);
  C3p0Plugin c3p0Plugin = new C3p0Plugin(this.getProperty("jdbcurl"), this.getProperty("user"), this.getProperty("password"),
   this.getProperty("driver"));
  me.add(c3p0Plugin);
  ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(c3p0Plugin);
 }

 /**
  * 配置路由
  */
 @Override
 public void configRoute(Routes me) {
  // TODO Auto-generated method stub
  // 采用扫描方式注入
  me.add("/test", TestConteroller.class);

 }

}
