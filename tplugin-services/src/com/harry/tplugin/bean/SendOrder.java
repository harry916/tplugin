package com.harry.tplugin.bean;

import java.io.Serializable;

public class SendOrder implements Serializable {

	private static final long serialVersionUID = -2102626711836894667L;
	private String state;
    private String proType;
    private String sendIndex;
	    
	public SendOrder() {
        super();
    }

	public SendOrder(String state, String proType, String sendIndex) {
		super();
		this.state = state;
		this.proType = proType;
		this.sendIndex = sendIndex;
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

	public String getSendIndex() {
		return sendIndex;
	}

	public void setSendIndex(String sendIndex) {
		this.sendIndex = sendIndex;
	}


	@Override
	public String toString() {
		return "SendOrder [state=" + state + ", proType=" + proType
				+ ", sendIndex=" + sendIndex + "]";
	}
    
}
