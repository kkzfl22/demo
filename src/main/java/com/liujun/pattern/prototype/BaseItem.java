package com.liujun.pattern.prototype;

import java.io.Serializable;

public class BaseItem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 公用对象
	 */
	private String baseName;

	
	/**
	 * 基本年龄信息
	 */
	private int baseAge;


	public String getBaseName() {
		return baseName;
	}


	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}


	public int getBaseAge() {
		return baseAge;
	}


	public void setBaseAge(int baseAge) {
		this.baseAge = baseAge;
	}


	@Override
	public String toString() {
		return "BaseItem [baseName=" + baseName + ", baseAge=" + baseAge + "]";
	}
	
	
	
}
