package com.harry.tplugin.bean;



public class AllowTypeLiveView  extends SendAllow {
	/**
	 * 
	 */
	private static final long serialVersionUID = -984743242126564161L;
	private AllowTypeViewId id;

	public AllowTypeLiveView() {
	}

	public AllowTypeLiveView(AllowTypeViewId id) {
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
		return "AllowTypeLiveView [id=" + id + "]";
	}
	
}
