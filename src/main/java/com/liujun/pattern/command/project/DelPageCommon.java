package com.liujun.pattern.command.project;

/**
 * 删除命令
 * @author liujun
 *
 */
public class DelPageCommon extends AbsCommon
{
	

	public DelPageCommon(EngineerInf demandEnginner, EngineerInf codeEnginner, EngineerInf artenginner)
	{
		super(demandEnginner, codeEnginner, artenginner);
	}

	@Override
	protected void exec()
	{
		//1,获得需求人员
		super.getDemandEnginner().find("需求人员2");
		
		//2,获得需求人员计划
		super.getDemandEnginner().play("针对删除某一功能页面");
		
		//在需求中删除
		super.getDemandEnginner().delete("删除某一功能页面");
		
		//删除不需要进行美工调整
		super.getCodeEnginner().delete("删除某一功能页面");
	}

}
