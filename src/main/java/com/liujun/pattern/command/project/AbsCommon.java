package com.liujun.pattern.command.project;

/**
 * 基本的命令操作
 * @author liujun
 *
 */
public abstract class AbsCommon
{
	/**
	 * 需求工程师实现
	 */
	private EngineerInf demandEnginner;
	
	/**
	 * 代码工程师
	 */
	private EngineerInf codeEnginner;
	
	/**
	 * 美术工程师实现
	 */
	private EngineerInf artenginner;
	
	
	/**
	 * 执行的命令
	 */
	protected abstract void exec();


	public AbsCommon(EngineerInf demandEnginner, EngineerInf codeEnginner, EngineerInf artenginner)
	{
		this.demandEnginner = demandEnginner;
		this.codeEnginner = codeEnginner;
		this.artenginner = artenginner;
	}


	public EngineerInf getDemandEnginner()
	{
		return demandEnginner;
	}


	public EngineerInf getCodeEnginner()
	{
		return codeEnginner;
	}


	public EngineerInf getArtenginner()
	{
		return artenginner;
	}
	
}
