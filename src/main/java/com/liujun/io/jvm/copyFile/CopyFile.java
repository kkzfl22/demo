package com.liujun.io.jvm.copyFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFile
{

	public static void main(String[] args)
	{
		CopyFile copy = new CopyFile();

		String file = "D:\\java\\work\\demo\\framework\\demo\\src\\main\\java\\com\\liujun\\io\\jvm\\copyFile\\";

		copy.copyInputChar(file + "input1.txt", file + "output2.txt");
	}

	public void copyInputChar(String inputFile, String writeFile)
	{
		FileInputStream file = null;
		FileOutputStream output = null;
		try
		{
			file = new FileInputStream(inputFile);
			output = new FileOutputStream(writeFile);

			byte[] buffByte = new byte[512];

			int length = -1;

			while ((length = file.read(buffByte)) != -1)
			{
				output.write(buffByte, 0, length);
				// output.write(buffByte);
			}
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (null != output)
			{
				try
				{
					output.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}

			if (null != file)
			{
				try
				{
					file.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}

		}

	}
}
