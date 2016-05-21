package com.liujun.framework.xfire.bean;

public class Boday {
	
	/**
	 * 当前的msg
	 */
	private int msg;
	
	/**
	 * 信息
	 */
	private String rsc;

	public int getMsg() {
		return msg;
	}

	public void setMsg(int msg) {
		this.msg = msg;
	}

	public String getRsc() {
		return rsc;
	}

	public void setRsc(String rsc) {
		this.rsc = rsc;
	}

	@Override
	public String toString() {
		return "Boday [msg=" + msg + ", rsc=" + rsc + "]";
	}
	

}
