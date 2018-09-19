package com.milktea.milkteauser.service;

import com.milktea.milkteauser.exception.MilkTeaException;
import com.milktea.milkteauser.vo.IOTBean;
import com.milktea.milkteauser.vo.StripeBean;

public interface PayInfoService {

    void  stripePay(StripeBean stripeBean) throws MilkTeaException;
    void iotPay(IOTBean iotBean);
}
