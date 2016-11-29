package com.water.Api.service.Impl;


import org.apache.log4j.Logger;
import org.codehaus.plexus.util.StringUtils;

import com.dz.API;
import com.dz.AddIgnoreListResp;
import com.dz.GetMultiMobilenumResp;
import com.dz.GetVcodeAndHoldMobilenumResp;
import com.dz.GetVcodeAndReleaseMobileResp;
import com.water.Api.Tools.Tools;
import com.water.Api.entity.YzmBean;
import com.water.Api.service.YzmService;

/**
 * 
 * @author gaoj
 * @author 实现验证码接码平台1
 *
 */
public class DzapiYzmServiceImpl implements YzmService<YzmBean> {

 private static Logger log = Logger.getLogger(DzapiYzmServiceImpl.class);

 public YzmBean YzmLogin(YzmBean t) {
  // TODO Auto-generated method stub
  t.setToken(API.getInstance().loginIn(t.getUid(), t.getPwd()).getToken());
  return t;
 }

 public YzmBean YzmGetMobilenum(YzmBean t) {
  // TODO Auto-generated method stub
	 GetMultiMobilenumResp getMultiMobilenumResp= API.getInstance().getMobilenum(t.getPid(), t.getUid(), t.getToken(), t.getSize(), t.getProvince(), t.getOperator(), t.getNotProvince());
  t.setMobiles(getMultiMobilenumResp.getMobile());
  log.info(Tools.ReturnMessage(getMultiMobilenumResp.getResult()));
  return t;
 }

 public YzmBean YzmGetVcodeAndReleaseMobile(YzmBean t) {
  // TODO Auto-generated method stub
  String[] mobiles = new String[t.getMobiles().length];
  String[] Codes = new String[t.getMobiles().length];
//  if (t.getMobiles().length > 1) {
   for (int i = 0; i < t.getMobiles().length; i++) {
	   GetVcodeAndReleaseMobileResp andReleaseMobileResp= API.getInstance().getVcodeAndReleaseMobile(t.getMobiles()[i], t.getUid(), t.getToken(), t.getAuthor_uid());
    mobiles[i] = andReleaseMobileResp.getMobile();
    Codes[i] = andReleaseMobileResp.getVerifyCode();
    log.info(Tools.ReturnMessage(andReleaseMobileResp.getResult()));
   }
//  } else {
//   t.setMobile(API.getInstance().getVcodeAndReleaseMobile(t.getMobiles()[0], t.getUid(), t.getToken(), t.getAuthor_uid()).getMobile());
//   t.setCode(API.getInstance().getVcodeAndReleaseMobile(t.getMobiles()[0], t.getUid(), t.getToken(), t.getAuthor_uid()).getVerifyCode());
//  }
  t.setMobiles(mobiles);
  t.setCodes(Codes);
  return t;
 }

 public YzmBean YzmGetVcodeAndHoldMobilenum(YzmBean t) {
  String[] Codes = new String[t.getMobiles().length];
//  if (t.getMobiles().length > 1) {
   for (int i = 0; i < t.getMobiles().length; i++) {
	GetVcodeAndHoldMobilenumResp  andHoldMobilenumResp= API.getInstance().getVcodeAndHoldMobilenum(t.getMobiles()[i], t.getUid(), t.getToken(), t.getNext_pid(), t.getAuthor_uid());
    Codes[i] = andHoldMobilenumResp.getVerifyCode();
    log.info(Tools.ReturnMessage(andHoldMobilenumResp.getResult()));
   }
   t.setCodes(Codes);
//  } else {
//   GetVcodeAndHoldMobilenumResp  andHoldMobilenumResp= API.getInstance().getVcodeAndHoldMobilenum(t.getMobiles()[0], t.getUid(), t.getToken(), t.getNext_pid(), t.getAuthor_uid());
//   t.setMobile(andHoldMobilenumResp.getMobile());
//   t.setCode(andHoldMobilenumResp.getVerifyCode());
//   log.info(Tools.ReturnMessage(andHoldMobilenumResp.getResult()));
//  }
  return t;
 }

 public YzmBean YzmAddIgnoreList(YzmBean t) {
  // TODO Auto-generated method stub
	 AddIgnoreListResp addIgnoreListResp=  API.getInstance().addIgnoreList(t.getPid(), StringUtils.join(t.getMobiles(), ","), t.getUid(), t.getToken());
	 t.setSize(addIgnoreListResp.getRow());
	 log.info(addIgnoreListResp.getResult());
  return t;
 }

 public YzmBean YzmGetUserInfos(YzmBean t) {
  // TODO Auto-generated method stub
  t.setMessge(API.getInstance().getUserInfos(t.getUid(), t.getToken()).getResult());
  return t;
 }

 public YzmBean YzmGetRecvingInfo(YzmBean t) {

  return t;
 }

}
