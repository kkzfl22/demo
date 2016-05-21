package com.liujun.serial;

import java.nio.ByteBuffer;


public class ByteUtils
{
	/**
	 * 将long类型数字转化为bytes数组
	 * @param x
	 * @param length
	 * @return
	 */
	public static byte[] longtoBytes(long x)
	{
		ByteBuffer longbuffer = ByteBuffer.allocate(8);
		longbuffer.putLong(0,x);
		return longbuffer.array();
	}
	
	public static long bytesToLong(byte[] bytes)
	{
		return bytesToLong(bytes, 0, bytes.length);
	}
	
	public static long bytesToLong(byte[] bytes,int offset,int length)
	{
		ByteBuffer longbuffer = ByteBuffer.allocate(length);
		longbuffer.put(bytes,offset,length);
		longbuffer.flip();
		return longbuffer.getLong();
	}
	
	
	public static byte[] strtoBytes(String x)
	{
		ByteBuffer longbuffer = ByteBuffer.allocate(x.length());
		longbuffer.put(x.getBytes());
		return longbuffer.array();
	}
	
	public static String bytetoStr(byte[] array)
	{
		ByteBuffer longbuffer = ByteBuffer.allocate(array.length);
		longbuffer.put(array,0,array.length);
		longbuffer.flip();
		return new String(longbuffer.array());
	}
}
