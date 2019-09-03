package com.milktea.milkteauser.vo;

public class ResponseBody<T> extends ResponseHeader{
    
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
    
    
    
}
