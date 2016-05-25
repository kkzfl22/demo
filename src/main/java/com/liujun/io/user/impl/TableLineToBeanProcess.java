package com.liujun.io.user.impl;

import com.liujun.io.user.DataBaseTableToEntry;
import com.liujun.io.user.LineProcessInf;

/**
 * 进行数据库描述文档中的行数据到java实体的转换
 * 
 * @author liujun
 * 
 * @date 2016年5月24日
 * @vsersion 0.0.1
 */
public class TableLineToBeanProcess implements LineProcessInf {

	/**
	 * 输出信息
	 */
	private String out = null;

	/**
	 * 输出的名字信息
	 */
	private String name = null;

	/**
	 * 列信息
	 */
	private String[] column = null;

	@Override
	public String processLine(String line) throws Exception {

		column = line.split("\t");

		out = "\t" + "/**" + DataBaseTableToEntry.LINE;
		out += "\t" + " * " + column[0] + DataBaseTableToEntry.LINE;
		out += "\t" + " */" + DataBaseTableToEntry.LINE;

		out += "\tprivate ";
		if ("整数".equals(column[2])) {
			out += " int ";
		} else {
			out += " String ";
		}

		name = column[1];
		while (name.indexOf("_") != -1) {
			String buffix = name.substring(0, name.indexOf("_"));
			String end = name.substring(name.indexOf("_") + 1).substring(0, 1).toUpperCase() + name.substring(name.indexOf("_") + 1).substring(1);

			name = buffix + end;
		}

		out += name + ";" + DataBaseTableToEntry.LINE;

		return out;
	}

}
