package com.liujun.pattern.state.common;

public class TestMain
{
	
	
	public static void main(String[] args)
	{
		Context context = new Context();
		
		context.setCurrLiftState(new CloseLiftState());
		
		context.openDoor();
		
		context.closeDoor();
		
		context.run();
		
		context.stop();
		
		context.openDoor();
		
		context.powerCut();
		
		context.stop();
		
		
		
		
	}
}
