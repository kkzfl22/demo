package com.liujun.pattern.facade.facade;

public class LetterProcess implements ILetterProcess
{

	@Override
	public void writeContext(String context)
	{
		System.out.println("写入信件内容，你好:"+context);
	}

	@Override
	public void fillEnvelope(String address)
	{
		System.out.println("写入地址:"+address);
	}

	@Override
	public void letterInotoEnvelope()
	{
		System.out.println("把信放入信封中");
	}

	@Override
	public void sendLetter()
	{
		System.out.println("到邮局去发信");
	}

}
