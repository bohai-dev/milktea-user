package com.milktea.milkteauser.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.milktea.milkteauser.domain.TeaUserInfo;
import com.milktea.milkteauser.exception.MilkTeaException;
import com.milktea.milkteauser.vo.ResponseHeader;




@RestController
@RequestMapping("/userLogin")
public class UserLoginController {

	public static String weiXinAppid = "wxbac9e1b7d8104470";
	
	public static String weiXinSecret = "08695399b120b9ed523db01ddd51d38d";
	
	public static String weiXinGrantType = "authorization_code";
	
	public String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
	
	public String param;
	
	//微信客户登入
	@RequestMapping(value="/weixin", method = RequestMethod.POST)
	public ResponseHeader  userInfoLogin(String code) throws MilkTeaException{
		
		ResponseHeader header = new ResponseHeader();
		
		//获取code后，请求以下链接获取access_token https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
		param = "appid=" + weiXinAppid +"&" + "secret=" + weiXinSecret + "&" + "code=" + code + "&" + "grant_type=authorization_code";
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
            JSONObject json = JSON.parseObject(result);
            access_token = json.getString("access_token");
            openid = json.getString("openid");
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
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
        
        
       //获取access_token后，拉取用户信息 https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
        url = "https://api.weixin.qq.com/sns/userinfo";
      		param = "access_token=" + access_token +"&" + "openid=" + openid + "&" + "lang=zh_CN";
      		result = "";
            in = null;
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
                  JSONObject json = JSON.parseObject(result);
                  openid = json.getString("openid");
                  String nickname = json.getString("nickname");
                  String sex = json.getString("sex");
                  String province = json.getString("province");
                  String city = json.getString("city");
                  String country = json.getString("country");
                  String headimgurl = json.getString("headimgurl");
                  String privilege = json.getString("privilege");
                  
                  System.out.println(result);
              } catch (Exception e) {
                  System.out.println("发送GET请求出现异常！" + e);
                  e.printStackTrace();
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
        
        

		return header;
	}
	
}
