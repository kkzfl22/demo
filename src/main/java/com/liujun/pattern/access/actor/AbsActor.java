package com.liujun.pattern.access.actor;

public abstract class AbsActor
{
	public void act(Role role)
	{
		System.out.println("演员可以演任何角色");
	}
	
	public void act(KongFuRole role)
	{
		System.out.println("演员可以演功夫角色");
	}
}
