package com.harry.tplugin.bean;

import java.io.Serializable;



public class StockSumView  implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2271741443234597770L;
	private StockSumViewId id;
	private Integer tmpNum;


	public StockSumView() {
	}


	public StockSumView(StockSumViewId id, Integer tmpNum) {
		super();
		this.id = id;
		this.tmpNum = tmpNum;
	}


	public StockSumViewId getId() {
		return id;
	}


	public void setId(StockSumViewId id) {
		this.id = id;
	}


	public Integer getTmpNum() {
		return tmpNum;
	}


	public void setTmpNum(Integer tmpNum) {
		this.tmpNum = tmpNum;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tmpNum == null) ? 0 : tmpNum.hashCode());
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
		StockSumView other = (StockSumView) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tmpNum == null) {
			if (other.tmpNum != null)
				return false;
		} else if (!tmpNum.equals(other.tmpNum))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "StockSumView [id=" + id + ", tmpNum=" + tmpNum + "]";
	}


}
