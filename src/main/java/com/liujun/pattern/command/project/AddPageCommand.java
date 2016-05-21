package com.liujun.pattern.command.project;

/**
 * 添加页面需求
 * 
 * @author liujun
 * 
 */
public class AddPageCommand extends AbsCommon
{

	
	public AddPageCommand(EngineerInf demandEnginner, EngineerInf codeEnginner, EngineerInf artenginner)
	{
		super(demandEnginner, codeEnginner, artenginner);
	}

	@Override
	protected void exec()
	{
		super.getDemandEnginner().find("需求人员1");
		// 1,找到需求工程师做计划
		super.getDemandEnginner().play("需求人员1");

		// 2,需求人员添加需求
		super.getDemandEnginner().add("现在添国一个功能叫添加用户需求");

		// 3,找到美工添加页面
		super.getArtenginner().add("美工添加用户需求页面");
		
		//4,由码农负责完成
		super.getCodeEnginner().add("添加用户功能");
	}

}
