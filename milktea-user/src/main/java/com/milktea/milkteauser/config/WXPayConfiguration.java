package com.milktea.milkteauser.config;

import com.milktea.milkteauser.wxpay.IWXPayDomain;
import com.milktea.milkteauser.wxpay.WXPayConfig;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 微信支付配置类
 *
 */
public class WXPayConfiguration  extends WXPayConfig {

    private byte[] certData;

    public WXPayConfiguration() throws Exception {
        //证书
        String certPath = "/path/to/apiclient_cert.p12";
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    //公众账号ID
    @Override
    public String getAppID() {
        return null;
    }

    //商户号
    @Override
    public String getMchID() {
        return null;
    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }

    @Override
    public IWXPayDomain getWXPayDomain() {
        return null;
    }
}
