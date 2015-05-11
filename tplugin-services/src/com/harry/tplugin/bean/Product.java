package com.harry.tplugin.bean;

import java.io.Serializable;

public class Product  implements Serializable {
	private static final long serialVersionUID = -1258357212515973833L;
	private String proId;
	private String proName;
	private String proType;
	private String unstandard;
	

	public Product(){}


	public Product(String proId, String proName, String proType,
			String unstandard) {
		this.proId = proId;
		this.proName = proName;
		this.proType = proType;
		this.unstandard = unstandard;
	}


	public String getProId() {
		return proId;
	}


	public void setProId(String proId) {
		this.proId = proId;
	}


	public String getProName() {
		return proName;
	}


	public void setProName(String proName) {
		this.proName = proName;
	}


	public String getProType() {
		return proType;
	}


	public void setProType(String proType) {
		this.proType = proType;
	}


	public String getUnstandard() {
		return unstandard;
	}


	public void setUnstandard(String unstandard) {
		this.unstandard = unstandard;
	}


	@Override
	public String toString() {
		return "Product [proId=" + proId + ", proName=" + proName
				+ ", proType=" + proType + ", unstandard=" + unstandard + "]";
	}

}
