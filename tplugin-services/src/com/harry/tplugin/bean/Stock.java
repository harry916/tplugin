package com.harry.tplugin.bean;

import java.io.Serializable;

public class Stock implements Serializable
{
    private static final long serialVersionUID = 9078689068543198959L;
    private String proId;
    private String storeId;
    private Integer num;
    private Integer tmpNum;
    
	public Stock() {
        super();
    }

    public Stock(String proId, String storeId, Integer num, Integer tmpNum) {
        super();
        this.proId = proId;
        this.storeId = storeId;
        this.num = num;
        this.tmpNum = tmpNum;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getTmpNum() {
        return tmpNum;
    }

    public void setTmpNum(Integer tmpNum) {
        this.tmpNum = tmpNum;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "Stock [proId=" + proId + ", storeId=" + storeId + ", num="
                + num + ", tmpNum=" + tmpNum + "]";
    }
    
}
