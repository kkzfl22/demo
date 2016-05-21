package com.som.user.userjdbc.console;

/**
 * 进行公共的参数配制信息
 * 
 * @author liujun
 * @date 2016年4月18日
 * @verion 0.0.1
 */
public enum CfgEnum
{
	;

	/**
	 * 参与配制的key
	 */
	private String key;

	private CfgEnum(String key)
	{
		this.key = key;
	}

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

}
