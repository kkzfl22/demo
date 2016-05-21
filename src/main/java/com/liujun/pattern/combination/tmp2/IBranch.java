package com.liujun.pattern.combination.tmp2;

import java.util.List;

public interface IBranch extends ICorp
{
	/**
	 * 添加节点信息
	 * @param corp
	 */
	void add(ICorp corp);
	
	/**
	 * 获取子节点信息
	 * @return
	 */
	List<ICorp> getSubordinateInfo();
}
