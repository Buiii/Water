package com.water.Api.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;

public class TestConteroller extends Controller {
 /**
  * 测试上传
  */
 @ActionKey("test")
 public void test() {
  UploadFile files = getFile("file");
  // try {
  // FileInputStream inputStream = new FileInputStream(file);
  // String encoding = "UTF-8";
  // InputStreamReader read = new InputStreamReader(inputStream, encoding);
  // OutputStream bos = new FileOutputStream(Savefilepath);
  // BufferedReader bufferedReader = new BufferedReader(read);
  // String lineTxt = null;
  // while ((lineTxt = bufferedReader.readLine()) != null && !"".equals((lineTxt
  // = bufferedReader.readLine()))) {
  //
  // }
  // } catch (IOException e) {
  // // TODO Auto-generated catch block
  // e.printStackTrace();
  // }
  return;
 }
}
