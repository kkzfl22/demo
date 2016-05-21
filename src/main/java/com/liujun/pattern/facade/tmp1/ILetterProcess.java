package com.liujun.pattern.facade.tmp1;

/**
 * 写信
 * 
 * @author liujun
 * 
 */
public interface ILetterProcess
{
	/**
	 * 写信件内容
	 * 
	 * @param context
	 */
	void writeContext(String context);

	/**
	 * 写信封
	 * 
	 * @param address
	 */
	void fillEnvelope(String address);

	/**
	 * 把信放入信封中
	 */
	void letterInotoEnvelope();

	/**
	 * 发送信
	 */
	void sendLetter();
}
