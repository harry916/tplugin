package com.harry.tplugin.bean;



public class AllowTypeUnNormalView  extends SendAllow {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1406142814874769222L;
	private AllowTypeViewId id;

	public AllowTypeUnNormalView() {
	}

	public AllowTypeUnNormalView(AllowTypeViewId id) {
		this.id = id;
	}

	public AllowTypeViewId getId() {
		return id;
	}

	public void setId(AllowTypeViewId id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AllowTypeUnNormalView [id=" + id + "]";
	}
	
}
