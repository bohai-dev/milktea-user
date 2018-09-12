package com.milktea.milkteauser.vo;

/**
 * 分页查询请求参数
 * @author caojia
 *
 */
public class PageRequestVo<T> {

    private Integer pageNumber;
    
    private Integer pageSize;
    
    private T params;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }
    
}
