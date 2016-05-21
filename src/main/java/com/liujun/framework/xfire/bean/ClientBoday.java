package com.liujun.framework.xfire.bean;

public class ClientBoday extends Boday {
	
	/**
	 * 显示信息
	 */
	private String showMsg;
	
	
	public String getShowMsg() {
		return showMsg;
	}

	public void setShowMsg(String showMsg) {
		this.showMsg = showMsg;
	}

	@Override
	public String toString() {
		return "ClientBoday [showMsg=" + showMsg + "]";
	}
	
}
