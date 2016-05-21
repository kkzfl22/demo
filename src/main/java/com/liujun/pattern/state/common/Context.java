package com.liujun.pattern.state.common;

public class Context 
{
	
	/**
	 * 当前电梯状态
	 */
	private LiftState currLiftState;
	
	/**
	 * 电梯运行状态
	 */
	private LiftState runState = new RunLiftState();
	
	
	/**
	 * 电梯停止状态
	 */
	private LiftState stopState = new StopLiftState();
	
	
	/**
	 * 电梯开门状态
	 */
	private LiftState openDoorState = new OpeningState();
	
	
	/**
	 * 电梯关门状态
	 */
	private LiftState closeDoorState = new CloseLiftState();
	
	
	/**
	 * 突然断电状态
	 */
	private LiftState powerCutState = new PowerCutLiftState();
	
	
	


	public LiftState getRunState()
	{
		return runState;
	}


	public LiftState getStopState()
	{
		return stopState;
	}


	public LiftState getOpenDoorState()
	{
		return openDoorState;
	}


	public LiftState getCloseDoorState()
	{
		return closeDoorState;
	}
	

	public LiftState getPowerCutState()
	{
		return powerCutState;
	}


	public LiftState getCurrLiftState()
	{
		return currLiftState;
	}
	

	public void setCurrLiftState(LiftState currLiftState)
	{
		this.currLiftState = currLiftState;
		this.currLiftState.setContext(this);
	}
	
	
	
	public void openDoor()
	{
		this.currLiftState.openDoor();
	}

	public void closeDoor()
	{
		this.currLiftState.closeDoor();
	}

	public void run()
	{
		this.currLiftState.run();
	}

	public void stop()
	{
		this.currLiftState.stop();
	}

	public void powerCut()
	{
		this.currLiftState.powerCut();
	}
	
	
	
	
}
