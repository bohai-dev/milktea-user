package com.milktea.milkteauser.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.milktea.milkteauser.dao.TeaGlobalTokenMapper;
import com.milktea.milkteauser.dao.TeaLoginWeixinMapper;
import com.milktea.milkteauser.domain.TeaGlobalToken;
import com.milktea.milkteauser.domain.TeaLoginWeixin;
import com.milktea.milkteauser.exception.MilkTeaErrorConstant;
import com.milktea.milkteauser.exception.MilkTeaException;
import com.milktea.milkteauser.service.UserLoginService;


@Service("userLoginService")
public  class UserLoginServiceImpl implements UserLoginService {
	
public static String weiXinAppid = "wxbac9e1b7d8104470";
	
	public static String weiXinSecret = "08695399b120b9ed523db01ddd51d38d";
	
	public static String weiXinGrantType = "authorization_code";
	
	public String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
	
	public String param;

	@Autowired
	TeaLoginWeixinMapper teaLoginWeixinMapper;
	
	@Autowired
	TeaGlobalTokenMapper teaGlobalTokenMapper;
	
	
	static Logger logger = LoggerFactory.getLogger(UserLoginServiceImpl.class);

	@Override
	public Integer insert(TeaLoginWeixin teaLoginWeixin) throws MilkTeaException {
		
		TeaLoginWeixin teaLoginWeixintemp = new TeaLoginWeixin();
		teaLoginWeixintemp = this.selectByopenId(teaLoginWeixin.getWeixinOpenid());
		if(null == teaLoginWeixintemp){
			//如果openid不存在则插入
			teaLoginWeixinMapper.insertSelective(teaLoginWeixin);
		} else {
			//如果openID已经存在则更新
			teaLoginWeixinMapper.updateByPrimaryKey(teaLoginWeixin);
		}
		
		return 1;
	}

	@Override
	public TeaLoginWeixin selectByopenId(String openId) throws MilkTeaException {
		//openID查询
		TeaLoginWeixin teaLoginWeixintemp = new TeaLoginWeixin();
		teaLoginWeixintemp = teaLoginWeixinMapper.selectByPrimaryKey(openId);
		return teaLoginWeixintemp;
		
	}

	@Override
	public Integer update(TeaLoginWeixin teaLoginWeixin) throws MilkTeaException {
		
		return null;
	}

	@Override
	public TeaLoginWeixin getTokenOpenId(String code) throws MilkTeaException {
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
        JSONObject json = JSON.parseObject(result);
        //如果是TOKEN过期要重新刷新TOKEN
        String errCode = "";
        errCode = json.getString("errcode");
        if(null != errCode){
      	  //TOKEN出错了。
        	throw new MilkTeaException(MilkTeaErrorConstant.WEIXIN_ACCESSTOKENMISSING_FAILURE);
        }
        access_token = json.getString("access_token");
        openid = json.getString("openid");
        System.out.println(result);
        TeaLoginWeixin teaLoginWeixin = new TeaLoginWeixin();
        teaLoginWeixin.setWeixinOpenid(openid);

	    
	    
	    
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
	              teaLoginWeixin = new TeaLoginWeixin();
	              json = JSON.parseObject(result);
	              teaLoginWeixin.setWeixinOpenid(json.getString("openid"));
	              teaLoginWeixin.setWeixinNickname(json.getString("nickname"));
	              teaLoginWeixin.setWeixinSex(json.getString("sex"));
	              teaLoginWeixin.setPrivilege(json.getString("privilege"));
	              teaLoginWeixin.setCity(json.getString("city"));
	              teaLoginWeixin.setCountry(json.getString("country"));
	              teaLoginWeixin.setHeadimgurl(json.getString("headimgurl"));
	              teaLoginWeixin.setWeixinProvince(json.getString("province"));
	              teaLoginWeixin.setAccessToken(access_token);
	              this.insert(teaLoginWeixin);
	              
	              return teaLoginWeixin;
	             
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
	}

