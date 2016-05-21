package com.liujun.io.seqWriteFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class TestMain
{
	public static void main(String[] args)
	{
		long systemStart = System.nanoTime();
		BeanInfo info = new BeanInfo("李四", 18, "上海市徐汇区", "男", 170, "高级工程师");
		
		List<String> list = new ArrayList<String>();
		
		for (int i = 0; i < 1000000; i++)
		{
			list.add("当前的结果是::当前的结果是::当前的结果是::当前的结果是::"+i);
		}
		
		info.setList(list);
		
		SerFile ser = new SerFile();
		
		String path = TestMain.class.getClassLoader().getResource("./com/liujun/io/seqWriteFile/").getPath();
		
		System.out.println("路径:"+path);
		
		String filePath = path+"/ser/serinfo.ser";
		
		ser.writeFile(filePath, info);
		
		Serializable serFile = ser.readFile(filePath);
		
		System.out.println(serFile);
		
		long systemEnd = System.nanoTime();
		
		
		long endvalue = systemEnd - systemStart;
		
		System.out.println(endvalue);
		System.out.println(endvalue/1000/1000/1000+"秒");
		
	}
}
