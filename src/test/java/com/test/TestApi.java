package com.test;


import org.junit.Test;

import com.water.Api.entity.ApiBean;
import com.water.Api.entity.YzmBean;
import com.water.Api.plugin.VerificationCodeApiPlugin;
import com.water.Api.service.YzmService;
import com.water.Api.service.Impl.DzapiYzmServiceImpl;

public class TestApi {
 @Test
 public void Api() {

  YzmService<YzmBean> service = new DzapiYzmServiceImpl();
  YzmBean t = new YzmBean();
  t.setUid("qq1106264855");
  t.setPwd("qq123456");
  t.setPid(8597);
  t.setNext_pid("8597");
  service.YzmLogin(t);
  service.YzmGetMobilenum(t);
  VerificationCodeApiPlugin codeApiPlugin = new VerificationCodeApiPlugin();
  codeApiPlugin.requestByGetMethod(
   new ApiBean("http://mp.soqi.cn/login/saveRegister.xhtml?mobile=" + t.getMobiles()[0] + "&&recId=BA38717EF51C84317CB0BCD31B48A3E7",
    "58.219.161.8", "8888", false, true));
  while (true) {
   service.YzmGetVcodeAndHoldMobilenum(t);
   if (!"".equals(t.getCodes()[0])&&t.getCodes()[0]!=null) {
    System.out.println(t.getCode());
    service.YzmAddIgnoreList(t);
    break;
   }
  }
 }
}

