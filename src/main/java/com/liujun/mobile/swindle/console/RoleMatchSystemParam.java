package com.liujun.mobile.swindle.console;

/**
 * 匹配规则参数信息
 * 
 * @author liujun
 * @date 2016年3月5日
 * @verion 0.0.1
 */
public enum RoleMatchSystemParam
{

	/**
	 * 1:主叫主动介绍
	 */
	ROLE_KEY_TYPE_ONE("1"),

	/**
	 * 2：主叫主动介绍
	 */
	ROLE_KEY_TYPE_TWO("2"),

	/**
	 * 3：广告推销
	 */
	ROLE_KEY_TYPE_THIRD("3"),

	;

	/**
	 * 参数
	 */
	private String key;

	private RoleMatchSystemParam(String key)
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
