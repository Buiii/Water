package com.water.Api.entity;

import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.log4j.Logger;

public class ApiBean {

 private static Logger log = Logger.getLogger(ApiBean.class);

 private String Method; // 方法名
 private String Message; // 消息
 private String PostUrl; // 请求地址
 private String GetUrl;// 请求地址
 private List<NameValuePair> Parameter; // 请求参数
 private String proxyHost;
 private String proxyPort;
 private boolean proxyBoolean;
 private boolean AgentBoolean;
 private RequestConfig requestConfig;


 public String getMethod() {
  return Method;
 }

 public void setMethod(String method) {
  Method = method;
 }

 public String getMessage() {
  return Message;
 }

 public void setMessage(String message) {
  Message = message;
 }


 public String getPostUrl() {
  return PostUrl;
 }

 public void setPostUrl(String postUrl) {
  PostUrl = postUrl;
 }

 public String getGetUrl() {
  return GetUrl;
 }

 public void setGetUrl(String getUrl) {
  GetUrl = getUrl;
 }

 public List<NameValuePair> getParameter() {
  return Parameter;
 }

 public void setParameter(List<NameValuePair> parameter) {
  Parameter = parameter;
 }

 public String getProxyHost() {
  return proxyHost;
 }

 public void setProxyHost(String proxyHost) {
  this.proxyHost = proxyHost;
 }

 public String getProxyPort() {
  return proxyPort;
 }

 public void setProxyPort(String proxyPort) {
  this.proxyPort = proxyPort;
 }

 public boolean isProxyBoolean() {
  return proxyBoolean;
 }

 public void setProxyBoolean(boolean proxyBoolean) {
  this.proxyBoolean = proxyBoolean;
 }

 public boolean isAgentBoolean() {
  return AgentBoolean;
 }

 public void setAgentBoolean(boolean agentBoolean) {
  AgentBoolean = agentBoolean;
 }

 public RequestConfig getRequestConfig() {
  return requestConfig;
 }

 public void setRequestConfig(RequestConfig requestConfig) {
  this.requestConfig = requestConfig;
 }

 public ApiBean(String Method, String PostUrl, List<NameValuePair> Parameter, String proxyHost, String proxyPort, boolean proxyBoolean,
  boolean AgentBoolean) {
  this.Method = Method;
  this.PostUrl = PostUrl;
  this.Parameter = Parameter;
  this.proxyHost = proxyHost;
  this.proxyPort = proxyPort;
  this.proxyBoolean = proxyBoolean;
  this.AgentBoolean = AgentBoolean;
  // System.setProperty("http.proxyHost", proxyHost); // 设置代理ip
  // System.setProperty("http.proxyPort", proxyPort); // 设置代理ip端口
  HttpHost proxy = new HttpHost(proxyHost, Integer.parseInt(proxyPort));
  RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
  this.requestConfig = config;
  log.info(("设置代理成功:" + proxyHost + ":" + proxyPort));
 }

 public ApiBean(String GetUrl, String proxyHost, String proxyPort, boolean proxyBoolean, boolean AgentBoolean) {
  this.GetUrl = GetUrl;
  this.proxyHost = proxyHost;
  this.proxyPort = proxyPort;
  this.proxyBoolean = proxyBoolean;
  this.AgentBoolean = AgentBoolean;
  // System.setProperty("http.proxyHost", proxyHost); // 设置代理ip
  // System.setProperty("http.proxyPort", proxyPort); // 设置代理ip端口
  if (proxyHost.equals("80") && proxyHost.equals("8080")) {
   log.info(("设置代理失败:" + proxyHost + ":" + proxyPort));
   return;
  }
  HttpHost proxy = new HttpHost(proxyHost, Integer.parseInt(proxyPort));
  RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
  this.requestConfig = config;
  log.info(("设置代理成功:" + proxyHost + ":" + proxyPort));
 }
}
