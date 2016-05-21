package com.liujun.pattern.bridge.pattern1;

import java.util.Map;

/**
 * 产品接口
 * @author liujun
 *
 */
public interface ProductInf
{
	/**
	 * 获得产品信息
	 * @return 
	 */
	public Map<String,String> getProductInfo();
	
	/**
	 * 创建产品
	 */
	public void makeProductInfo();
}
