package com.harry.tplugin.bean;



public class AllowTypeNormalView  extends SendAllow {


	/**
	 * 
	 */
	private static final long serialVersionUID = 898279422871777810L;
	private AllowTypeViewId id;

	public AllowTypeNormalView() {
	}

	public AllowTypeNormalView(AllowTypeViewId id) {
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
		return "AllowTypeNormalView [id=" + id + "]";
	}
	
}
