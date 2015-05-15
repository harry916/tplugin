package com.harry.tplugin.bean;

import java.io.Serializable;

public class AllowTypeViewId implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1465349460660430124L;
	private String state;
    private String proType;
    private String storeJson;
    
	public AllowTypeViewId() {
    }

	public AllowTypeViewId(String state, String proType, String storeJson) {
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
		return "AllowTypeLiveViewId [state=" + state + ", proType=" + proType
				+ ", storeJson=" + storeJson + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((proType == null) ? 0 : proType.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result
				+ ((storeJson == null) ? 0 : storeJson.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AllowTypeViewId other = (AllowTypeViewId) obj;
		if (proType == null) {
			if (other.proType != null)
				return false;
		} else if (!proType.equals(other.proType))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (storeJson == null) {
			if (other.storeJson != null)
				return false;
		} else if (!storeJson.equals(other.storeJson))
			return false;
		return true;
	}
    
}
