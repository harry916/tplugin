package com.harry.tplugin.bean;

import java.io.Serializable;

public class SendAllow implements Serializable
{
    private static final long serialVersionUID = 5824740183206559916L;
   
    private String state;
    private String proType;
    private String storeJson;
    
	public SendAllow() {
        super();
    }

	public SendAllow(String state, String proType, String storeJson) {
		super();
		this.state = state;
		this.proType = proType;
		this.storeJson = storeJson;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getProType() {
		return proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}

	public String getStoreJson() {
		return storeJson;
	}

	public void setStoreJson(String storeJson) {
		this.storeJson = storeJson;
	}


	@Override
	public String toString() {
		return "SendAllow [state=" + state + ", proType=" + proType
				+ ", storeJson=" + storeJson + "]";
	}
    
}
