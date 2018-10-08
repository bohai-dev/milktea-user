package com.milktea.milkuser.com.milktea.milkteauser.utils;

import com.milktea.milkteauser.service.PayInfoService;
import com.milktea.milkteauser.vo.IOTBean;
import com.milktea.milkteauser.vo.IotResponseBean;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UtilsTest {

  @Test
  public void test(){
      IotResponseBean iotResponseBean=new IotResponseBean();
      iotResponseBean.setPayOrderId("11111");
      iotResponseBean.setAmount(100);
      iotResponseBean.setMchId("11561");
      iotResponseBean.setCurrency("CAD");

      System.out.println(iotResponseBean.toString());

  }
}
