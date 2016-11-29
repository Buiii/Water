package com.water.Api.plugin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.water.Api.Tools.Tools;
import com.water.Api.entity.ApiBean;


public class VerificationCodeApiPlugin {

 private static Logger log = Logger.getLogger(VerificationCodeApiPlugin.class);
 private String mess = "";

 /**
  * 通过GET方式发起http请求
  * 
  * @throws IOException
  * @throws ParseException
  */
 @Test
 public String requestByGetMethod(ApiBean apiBean) throws ParseException{
  // 创建默认的httpClient实例
  CloseableHttpClient httpClient = new MultiplatformPlugin()
   .start();
  HttpGet get = new HttpGet(apiBean.getGetUrl());
  CloseableHttpResponse httpResponse = null;
  try {
   // 用get方法发送http请求
   get.setHeader("Accept", "Accept text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
   get.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
   get.setHeader("Date", new Date().toString());
   get.setHeader("Accept-Encoding", "gzip, deflate");
   get.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
   get.setHeader("Connection", "keep-alive");
   get.setHeader("Cookie", " JSESSIONID=56F74B46B701AFC8A14AB5061FB9F465; Path=/");
   if (apiBean.isAgentBoolean()) {
    get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
   } else {
    get.setHeader("User-Agent",
     "Mozilla/5.0 (Linux; U; Android 2.2; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
   }

   if (apiBean.isProxyBoolean()) {
    log.info(("设置代理成功:" + apiBean.getRequestConfig().getLocalAddress() + ":" + apiBean.getRequestConfig().getProxy()));
    get.setConfig(apiBean.getRequestConfig());
   }
   log.info("执行get请求:...." + get.getURI());
   
   // 发送get请求
   httpResponse = httpClient.execute(get);
    // response实体
   HttpEntity  entity = httpResponse.getEntity();
   String mesg= EntityUtils.toString(entity);
    if (null != entity) {
     log.info(("响应状态码:" + httpResponse.getStatusLine()));
     log.info("-------------------------------------------------");
     log.info("响应内容:" + Tools.ReturnMessage(mesg));
     log.info("-------------------------------------------------");
     mess = Tools.ReturnMessage(mesg);
    }
  } catch (Exception e) {
   e.printStackTrace();
  } finally {
   try {
	httpResponse.close();
    closeHttpClient(httpClient);
   } catch (IOException e) {
    e.printStackTrace();
   }
  }
  return mess;
 }

 /**
  * POST方式发起http请求
  * 
  * @throws IOException
  * @throws ParseException
  */
 @Test
 public String requestByPostMethod(ApiBean apiBean) throws ParseException {
  CloseableHttpClient httpClient = new MultiplatformPlugin().start();
  try {
   HttpPost post = new HttpPost(apiBean.getGetUrl()); // 这里用上本机的某个工程做测试
   post.setHeader("Accept", "Accept text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
   post.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
   post.setHeader("Date", new Date().toString());
   post.setHeader("Accept-Encoding", "gzip, deflate");
   post.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
   post.setHeader("Connection", "keep-alive");
   post.setHeader("Cookie", " JSESSIONID=56F74B46B701AFC8A14AB5061FB9F465; Path=/");
   if (apiBean.isAgentBoolean()) {
    post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
   } else {
    post.setHeader("User-Agent",
     "Mozilla/5.0 (Linux; U; Android 2.2; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
   }
   if (apiBean.isProxyBoolean()) {
    post.setConfig(apiBean.getRequestConfig());
   }
   // 创建参数列表
   List<NameValuePair> list = apiBean.getParameter();
   // url格式编码
   UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list, "UTF-8");
   post.setEntity(uefEntity);
   System.out.println("POST 请求...." + post.getURI());
   // 执行请求
   CloseableHttpResponse httpResponse = httpClient.execute(post);
   try {
	HttpEntity  entity = httpResponse.getEntity();
	String mesg= EntityUtils.toString(entity);
    if (null != entity) {
     log.info("-------------------------------------------------------");
     log.info(Tools.ReturnMessage(mesg));
     log.info("-------------------------------------------------------");
     mess = Tools.ReturnMessage(mesg);
    }
   } finally {
    httpResponse.close();
   }
  } catch (UnsupportedEncodingException e) {
   e.printStackTrace();
  } catch (IOException e) {
   e.printStackTrace();
  } finally {
   try {
    closeHttpClient(httpClient);
   } catch (Exception e) {
    e.printStackTrace();
   }
  }
  return mess;
 }


 private void closeHttpClient(CloseableHttpClient client) throws IOException {
  if (client != null) {
   client.close();
  }
 }
} 
