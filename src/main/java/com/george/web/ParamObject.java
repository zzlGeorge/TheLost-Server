package com.george.web;

/**
 * Created by George on 2018/1/2.
 */
public class ParamObject {
    private int code;   //0，操作失败  1，操作成功
    private int pageSize;
    private int pageNumber;
    private int totalDisplaySize;
    private String message;
    private Object dataList;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalDisplaySize() {
        return totalDisplaySize;
    }

    public void setTotalDisplaySize(int totalDisplaySize) {
        this.totalDisplaySize = totalDisplaySize;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDataList() {
        return dataList;
    }

    public void setDataList(Object dataList) {
        this.dataList = dataList;
    }
}
