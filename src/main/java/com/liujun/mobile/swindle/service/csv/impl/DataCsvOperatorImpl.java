package com.liujun.mobile.swindle.service.csv.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import com.liujun.mobile.swindle.bean.RuleCsvBean;
import com.liujun.mobile.swindle.service.csv.DataOperatorInf;
import com.liujun.mobile.swindle.service.csv.ParseCsvDataLineInf;

/**
 * 进行csv文件的导入导出操作
 * 
 * @author liujun
 * @date 2016年3月5日
 * @verion 0.0.1
 */
public class DataCsvOperatorImpl implements DataOperatorInf
{

	/**
	 * 使用的编码
	 */
	private static final String FILE_ENCODE = "GBK";

	/**
	 * 定义分隔符
	 */
	private static final String NEW_LINE_SEPARATOR = "\r\n";

	/**
	 * 进行csv文件文件写入
	 * 
	 * @param path
	 *            路径信息
	 * @param cloumn
	 *            列信息
	 * @param soureList
	 *            写入的数据信息
	 */
	@SuppressWarnings({ "rawtypes" })
	public void writeCsvFile(String path, List<ParseCsvDataLineInf> soureList)
	{
		if (null == soureList || soureList.isEmpty())
		{
			return;
		}

		BufferedWriter writebuf = null;
		CSVPrinter csvWrite = null;

		CSVFormat csvFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
		try
		{
			writebuf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), FILE_ENCODE));
			csvWrite = new CSVPrinter(writebuf, csvFormat);

			List<String> userDataRecord = new ArrayList<String>();

			ParseCsvDataLineInf item = null;

			for (int i = 0; i < soureList.size(); i++)
			{
				item = soureList.get(i);

				if (i == 0)
				{
					// 写入列信息,仅写一次，需在第一次的时候写入
					csvWrite.printRecord(item.getColumn());
				}

				// 普通bean对象转化为需要写入的数据格式
				String[] csvdata = item.parseCsvData();

				if (null != csvdata && csvdata.length > 0)
				{
					for (int j = 0; j < csvdata.length; j++)
					{
						userDataRecord.add(csvdata[j]);
					}
				}

				// 将文件写入csv
				csvWrite.printRecord(userDataRecord);

				// 清空写入队列
				userDataRecord.clear();

				if (i % 10000 == 0)
				{
					csvWrite.flush();
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				// 释放流
				csvWrite.close();
				writebuf.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * 读取csv文件信息
	 * 
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 */
	public List<RuleCsvBean> readCsvFile(String path)
	{
		List<RuleCsvBean> list = null;
		try
		{
			list = this.readCsvFile(new FileInputStream(path));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	public static void main(String[] args)
	{
		long start = System.currentTimeMillis();

		DataCsvOperatorImpl csvOper = new DataCsvOperatorImpl();

		List<ParseCsvDataLineInf> list = new ArrayList<ParseCsvDataLineInf>();

		for (int i = 0; i < 1000; i++)
		{
			RuleCsvBean user = new RuleCsvBean();

			user.setType("username:" + i);
			user.setRuleinfo("pwd:" + i);

			list.add(user);
		}

		csvOper.writeCsvFile("D:\\java\\test\\userinfo.csv", list);

		long end = System.currentTimeMillis();

		System.out.println((end - start) / 1000f);
		// 进行读取操作
		List<RuleCsvBean> listCsv = csvOper.readCsvFile("d:\\java\\test\\userinfo.csv");

		System.out.println(listCsv.size());

		long end2 = System.currentTimeMillis();

		System.out.println((end2 - start) / 1000f);

	}

	@Override
	public List<RuleCsvBean> readCsvFile(InputStream input)
	{

		List<RuleCsvBean> list = null;

		// 创建CSVFormat（header mapping）
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(NEW_LINE_SEPARATOR);

		BufferedReader read = null;

		CSVParser csvparse = null;

		try
		{
			// 读取文件
			read = new BufferedReader(new InputStreamReader(input, FILE_ENCODE));

			// 转化为csv文件
			csvparse = new CSVParser(read, csvFileFormat);

			// 将csv文件转为集体
			List<CSVRecord> csvRecords = csvparse.getRecords();

			if (null != csvRecords && csvRecords.size() > 1)
			{
				list = new ArrayList<RuleCsvBean>();

				ParseCsvDataLineInf<RuleCsvBean> userinfo = new RuleCsvBean();

				System.out.println("数据条数:" + csvRecords.size());

				for (int i = 0; i < csvRecords.size(); i++)
				{
					// 需跳过第一行记录，为表头
					if (i > 0)
					{
						// 转化行数据，并放入集合
						list.add(userinfo.toDataBean(csvRecords.get(i)));
					}
				}
			}

		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (null != csvparse)
			{
				try
				{
					csvparse.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if (null != read)
			{
				try
				{
					read.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return list;

	}
}
