package com.harry.tplugin.bean;

import java.io.Serializable;

public class Store implements Serializable
{
    private static final long serialVersionUID = 5824740183206559916L;
    private String storeId;
    private String storeName;
    private String sendScope;
    private Integer index;
    
	public Store() {
        super();
    }
    public Store(String storeId, String storeName, String sendScope,
			Integer index) {
		super();
		this.storeId = storeId;
		this.storeName = storeName;
		this.sendScope = sendScope;
		this.index = index;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getSendScope() {
		return sendScope;
	}
	public void setSendScope(String sendScope) {
		this.sendScope = sendScope;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	@Override
	public String toString() {
		return "Store [storeId=" + storeId + ", storeName=" + storeName
				+ ", sendScope=" + sendScope + ", index=" + index + "]";
	}

}
