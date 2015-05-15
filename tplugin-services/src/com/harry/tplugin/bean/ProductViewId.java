package com.harry.tplugin.bean;

import java.io.Serializable;

public class ProductViewId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4918810021661864137L;
	private String proId;
	private String proName;
	private String proType;
	private String unstandard;
    
	public ProductViewId() {
    }

	public ProductViewId(String proId, String proName, String proType,
			String unstandard) {
		super();
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
		return "ProductViewId [proId=" + proId + ", proName=" + proName
				+ ", proType=" + proType + ", unstandard=" + unstandard + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((proId == null) ? 0 : proId.hashCode());
		result = prime * result + ((proName == null) ? 0 : proName.hashCode());
		result = prime * result + ((proType == null) ? 0 : proType.hashCode());
		result = prime * result
				+ ((unstandard == null) ? 0 : unstandard.hashCode());
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
		ProductViewId other = (ProductViewId) obj;
		if (proId == null) {
			if (other.proId != null)
				return false;
		} else if (!proId.equals(other.proId))
			return false;
		if (proName == null) {
			if (other.proName != null)
				return false;
		} else if (!proName.equals(other.proName))
			return false;
		if (proType == null) {
			if (other.proType != null)
				return false;
		} else if (!proType.equals(other.proType))
			return false;
		if (unstandard == null) {
			if (other.unstandard != null)
				return false;
		} else if (!unstandard.equals(other.unstandard))
			return false;
		return true;
	}

	
    
}
