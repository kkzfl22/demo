package com.liujun.pattern.access.actor;

public class TestMain
{
	public static void main(String[] args)
	{
		// AbsActor actor = new OldActor();
		//
		// Role role = new KongFuRole();
		//
		// actor.act(role);
		// actor.act(new KongFuRole());
		
		//初始化演员
		AbsActor actor2 = new OldActor();

		//初始化角色
		Role role2 = new CommonRole();
		
		//演员演角色
		role2.Accept(actor2);
	}
}
