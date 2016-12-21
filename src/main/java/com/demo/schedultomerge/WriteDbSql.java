package com.demo.schedultomerge;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class WriteDbSql
{

	
	public static final String EnterLine = "\r\n";
	
	

	public static void main(String[] args) throws Exception
	{
		
		
		
		
		WriteDbSql write = new WriteDbSql();

		// String[] readFile = new String[]
		// { "[贵州]二期评分系统3G多维度数据汇聚", "[贵州]二期评分系统3G后台数据汇聚", "[贵州]二期评分系统4G后台数据汇聚",
		// "[贵州]二期评分系统4G多维度数据汇聚" };
		String[] readFile = new String[]
		// {
		// "[贵州]二期双11监控5分钟汇聚到15分钟并传送","[贵州]二期双11监控5分钟汇聚到60分钟关传送","[贵州]二期双11监控15汇聚后生成文件推送ftp","[贵州]二期双11监控60汇聚后生成文件推送ftp"
		// };
		{ "珠海大数据平台后台小时汇聚","珠海大数据平台后台天汇聚" };

		for (String file : readFile)
		{
			write.wireteData(file);
		}
		
		//write.wireteOneFolderData("建表语句");
		//write.wireteOneFolderData("公共语句");

		// 写入同一目录下所有文件
		// write.writeFileOne("D:\\java\\work\\schedule\\db\\后台脚本\\建表语句\\",
		// "D:\\java\\work\\schedule\\TestDb\\insert\\建表语句.txt");
		// write.writeFileOne("D:\\java\\work\\schedule\\db\\后台脚本\\公共语句",
		// "D:\\java\\work\\schedule\\TestDb\\insert\\公共语句.txt");

	}

	private void writeFileOne(String readPath, String writePath) throws Exception
	{
		List<String> rolistPath = this.getFileList(readPath);

		File file = new File(writePath);
		if (file.exists())
		{
			file.delete();
		}

		if (null != rolistPath && rolistPath.size() > 0)
		{

			for (String ps : rolistPath)
			{
				this.writeSql(ps, writePath);
			}
		}
	}

	public  void wireteOneFolderData(String folder) throws Exception
	{
		System.out.println("数据开始生成批定文件夹:");

		String rollpath = "D:\\java\\work\\schedule\\db\\后台脚本\\"+folder;
		// String rollwritePath = "D:\\java\\work\\schedule\\TestDb\\rollback\\"
		// + readFile + ".sql";
		String writePath = "D:\\java\\work\\schedule\\TestDb\\insert\\"+folder+".sql";

		List<String> rolistPath = this.getFileList(rollpath);

		File file = new File(writePath);
		if (file.exists())
		{
			file.delete();
		}

		if (null != rolistPath && rolistPath.size() > 0)
		{

			for (String ps : rolistPath)
			{
				this.writeSql(ps, writePath);
			}

		}

		System.out.println("数据生成完毕:..." );
	}

	private void wireteData(String readFile) throws Exception
	{
		System.out.println("数据开始生成:" + readFile);

		String rollpath = "D:\\java\\work\\schedule\\db\\后台脚本\\回滚语句\\" + readFile;
		// String rollwritePath = "D:\\java\\work\\schedule\\TestDb\\rollback\\"
		// + readFile + ".sql";
		String writePath = "D:\\java\\work\\schedule\\TestDb\\insert\\" + readFile + ".sql";

		List<String> rolistPath = this.getFileList(rollpath);

		File file = new File(writePath);
		if (file.exists())
		{
			file.delete();
		}

		if (null != rolistPath && rolistPath.size() > 0)
		{

			for (String ps : rolistPath)
			{
				this.writeSql(ps, writePath);
			}

		}

		String path = "D:\\java\\work\\schedule\\db\\后台脚本\\插入语句\\" + readFile;
		// String writePath = "D:\\java\\work\\schedule\\TestDb\\insert\\" +
		// readFile + ".sql";

		List<String> listPath = this.getFileList(path);

		// File file2 = new File(writePath);
		// if (file2.exists())
		// {
		// file2.delete();
		// }

		for (String ps : listPath)
		{
			this.writeSql(ps, writePath);
		}

		System.out.println("数据生成完毕:" + readFile);
	}

	public List<String> getFileList(String path)
	{
		List<String> result = null;

		File file = new File(path);

		if (file.exists())
		{
			result = new ArrayList<String>();
			for (String listItem : file.list())
			{
				result.add(path + "\\" + listItem);
			}

			Collections.sort(result);
		}

		return result;
	}

	public void writeSql(String rfile, String writeFile) throws Exception
	{
		FileInputStream read = null;
		FileOutputStream write = null;

		FileChannel redchannel = null;
		FileChannel wriChannel = null;

		try
		{
			read = new FileInputStream(rfile);
			write = new FileOutputStream(writeFile, true);

			redchannel = read.getChannel();
			wriChannel = write.getChannel();

			ByteBuffer buff = ByteBuffer.allocate(512);

			while (redchannel.read(buff) != -1)
			{
				buff.flip();
				wriChannel.write(buff);
				buff.clear();
			}

			buff.put(EnterLine.getBytes());
			buff.put(EnterLine.getBytes());
			buff.put(EnterLine.getBytes());
			buff.put(EnterLine.getBytes());
			buff.put(EnterLine.getBytes());
			buff.put(EnterLine.getBytes());
			buff.put(EnterLine.getBytes());
			buff.put(EnterLine.getBytes());
			buff.put(EnterLine.getBytes());
			buff.put(EnterLine.getBytes());
			buff.put(EnterLine.getBytes());

			buff.flip();
			wriChannel.write(buff);
			buff.clear();
			write.flush();
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		} finally
		{
			if (null != write)
			{
				write.close();
			}
			if (null != read)
			{
				read.close();
			}

		}
	}
}
