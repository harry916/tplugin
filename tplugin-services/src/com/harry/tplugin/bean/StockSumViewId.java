package com.harry.tplugin.bean;

import java.io.Serializable;

public class StockSumViewId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 582954077009875816L;
	private String proId;
	private Integer num;

	public StockSumViewId() {
	}

	public StockSumViewId(String proId, String storeId, Integer num) {
		super();
		this.proId = proId;
		this.num = num;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
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
		StockSumViewId other = (StockSumViewId) obj;
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
		return true;
	}

	@Override
	public String toString() {
		return "StockSumViewId [proId=" + proId + ", num=" + num + "]";
	}


}
