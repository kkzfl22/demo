package com.liujun.io.user;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class DataBaseTableToEntry {
	
	/**
	 * 换行符
	 */
	private static final String LINE = "\r\n";
	
	/**
	 * 读取文件将将列中的首字母大写
	 * @param path
	 */
	public void readToBig(String path)
	{
		FileReader read = null;
		BufferedReader bufRead = null;
		
		try {
			read = new FileReader(path);
			bufRead = new BufferedReader(read);
			String line = null;
			String[] column = null; 
			
			String out = null;
			String name = null;
			
			while((line = bufRead.readLine()) != null)
			{
				column = line.split("\t");
				
				out =   "\t" + "/**"+LINE;
				out +=  "\t" + " * " + column[0] +LINE;
				out +=  "\t" + " */"  +LINE;
				
				out += "\tprivate ";
				if("整数".equals(column[2]))
				{
					out += " int ";
				}
				else
				{
					out += " String ";
				}
				
				name = column[1];
				while(name.indexOf("_") != -1)
				{
					String buffix = name.substring(0,name.indexOf("_"));
					String end = name.substring(name.indexOf("_")+1).substring(0,1).toUpperCase() + name.substring(name.indexOf("_")+1).substring(1);
				
					name = buffix + end;
				}
				
				out += name+";"+LINE ;	
				
				System.out.println(out);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			IOUtils.closeQuietly(bufRead);
			IOUtils.closeQuietly(read);
		}
		
	}
	
	public static void main(String[] args) {
		String path = DataBaseTableToEntry.class.getClassLoader().getResource("com/liujun/io/user/").getPath() + "table.txt";
		
		DataBaseTableToEntry load = new DataBaseTableToEntry();
		load.readToBig(path);
		System.out.println();
	}

}
