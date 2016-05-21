package com.liujun.framework.apachecommons;

import org.apache.commons.csv.CSVRecord;

/**
 * 转换csv文件行的接口信息
 * 
 * @author liujun
 * 
 */
public interface ParseCsvDataLineInf<T>
{
	
	/**
	 * 得到列头信息
	 * @return
	 */
	public String[] getColumn();

	/**
	 * 进行csv文件行数据转换
	 * 
	 * @return 将当前数据转换成数组信息
	 */
	public String[] parseCsvData();

	/**
	 * 将csv对象转化为javabean对象
	 *
	 * @param iter 进行遍历的迭代器对象
	 * @return l
	 */
	public T toDataBean(CSVRecord iter);

}
