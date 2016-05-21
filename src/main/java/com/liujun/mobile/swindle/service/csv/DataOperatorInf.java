package com.liujun.mobile.swindle.service.csv;

import java.io.InputStream;
import java.util.List;

import com.liujun.mobile.swindle.bean.RuleCsvBean;

/**
 * 文件导入导出接口
 * 
 * @author liujun
 * @date 2016年3月5日
 * @verion 0.0.1
 */
public interface DataOperatorInf
{
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
	@SuppressWarnings("rawtypes")
	public void writeCsvFile(String path, List<ParseCsvDataLineInf> soureList);

	/**
	 * 读取csv文件信息
	 * 
	 * @param path
	 *            路径 信息
	 * @return 返回读取的数据
	 */
	public List<RuleCsvBean> readCsvFile(String path);

	/**
	 * 读取csv文件信息
	 * 
	 * @param input
	 *            数据流信息
	 * @return 返回读取的数据
	 */
	public List<RuleCsvBean> readCsvFile(InputStream input);
}
