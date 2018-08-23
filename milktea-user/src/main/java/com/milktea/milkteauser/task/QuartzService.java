package com.milktea.milkteauser.task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.milktea.milkteauser.dao.TeaGlobalTokenMapper;
import com.milktea.milkteauser.domain.TeaGlobalToken;
import com.milktea.milkteauser.exception.MilkTeaErrorConstant;



@Component
public class QuartzService {
	static Logger log = LoggerFactory.getLogger(QuartzService.class);
	
	public static String weiXinAppid = "wxbac9e1b7d8104470";
	
	public static String weiXinSecret = "08695399b120b9ed523db01ddd51d38d";
	
	public static String weiXinGrantType = "authorization_code";
	
	public String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
	
	public String param;
	
	@Autowired
	TeaGlobalTokenMapper teaGlobalTokenMapper;
	
	
	
	@Scheduled(cron = "0 0/60 * * * ?")
    public void timerToNow(){
		//取得系统全局TOKEN 根据APPID及密码
		//请求获得全局Access Token
		//https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
		url = "https://api.weixin.qq.com/cgi-bin/token";
		param = "appid=" + weiXinAppid +"&" + "secret=" + weiXinSecret + "&" + "grant_type=client_credential";
		String result = "";
	    BufferedReader in = null;
	    String access_token = "";
	    String openid = "";
	    try {
	        String urlNameString = url + "?" + param;
	        URL realUrl = new URL(urlNameString);
	        // 打开和URL之间的连接
	        URLConnection connection = realUrl.openConnection();
	        // 设置通用的请求属性
	        connection.setRequestProperty("accept", "*/*");
	        connection.setRequestProperty("connection", "Keep-Alive");
	        connection.setRequestProperty("user-agent",
	                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	        // 建立实际的连接
	        connection.connect();
	        // 获取所有响应头字段
	        Map<String, List<String>> map = connection.getHeaderFields();
	        // 遍历所有的响应头字段
	        for (String key : map.keySet()) {
	            System.out.println(key + "--->" + map.get(key));
	        }
	        // 定义 BufferedReader输入流来读取URL的响应
	        in = new BufferedReader(new InputStreamReader(
	                connection.getInputStream()));
	        String line;
	        while ((line = in.readLine()) != null) {
	            result += line;
	        }
	        
	    } catch (Exception e) {
	    	log.error(MilkTeaErrorConstant.WEIXIN_GLOBALTOKENMISSING_FAILURE.getCnErrorMsg(), e);
	    	return;
	        
	    }
	    // 使用finally块来关闭输入流
	    finally {
	        try {
	            if (in != null) {
	                in.close();
	            }
	        } catch (Exception e2) {
	            e2.printStackTrace();
	        }
	    }
        JSONObject json = JSON.parseObject(result);
        //如果是TOKEN过期要重新刷新TOKEN
        String errCode = "";
        errCode = json.getString("errcode");
        if(null != errCode){
      	  //TOKEN出错了。
        	log.error(MilkTeaErrorConstant.WEIXIN_GLOBALTOKENMISSING_FAILURE.getCnErrorMsg());
        	return;
        }
        //全局TOKEN 13_zWXUzf7anEw6_weLpWHqJY4oxldG6lJayeOHgfrmlc0E3vPtjUDtiWEN6ui2lbJM9z622d9uvQbPER4OXLwUZ9WS9fRPyZ8H8Gm9hpdMTipTPblST718f0Ri7uzd9hamycor12llao5xQRbvYJTdADAMXH
        access_token = json.getString("access_token");
        teaGlobalTokenMapper.deleteAll();
        
        TeaGlobalToken teaGlobalToken = new TeaGlobalToken();
        teaGlobalToken.setToken(access_token);
        teaGlobalToken.setInsertTime(new Date());
        
        teaGlobalTokenMapper.insert(teaGlobalToken);
        
		
		
		log.info("取得系统TOKEN:",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),"每隔60分钟执行");
        System.out.println("取得系统TOKEN:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "，每60分钟运行一次！");
    }



}
