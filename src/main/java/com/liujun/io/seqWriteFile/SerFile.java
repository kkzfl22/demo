package com.liujun.io.seqWriteFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.commons.compress.utils.IOUtils;

public class SerFile
{
	/**
	 * 将java序列化对象写入到文件中
	 * @param path 文件路径
	 * @param bean 序列化的对象信息
	 */
	public void writeFile(String path,Serializable bean)
	{
		FileOutputStream output = null;
		ObjectOutputStream objectOutput = null;
		try
		{
			output = new FileOutputStream(path);
			objectOutput = new ObjectOutputStream(output);
			objectOutput.writeObject(bean);
			
			objectOutput.flush();
			
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}	
		finally
		{
			IOUtils.closeQuietly(objectOutput);
			IOUtils.closeQuietly(output);
		}
	}
	
	/**
	 * 读取序列化文件
	 * @param path 序列化文件路径
	 * @return 结果信息
	 */
	public Serializable readFile(String path)
	{
		Serializable bean = null;
		
		FileInputStream input = null;
		ObjectInputStream objInput = null;
		
		try
		{
			input = new FileInputStream(path);
			
			objInput = new ObjectInputStream(input);
			
			bean = (Serializable) objInput.readObject();
			
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			IOUtils.closeQuietly(objInput);
			IOUtils.closeQuietly(input);
		}
		
		return bean;
	}
}
