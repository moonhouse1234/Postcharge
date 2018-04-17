package com.yzcx.postcharge.entity.product;

import java.util.List;

/**
 * @author liuqingjie
 * @create $date$
 * @description $desc$
 */

public class Product {
    private String msg;
    private int error;
    private List<Content> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public List<Content> getData() {
        return data;
    }

    public void setData(List<Content> data) {
        this.data = data;
    }
}
