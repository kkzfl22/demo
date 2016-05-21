package com.liujun.pattern.facade.facade;

/**
 * 邮差
 * @author liujun
 *
 */
public class ModenPostOffice
{
	private ILetterProcess letterProcess = new LetterProcess();
	
	private Police check = new Police();
	
	public void sendLetter(String address,String context)
	{
		this.letterProcess.writeContext(context);
		
		this.letterProcess.fillEnvelope(address);
		
		this.letterProcess.letterInotoEnvelope();
		
		this.check.checkLetter(this.letterProcess);
		
		this.letterProcess.sendLetter();
	}
}
