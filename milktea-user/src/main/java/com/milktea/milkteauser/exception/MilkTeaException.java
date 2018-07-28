package com.milktea.milkteauser.exception;

/**
 * 奶茶异常类
 * @author caoxx
 *
 */
public class MilkTeaException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -3811588015556309876L;
    
    private final String errorCode;
    
    private final String cnErrorMsg;
    
    private final String usErrorMsg;
    
    public MilkTeaException(String errorCode, String cnErrorMsg, String usErrorMsg) {
        super(cnErrorMsg);
        this.errorCode = errorCode;
        this.cnErrorMsg = cnErrorMsg;
        this.usErrorMsg = usErrorMsg;
    }
    
    public MilkTeaException(String errorCode, String cnErrorMsg, String usErrorMsg, Throwable cause) {
        super(cnErrorMsg, cause);
        this.errorCode = errorCode;
        this.cnErrorMsg = cnErrorMsg;
        this.usErrorMsg = usErrorMsg;
    }
    
    public MilkTeaException(MilkTeaErrorConstant errorConstant) {
        super(errorConstant.cnErrorMsg);
        this.errorCode = errorConstant.errorCode;
        this.cnErrorMsg = errorConstant.cnErrorMsg;
        this.usErrorMsg = errorConstant.usErrorMsg;
    }
    
    public MilkTeaException(MilkTeaErrorConstant errorConstant, Throwable cause) {
        super(errorConstant.cnErrorMsg, cause);
        this.errorCode = errorConstant.errorCode;
        this.cnErrorMsg = errorConstant.cnErrorMsg;
        this.usErrorMsg = errorConstant.usErrorMsg;
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
