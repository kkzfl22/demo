package com.liujun.pattern.access.actor;

/**
 * 普通角色
 * @author liujun
 *
 */
public class CommonRole implements Role
{

	@Override
	public void Accept(AbsActor actor)
	{
		actor.act(this);
	}

}
