package com.harry.tplugin.bean;

import java.io.Serializable;



public class SysMain implements Serializable
{ 
	private static final long serialVersionUID = 1957750604803691826L;
	private String sysId;
    private String content;
    private String className;
    private String command;

	public SysMain(){}
    
   
    public SysMain(String sysId, String content, String className, String command) {
		super();
		this.sysId = sysId;
		this.content = content;
		this.className = className;
		this.command = command;
	}


	public String getSysId() {
		return sysId;
	}


	public void setSysId(String sysId) {
		this.sysId = sysId;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	public String getCommand() {
		return command;
	}


	public void setCommand(String command) {
		this.command = command;
	}


	@Override
	public String toString() {
		return "SysMain [sysId=" + sysId + ", content=" + content + ", className="
				+ className + ", command=" + command + "]";
	}

}
