package com.harry.tplugin.bean;



public class StockBJView  extends Stock {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3778177096093277767L;
	private StockViewId id;
	private Integer tmpNum;

	public Integer getTmpNum() {
		return tmpNum;
	}

	public void setTmpNum(Integer tmpNum) {
		this.tmpNum = tmpNum;
	}

	public StockBJView() {
	}

	public StockBJView(StockViewId id) {
		this.id = id;
	}

	public StockViewId getId() {
		return id;
	}

	public void setId(StockViewId id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "StockBJView [id=" + id + ", tmpNum=" + tmpNum + "]";
	}

}
