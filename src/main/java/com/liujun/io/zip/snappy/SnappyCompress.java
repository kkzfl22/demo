package com.liujun.io.zip.snappy;

import java.io.IOException;

import org.xerial.snappy.Snappy;

public class SnappyCompress
{
	public static void main(String[] args) throws IOException
	{
		String length = "1234567890    12";
		
		System.out.println(length.getBytes().length);
		
		byte[] compress = Snappy.compress(length);
		
		System.out.println(compress.length);
		
		byte[] compressRs = Snappy.uncompress(compress);
		
		String vs = new String(compressRs);
		
		System.out.println(vs);
	}
}
