package com.harry.tplugin.bean;

import java.io.Serializable;

public class StockViewId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9015013971001450495L;
	private String proId;
	private String storeId;
	private Integer num;

	public StockViewId() {
	}

	public StockViewId(String proId, String storeId, Integer num) {
		super();
		this.proId = proId;
		this.storeId = storeId;
		this.num = num;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((num == null) ? 0 : num.hashCode());
		result = prime * result + ((proId == null) ? 0 : proId.hashCode());
		result = prime * result + ((storeId == null) ? 0 : storeId.hashCode());
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
		StockViewId other = (StockViewId) obj;
		if (num == null) {
			if (other.num != null)
				return false;
		} else if (!num.equals(other.num))
			return false;
		if (proId == null) {
			if (other.proId != null)
				return false;
		} else if (!proId.equals(other.proId))
			return false;
		if (storeId == null) {
			if (other.storeId != null)
				return false;
		} else if (!storeId.equals(other.storeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StockViewId [proId=" + proId + ", storeId=" + storeId
				+ ", num=" + num + "]";
	}


}
