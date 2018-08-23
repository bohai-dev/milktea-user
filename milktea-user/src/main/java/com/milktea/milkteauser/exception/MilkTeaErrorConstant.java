package com.milktea.milkteauser.exception;

/**
 * 错误信息
 * @author caoxx
 *
 */
public enum MilkTeaErrorConstant {
    
    SUCCESS("00000", "成功" , "success"),
    
    DATABASE_ACCESS_FAILURE("00001", "数据库访问失败" , "Database access failure"),
    WEIXIN_ACCESSTOKEN_FAILURE("00002", "微信access_token取得错误" , "Get access_token failure"),
    WEIXIN_GETUSERINFO_FAILURE("00003", "微信用户详细信息取得错误" , "Get userinfo failure"),
    MILETEA_SHOP_FAILURE("00004","后台返回数据异常","Get mileteashop failure"),
    SMS_FAILURE("00005","手机短消息异常","SMS failure"),
    SMS_RESPONSE_FAILURE("00006","手机短消息请求异常","SMS RESPONSE failure"),
    PAY_FAIL("00007","",""),
    SMS_REGISTEFAILURE("00008","短消息验证失败","SMS registe failure"),
    SMS_OUTTIME("00009","短消息验证超时","SMS OUTTIME"),
    WEIXIN_REFRESHTOKEN_FAILURE("00010", "微信TOKEN刷新错误" , "Get refreshToken failure"),
    WEIXIN_ACCESSTOKENMISSING_FAILURE("41001","微信内部错误","access_token missing"),
    WEIXIN_GLOBALTOKENMISSING_FAILURE("00011","微信全局TOKEN取得错误","Get global_token failure"),
    
   
    
    
    
    UNKNOW_EXCEPTION("10000", "程序内部异常" , "Something wrong with program");
    
    final String errorCode;
    
    final String cnErrorMsg;
    
    final String usErrorMsg;
    
    private MilkTeaErrorConstant(String errorCode , String cnErrorMsg, String usErrorMsg){
        this.errorCode = errorCode;
        this.cnErrorMsg = cnErrorMsg;
        this.usErrorMsg = usErrorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getCnErrorMsg() {
        return cnErrorMsg;
    }

    public String getUsErrorMsg() {
        return usErrorMsg;
    }

}
