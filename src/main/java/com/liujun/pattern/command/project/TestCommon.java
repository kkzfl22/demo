package com.liujun.pattern.command.project;

import java.util.ArrayList;
import java.util.List;

/**
 * 命令模式分为两部分
 * 1，命令接受者，即最终执行命令人或者物
 * 
 * 2,命令传递者，负责将命令拆分发送给不同的接受者
 * 
 * 
 * 
 * 
 * @author liujun
 *
 */
public class TestCommon
{
	
	public static void main(String[] args)
	{
		//执行对象
		EngineerInf demandEnginner = new DemandEngineer();
		EngineerInf codeEnginner = new CodeEngineer();
		EngineerInf artenginner = new ArtEngineer();
		
		//1,创建添加页面的命令
		AddPageCommand common = new AddPageCommand(demandEnginner,codeEnginner,artenginner);
		//进行删除页面的命令
		DelPageCommon common1 = new DelPageCommon(demandEnginner,codeEnginner,artenginner);

		//现在发布命令
		InvokeService invokde = new InvokeService();
		
		//进行多命令执行
		List<AbsCommon> commList = new ArrayList<AbsCommon>();
		
		commList.add(common);
		commList.add(common1);
		
		//为发布命令对象命令执行对象
		invokde.setCommon(commList);
		
		//开始执行
		invokde.action();
		
	}
	
}
