package com.liujun.pattern.access.actor;

/**
 * 功夫角色
 * @author liujun
 *
 */
public class KongFuRole implements Role
{

	@Override
	public void Accept(AbsActor actor)
	{
		//功夫角色
		actor.act(this);
	}

}
