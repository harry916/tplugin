package com.harry.tplugin.bean;



public class StockSHView  extends Stock {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 34460919717003287L;
	private StockViewId id;
	private Integer tmpNum;


	public StockSHView() {
	}

	public StockSHView(StockViewId id) {
		this.id = id;
	}

	public StockViewId getId() {
		return id;
	}

	public Integer getTmpNum() {
		return tmpNum;
	}

	public void setTmpNum(Integer tmpNum) {
		this.tmpNum = tmpNum;
	}

	public void setId(StockViewId id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "StockSHView [id=" + id + ", tmpNum=" + tmpNum + "]";
	}

}
