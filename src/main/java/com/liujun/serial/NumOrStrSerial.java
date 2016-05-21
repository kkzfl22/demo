package com.liujun.serial;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * 用数据来存储数据，减少数据占用
 * @author liujun
 *
 */
public class NumOrStrSerial
{

	/**
	 * 测试字符串在不同编码下的长度
	 * @throws UnsupportedEncodingException
	 */
	public void saveChar() throws UnsupportedEncodingException
	{
		String str = "1234567890";
		//String str = "ABCDEFGHIJK";
		
		System.out.println("字符串str长度:"+str.length());
		System.out.println(Charset.defaultCharset().name() + "长度:"+str.getBytes().length);
		System.out.println("unicode:"+str.getBytes("unicode").length);
		System.out.println("gbk:"+str.getBytes("gbk").length);
		System.out.println("gb2312:"+str.getBytes("gb2312").length);
		System.out.println("iso-8859-1:"+str.getBytes("iso-8859-1").length);
	}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException
	{
		
		NumOrStrSerial choose = new NumOrStrSerial();
		
		choose.saveChar();
		choose.saveNum();
		choose.saveStr();
	}
	
	
	/**
	 * 字符串通过转换为数字来保存
	 */
	public void saveNum()
	{
		
		String str = "1234567890";
		
		System.out.println();
		System.out.println("str 数字长度:"+str.length());
		System.out.println("long :" + ByteUtils.longtoBytes(Long.parseLong(str)).length);
		byte[] byArray = ByteUtils.longtoBytes(Long.parseLong(str));
		System.out.println("还原后的值:"+ByteUtils.bytesToLong(byArray));
	}
	
	
	/**
	 * 字符串通过转换为数字来保存
	 */
	public void saveStr()
	{
		String str = "ABCDEFGHIJK";
		
		System.out.println();
		System.out.println("str 数字长度:"+str.length());
		System.out.println("long :" + ByteUtils.strtoBytes(str).length);
		byte[] byArray = ByteUtils.strtoBytes(str);
		System.out.println("还原后的值:"+ByteUtils.bytetoStr(byArray));
	}
}
