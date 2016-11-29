package com.water.Api.service.Impl;

import com.water.Api.entity.ApiBean;
import com.water.Api.entity.YzmBean;
import com.water.Api.service.YzmService;

public class ShjmptYzmServiceImpl implements YzmService<YzmBean> {

 public YzmBean YzmLogin(YzmBean t) {
  // TODO Auto-generated method stub
  t.setToken(codeApiPlugin
   .requestByGetMethod(
    new ApiBean("http://api.shjmpt.com:9002/pubApi/uLogin?uName=" + t.getUid() + "&pWord=" + t.getPwd(), "", "", false, false))
   .split("|")[0]);
  return t;
 }

 public YzmBean YzmGetMobilenum(YzmBean t) {
  t.setMobiles(codeApiPlugin.requestByGetMethod(
   new ApiBean("http://api.shjmpt.com:9002/pubApi/GetPhone?ItemId=" + t.getPid() + "&token=" + t.getToken(), "", "", false, false))
   .split(";"));
  return t;
 }

 public YzmBean YzmGetVcodeAndReleaseMobile(YzmBean t) {
  t.setCode(codeApiPlugin.requestByGetMethod(
   new ApiBean("http://api.shjmpt.com:9002/pubApi/GMessage?token=" + t.getToken() + "&ItemId=" + t.getPid() + "&Phone=" + t.getMobiles()[0],
    "", "", false, false))
   .split("&")[3]);
  return t;
 }

 public YzmBean YzmGetVcodeAndHoldMobilenum(YzmBean t) {
  // TODO Auto-generated method stub
  return null;
 }

 public YzmBean YzmAddIgnoreList(YzmBean t) {
  // TODO Auto-generated method stub
  return null;
 }

 public YzmBean YzmGetUserInfos(YzmBean t) {
  // TODO Auto-generated method stub
  return null;
 }

 public YzmBean YzmGetRecvingInfo(YzmBean t) {
  // TODO Auto-generated method stub
  return null;
 }

}
