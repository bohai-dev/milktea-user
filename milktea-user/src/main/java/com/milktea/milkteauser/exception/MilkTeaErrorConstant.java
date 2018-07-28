package com.milktea.milkteauser.exception;

/**
 * 错误信息
 * @author caoxx
 *
 */
public enum MilkTeaErrorConstant {
    
    SUCCESS("00000", "成功" , "success");
    
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
