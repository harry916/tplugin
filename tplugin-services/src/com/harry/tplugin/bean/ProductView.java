package com.harry.tplugin.bean;



public class ProductView  extends Product {

	/**
	 * 
	 */
	private static final long serialVersionUID = 108171137593521830L;
	private ProductViewId id;

	public ProductView() {
	}

	public ProductView(ProductViewId id) {
		this.id = id;
	}

	public ProductViewId getId() {
		return id;
	}

	public void setId(ProductViewId id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ProductView [id=" + id + "]";
	}
	
}
