package com.harry.tplugin.bean;

import java.io.Serializable;


public class Login implements Serializable{
	private static final long serialVersionUID = 7424971275984173507L;
	private String userName;
	private String password;
	private String ipAdress;
	private String powerCode;
	
	
	public Login() {
		super();
	}
	public Login(String userName, String password, String ipAdress,
			String powerCode) {
		super();
		this.userName = userName;
		this.password = password;
		this.ipAdress = ipAdress;
		this.powerCode = powerCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIpAdress() {
		return ipAdress;
	}
	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}
	public String getPowerCode() {
		return powerCode;
	}
	public void setPowerCode(String powerCode) {
		this.powerCode = powerCode;
	}
	@Override
	public String toString() {
		return "Login [userName=" + userName + ", password=" + password
				+ ", ipAdress=" + ipAdress + ", powerCode=" + powerCode + "]";
	}
	
	
}
