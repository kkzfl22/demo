package com.liujun.pattern.access.actor;

/**
 * 基本的角色信息
 * @author liujun
 *
 */
public interface Role
{
	/**
	 * 学员可以演的角色
	 * @param actor
	 */
	public void Accept(AbsActor actor);
}
