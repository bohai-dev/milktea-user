package com.milktea.milkteauser.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.milktea.milkteauser.domain.TeaLoginWeixin;
import com.milktea.milkteauser.exception.MilkTeaErrorConstant;
import com.milktea.milkteauser.exception.MilkTeaException;
import com.milktea.milkteauser.service.UserLoginService;
import com.milktea.milkteauser.vo.ClassGoodsRequestVo;
import com.milktea.milkteauser.vo.ResponseBody;







@RestController
@RequestMapping("/userLogin")
public class UserLoginController {

	public static String weiXinAppid = "wxbac9e1b7d8104470";
	
	public static String weiXinSecret = "08695399b120b9ed523db01ddd51d38d";
	
	public static String weiXinGrantType = "authorization_code";
	
	public String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
	
	public String param;
	
	@Autowired
    private UserLoginService userLoginService;
	
	//微信客户登入
	@RequestMapping(value="/weixin", method = RequestMethod.POST)
	public ResponseBody<TeaLoginWeixin>  userInfoLogin(String code) throws MilkTeaException{
		Logger logger = LoggerFactory.getLogger(UserLoginController.class);
		
		ResponseBody<TeaLoginWeixin> responseBody = new ResponseBody<TeaLoginWeixin>();
		
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
            TeaLoginWeixin teaLoginWeixin = new TeaLoginWeixin();
            teaLoginWeixin.setWeixinOpenid(openid);
            responseBody.setData(teaLoginWeixin);
        } catch (Exception e) {
        	logger.error(MilkTeaErrorConstant.WEIXIN_ACCESSTOKEN_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.WEIXIN_ACCESSTOKEN_FAILURE, e);
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
                  TeaLoginWeixin teaLoginWeixin = new TeaLoginWeixin();
                  JSONObject json = JSON.parseObject(result);
                  teaLoginWeixin.setWeixinOpenid(json.getString("openid"));
                  teaLoginWeixin.setWeixinNickname(json.getString("nickname"));
                  teaLoginWeixin.setWeixinSex(json.getString("sex"));
                  teaLoginWeixin.setPrivilege(json.getString("privilege"));
                  teaLoginWeixin.setCity(json.getString("city"));
                  teaLoginWeixin.setCountry(json.getString("country"));
                  teaLoginWeixin.setHeadimgurl(json.getString("headimgurl"));
                  teaLoginWeixin.setWeixinProvince(json.getString("province"));
                  this.userLoginService.insert(teaLoginWeixin);
                  
                  responseBody.setData(teaLoginWeixin);
                  
                  
                  System.out.println(result);
              } catch (Exception e) {
            	  logger.error(MilkTeaErrorConstant.WEIXIN_GETUSERINFO_FAILURE.getCnErrorMsg(), e);
                  throw new MilkTeaException(MilkTeaErrorConstant.WEIXIN_GETUSERINFO_FAILURE, e);
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
        
        

		return responseBody;
	}
	
	//得到所有店铺LIST
	@RequestMapping(value="/storelist", method = RequestMethod.POST)
	public ResponseBody<JSONObject>  getStoreList() throws MilkTeaException{
		BufferedReader in = null;
		String result = "";
		Logger logger = LoggerFactory.getLogger(UserLoginController.class);
		ResponseBody<JSONObject> responseBody = new ResponseBody<JSONObject>();
		JSONObject jsonObject = new JSONObject();
		//调用商品后台，取得默认登入商铺内的商品及所有商铺
        
        //所有商铺 
//		param = "appid=" + weiXinAppid +"&" + "secret=" + weiXinSecret + "&" + "code=" + code + "&" + "grant_type=authorization_code";
        String url = "https://******/queryStores";
        String urlNameString = url ;
        
        try {
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
	        String line = "";
	        while ((line = in.readLine()) != null) {
	            result += line;
	        }
	        jsonObject = JSON.parseObject(result);
	        responseBody.setData(jsonObject);
        }  catch (Exception e) {
        	logger.error(MilkTeaErrorConstant.MILETEA_SHOP_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.MILETEA_SHOP_FAILURE, e);
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
		return responseBody;
	}
	
	//取得店铺内的商品
	@RequestMapping(value="/getClassGoods", method = RequestMethod.POST)
	public ResponseBody<JSONObject>  getClassGoods(String storeNo,String classType) throws MilkTeaException{
		BufferedReader in = null;
		String result = "";
		Logger logger = LoggerFactory.getLogger(UserLoginController.class);
		ResponseBody<JSONObject> responseBody = new ResponseBody<JSONObject>();
		JSONObject jsonObject = new JSONObject();
		
		//调用商品后台，取得默认登入商铺内的商品及所有商铺
        
        //所有商铺 
//			param = "appid=" + weiXinAppid +"&" + "secret=" + weiXinSecret + "&" + "code=" + code + "&" + "grant_type=authorization_code";
        String url = "https://******/queryClassGoods";
//        String urlNameString = url + "?" + JSON.toJSONString(classGoodsRequestVo);
        
//        try {
//	        URL realUrl = new URL(urlNameString);
//	        // 打开和URL之间的连接
//	        URLConnection connection = realUrl.openConnection();
//	        // 设置通用的请求属性
//	        connection.setRequestProperty("accept", "*/*");
//	        connection.setRequestProperty("connection", "Keep-Alive");
//	        connection.setRequestProperty("user-agent",
//	                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//	        // 建立实际的连接
//	        connection.connect();
//	        // 获取所有响应头字段
//	        Map<String, List<String>> map = connection.getHeaderFields();
//	        // 遍历所有的响应头字段
//	        for (String key : map.keySet()) {
//	            System.out.println(key + "--->" + map.get(key));
//	        }
//	        // 定义 BufferedReader输入流来读取URL的响应
//	        in = new BufferedReader(new InputStreamReader(
//	                connection.getInputStream()));
//	        String line = "";
//	        while ((line = in.readLine()) != null) {
//	            result += line;
//	        }
//	        jsonObject = JSON.parseObject(result);
//	        responseBody.setData(jsonObject);
//        }  catch (Exception e) {
//        	logger.error(MilkTeaErrorConstant.MILETEA_SHOP_FAILURE.getCnErrorMsg(), e);
//            throw new MilkTeaException(MilkTeaErrorConstant.MILETEA_SHOP_FAILURE, e);
//        }
//        // 使用finally块来关闭输入流
//        finally {
//            try {
//                if (in != null) { }
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            }
//        }
        //POST 请求
        JsonObject message = new JsonObject();
        message.addProperty("storeNo", storeNo);
        message.addProperty("classType", classType);
        PrintWriter out = null;
//        InputStream in = null;
        JsonObject jo = null;
        try {
            // 打开和URL之间的连接
            URLConnection conn = new URL(url).openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(message.toString());
            // flush输出流的缓冲
            out.flush();
            // POST请求
     
            out.flush();
            out.close();
	        // 定义 BufferedReader输入流来读取URL的响应
	        in = new BufferedReader(new InputStreamReader(
	        		conn.getInputStream()));
	        String line = "";
	        while ((line = in.readLine()) != null) {
	            result += line;
	        }
	        jsonObject = JSON.parseObject(result);
	        responseBody.setData(jsonObject);

        }  catch (Exception e) {
        	logger.error(MilkTeaErrorConstant.MILETEA_SHOP_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.MILETEA_SHOP_FAILURE, e);
//        }
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
            if (in != null ){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        
        
        
		return responseBody;
	}
	
	
	
}
