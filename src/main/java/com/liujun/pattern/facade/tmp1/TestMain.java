package com.liujun.pattern.facade.tmp1;

public class TestMain
{
	public static void main(String[] args)
	{
		ILetterProcess process = new LetterProcess();
		
		//1,写信
		process.writeContext("这是信件的内容。。。");
		
		process.fillEnvelope("上海。。");
		
		process.letterInotoEnvelope();
		
		process.sendLetter();
	}
}
