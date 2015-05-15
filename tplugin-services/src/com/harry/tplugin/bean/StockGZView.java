package com.harry.tplugin.bean;



public class StockGZView  extends Stock {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4946261258365022877L;
	private StockViewId id;
	private Integer tmpNum;


	public StockGZView() {
	}

	public StockGZView(StockViewId id) {
		this.id = id;
	}

	public StockViewId getId() {
		return id;
	}

	public void setId(StockViewId id) {
		this.id = id;
	}

	public Integer getTmpNum() {
		return tmpNum;
	}

	public void setTmpNum(Integer tmpNum) {
		this.tmpNum = tmpNum;
	}

	@Override
	public String toString() {
		return "StockGZView [id=" + id + ", tmpNum=" + tmpNum + "]";
	}

}