	@Override
	public TeaLoginWeixin getWeixinCheck(String code, String accessToken, String openId) throws MilkTeaException {
		//获取access_token后，拉取用户信息 https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
	    url = "https://api.weixin.qq.com/sns/userinfo";
  		param = "access_token=" + accessToken +"&" + "openid=" + openId + "&" + "lang=zh_CN";
  		String result = "";
	    BufferedReader in = null;
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
              //如果是TOKEN过期要重新刷新TOKEN
              String errCode = "";
              errCode = json.getString("errcode");
              if(null != errCode){
            	  //TOKEN出错了。
            	  return this.getrefreshToken(code,accessToken,openId);
              }
              
              teaLoginWeixin.setWeixinOpenid(json.getString("openid"));
              teaLoginWeixin.setWeixinNickname(json.getString("nickname"));
              teaLoginWeixin.setWeixinSex(json.getString("sex"));
              teaLoginWeixin.setPrivilege(json.getString("privilege"));
              teaLoginWeixin.setCity(json.getString("city"));
              teaLoginWeixin.setCountry(json.getString("country"));
              teaLoginWeixin.setHeadimgurl(json.getString("headimgurl"));
              teaLoginWeixin.setWeixinProvince(json.getString("province"));
              teaLoginWeixin.setAccessToken(accessToken);
              this.insert(teaLoginWeixin);
              
              return teaLoginWeixin;
             
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
	}
	
	
	private TeaLoginWeixin getrefreshToken(String code,String accessToken, String openId) throws MilkTeaException {
		 url = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
//		 https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN
	  		param = "appid=" + weiXinAppid +"&" + "refresh_token=" + accessToken + "&" + "grant_type=refresh_token";
	  		String result = "";
		    BufferedReader in = null;
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
	              //如果是TOKEN过期要重新刷新TOKEN
	              String errCode = "";
	              errCode = json.getString("errcode");
	              if(null != errCode){
	            	  //GETTOKEN出错了。三十日过期
	            	  return this.getTokenOpenId(code);
	              }
	              
	              //得到新的TOKEN 
	              accessToken = json.getString("access_token");
	              
	              url = "https://api.weixin.qq.com/sns/userinfo";
	        		param = "access_token=" + accessToken +"&" + "openid=" + openId + "&" + "lang=zh_CN";
	        		result = "";
	      	        in = null;
	                    urlNameString = url + "?" + param;
	                    realUrl = new URL(urlNameString);
	                    // 打开和URL之间的连接
	                    connection = realUrl.openConnection();
	                    // 设置通用的请求属性
	                    connection.setRequestProperty("accept", "*/*");
	                    connection.setRequestProperty("connection", "Keep-Alive");
	                    connection.setRequestProperty("user-agent",
	                            "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	                    // 建立实际的连接
	                    connection.connect();
	                    // 获取所有响应头字段
	                    map = connection.getHeaderFields();
	                    // 遍历所有的响应头字段
	                    for (String key : map.keySet()) {
	                        System.out.println(key + "--->" + map.get(key));
	                    }
	                    // 定义 BufferedReader输入流来读取URL的响应
	                    in = new BufferedReader(new InputStreamReader(
	                            connection.getInputStream()));
	                    line = "";
	                    while ((line = in.readLine()) != null) {
	                        result += line;
	                    }
	                    teaLoginWeixin = new TeaLoginWeixin();
	                    json = JSON.parseObject(result);
	                    //如果是TOKEN过期要重新刷新TOKEN
	                    errCode = "";
	                    errCode = json.getString("errcode");
	                    if(!"".equals(errCode)){
	                  	  //TOKEN出错了。
	                  	  return this.getrefreshToken(code,accessToken,openId);
	                    }
	                    
	                    teaLoginWeixin.setWeixinOpenid(json.getString("openid"));
	                    teaLoginWeixin.setWeixinNickname(json.getString("nickname"));
	                    teaLoginWeixin.setWeixinSex(json.getString("sex"));
	                    teaLoginWeixin.setPrivilege(json.getString("privilege"));
	                    teaLoginWeixin.setCity(json.getString("city"));
	                    teaLoginWeixin.setCountry(json.getString("country"));
	                    teaLoginWeixin.setHeadimgurl(json.getString("headimgurl"));
	                    teaLoginWeixin.setWeixinProvince(json.getString("province"));
	                    teaLoginWeixin.setAccessToken(accessToken);
	                    this.insert(teaLoginWeixin);
	                    
	                    return teaLoginWeixin;

	          } catch (Exception e) {
	        	  logger.error(MilkTeaErrorConstant.WEIXIN_REFRESHTOKEN_FAILURE.getCnErrorMsg(), e);
	              throw new MilkTeaException(MilkTeaErrorConstant.WEIXIN_REFRESHTOKEN_FAILURE, e);
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
	}

	@Override
	public TeaLoginWeixin getGlobalToken(String code, String accessToken, String openId) throws MilkTeaException {
		//获取access_token后，拉取用户信息//https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID
//		取得全局TOKEN
		TeaGlobalToken teaGlobalToken = new TeaGlobalToken();
		teaGlobalToken = teaGlobalTokenMapper.getGlobalToken();
		
		
	    url = "https://api.weixin.qq.com/cgi-bin/user/info";
  		param = "access_token=" + teaGlobalToken.getToken() +"&" + "openid=" + openId ;
  		String result = "";
	    BufferedReader in = null;
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
              //如果是TOKEN过期要重新刷新TOKEN
              String errCode = "";
              errCode = json.getString("errcode");
              if(null != errCode){
            	  //TOKEN出错了。
            	  logger.error(MilkTeaErrorConstant.WEIXIN_USEGLOBALTOKEN_FAILURE.getCnErrorMsg());
                  throw new MilkTeaException(MilkTeaErrorConstant.WEIXIN_USEGLOBALTOKEN_FAILURE);
              }
              
              teaLoginWeixin.setWeixinOpenid(json.getString("openid"));
              teaLoginWeixin.setWeixinNickname(json.getString("nickname"));
              teaLoginWeixin.setWeixinSex(json.getString("sex"));
//              teaLoginWeixin.setPrivilege(json.getString("privilege"));
              teaLoginWeixin.setCity(json.getString("city"));
              teaLoginWeixin.setCountry(json.getString("country"));
              teaLoginWeixin.setHeadimgurl(json.getString("headimgurl"));
              teaLoginWeixin.setWeixinProvince(json.getString("province"));
              teaLoginWeixin.setAccessToken(accessToken);
              this.insert(teaLoginWeixin);
              
              return teaLoginWeixin;
             
          } catch (Exception e) {
        	  logger.error(MilkTeaErrorConstant.WEIXIN_USEGLOBALTOKEN_FAILURE.getCnErrorMsg(), e);
              throw new MilkTeaException(MilkTeaErrorConstant.WEIXIN_USEGLOBALTOKEN_FAILURE, e);
        	  
        	  
        	  
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
	}
	
	
	

	
	
	
	
    
}
