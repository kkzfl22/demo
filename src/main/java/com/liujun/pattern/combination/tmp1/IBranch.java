package com.liujun.pattern.combination.tmp1;

import java.util.List;

/**
 * 树枝节点对象
 * @author liujun
 *
 */
public interface IBranch
{
	/**
	 * 获取信息
	 * @return
	 */
	String getInfo();
	
	/**
	 * 添加树枝节点
	 * @param branch
	 */
	void add(IBranch branch);
	
	/**
	 * 添加树枝节点
	 * @param leaf
	 */
	void add(ILeaf leaf);
	
	/**
	 * 获取子节点信息
	 * @return
	 */
	List getSubordinateInfo();

}
