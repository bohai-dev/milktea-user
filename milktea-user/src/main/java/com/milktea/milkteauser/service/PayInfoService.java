package com.milktea.milkteauser.service;

import com.milktea.milkteauser.exception.MilkTeaException;
import com.milktea.milkteauser.vo.IOTBean;
import com.milktea.milkteauser.vo.IotResponseBean;
import com.milktea.milkteauser.vo.ResponseBody;
import com.milktea.milkteauser.vo.StripeBean;

public interface PayInfoService {

    void  stripePay(StripeBean stripeBean) throws MilkTeaException;
    ResponseBody<String> iotPay(IOTBean iotBean) throws MilkTeaException;
    String iotNotify(IotResponseBean iotResponseBean) throws MilkTeaException;
}
