package com.liujun.jvm.dataguru.third;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

public class UseLoad
{
	public static void main(String[] args)
	{
		UseLoad load = new UseLoad();
		load.load("load.txt", "D:/java/work/jvm/03/jar/", "D:\\java\\jdk\\jdk7\\jdk1.7.0_79\\jre\\lib\\rt.jar", "D:/java/work/jvm/03/ourput/jar/");
	}

	public void load(String name, String rtpath, String rtJdkPath, String writePathOut)
	{
		FileReader read = null;
		BufferedReader bufRead = null;

		String path = UseLoad.class.getClassLoader().getResource("com/liujun/jvm/dataguru/third/" + name).getPath();

		String line = null;

		boolean first = false;
		String[] value = null;

		try
		{
			read = new FileReader(path);
			bufRead = new BufferedReader(read);

			while ((line = bufRead.readLine()) != null)
			{
				if (!first)
				{
					first = true;
					continue;
				}
				line = line.replaceAll("]", "");
				value = line.split(" ");

				if (value.length >= 3 && rtJdkPath.equals(value[3]))
				{
					path = value[1].replaceAll("\\.", "/");

					// 读取对应的文件
					String soucePath = rtpath + path + ".class";
					String wrilePath = writePathOut + path + ".class";
					copyFile(soucePath, wrilePath);
				}
			}

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			IOUtils.closeQuietly(bufRead);
			IOUtils.closeQuietly(read);
		}

	}

	/**
	 * 拷贝文件
	 * 
	 * @param sourcePath
	 *            源文件目录
	 * @param outPath
	 *            目的文件目录
	 */
	private void copyFile(String sourcePath, String outPathFile)
	{
		InputStream in = null;
		OutputStream output = null;

		byte[] buff = new byte[512];
		int length = 0;
		try
		{
			//提取路径
			String outPath = outPathFile.substring(0,outPathFile.lastIndexOf("/"));
			
			File file = new File(outPath);
			if (!file.exists())
			{
				file.mkdirs();
			}
			in = new FileInputStream(sourcePath);
			output = new FileOutputStream(outPathFile, true);

			while ((length = in.read(buff)) != -1)
			{
				output.write(buff, 0, length);
			}

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			IOUtils.closeQuietly(output);
			IOUtils.closeQuietly(in);
		}

	}
}
