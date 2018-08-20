package com.milktea.milkuser.com.milktea.milkteauser.utils;

import com.milktea.milkteauser.config.WXPayConfiguration;
import com.milktea.milkteauser.wxpay.WXPay;

import java.util.HashMap;
import java.util.Map;

public class WXPayTest {

    /**
     * 微信支付统一下单接口（公众号）
     */
    public void unifiedOrder()  throws Exception{
        WXPayConfiguration config = new WXPayConfiguration();
        WXPay wxpay = new WXPay(config);

        Map<String, String> data = new HashMap<String, String>();
        //TODO: 商品描述  商家名称-销售商品类目
        data.put("body", "腾讯-游戏");
        //TODO:商户订单号
        data.put("out_trade_no", "2016090910595900000012");
        //设备号 自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
        // data.put("device_info", "");
        //标价币种   默认人民币 CNY
        data.put("fee_type", "CAD");
        // TODO:标价金额 最小单位
        data.put("total_fee", "1");
        //交易类型: 此处指定为公众号支付
        data.put("trade_type", "JSAPI");
        //TODO:终端IP 用户端ip或者服务器ip
        data.put("spbill_create_ip", "123.12.12.123");
        //TODO:异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
        data.put("notify_url", "http://www.example.com/wxpay/notify");
        //商品ID  trade_type=NATIVE时（即扫码支付），此参数必传。此参数为二维
        // 码中包含的商品ID，商户自行定义。
        //data.put("product_id", "12");
        //TODO:用户标识 trade_type=JSAPI时（即公众号支付），此参数必传，此参数为微信用户在商户对应appid下的唯一标识。
        data.put("openid","123");

        try {
            Map<String, String> resp = wxpay.unifiedOrder(data);
            System.out.println(resp);

           /* 前台需要参数
            "appId":"wx2421b1c4370ec43b",     //公众号名称，由商户传入
            "timeStamp":"1395712654",         //时间戳，自1970年以来的秒数
            "nonceStr":"e61463f8efa94090b1f366cccfbbb444", //随机串
            "package":"prepay_id=u802345jgfjsdfgsdg888",
            "signType":"MD5",         //微信签名方式：
            "paySign":"70EA570631E4BB79628FBCA90534C63FF7FADD89" //微信签名*/
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 微信支付统一下单接口（pc端扫码支付）
     */
    public void unifiedOrder2()  throws Exception{
        WXPayConfiguration config = new WXPayConfiguration();
        WXPay wxpay = new WXPay(config);

        Map<String, String> data = new HashMap<String, String>();
        //TODO: 商品描述  商家名称-销售商品类目
        data.put("body", "腾讯-游戏");
        //TODO:商户订单号，自定义
        data.put("out_trade_no", "2016090910595900000012");
        //设备号 自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
        // data.put("device_info", "");
        //标价币种   默认人民币 CNY
        data.put("fee_type", "CAD");
        // TODO:标价金额 最小单位
        data.put("total_fee", "1");
        //交易类型: 此处指定为NATIVE扫码支付
        data.put("trade_type", "NATIVE ");
        //TODO:终端IP 用户端ip或者服务器ip
        data.put("spbill_create_ip", "123.12.12.123");
        //TODO:异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
        data.put("notify_url", "http://www.example.com/wxpay/notify");
        //商品ID  trade_type=NATIVE时（即扫码支付），此参数必传。此参数为二维
        // 码中包含的商品ID，商户自行定义。
        //data.put("product_id", "12");
        //TODO:用户标识 trade_type=JSAPI时（即公众号支付），此参数必传，此参数为微信用户在商户对应appid下的唯一标识。
        data.put("openid","123");

        try {
            Map<String, String> resp = wxpay.unifiedOrder(data);
            System.out.println(resp);

           //TODO:前台需要code_url（二维码链接）
           //TODO:将链接转成二维码图片
            //String codeHref="http://qr.liantu.com/api.php?text="+codeUrl;
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
