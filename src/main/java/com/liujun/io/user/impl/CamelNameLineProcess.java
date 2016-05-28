package com.liujun.io.user.impl;

import com.liujun.io.user.DataBaseTableToEntry;
import com.liujun.io.user.LineProcessInf;

/**
 * 使用命名法来进行转换数据
 * 
 * @author liujun
 * 
 * @date 2016年5月24日
 * @vsersion 0.0.1
 */
public class CamelNameLineProcess implements LineProcessInf {

	@Override
	public String processLine(String linename) throws Exception {

		String name = linename;
		while (name.indexOf("_") != -1) {
			String buffix = name.substring(0, name.indexOf("_"));
			String end = name.substring(name.indexOf("_") + 1).substring(0, 1).toUpperCase() + name.substring(name.indexOf("_") + 1).substring(1);

			name = buffix + end;
		}
		
		 name = "\t\t"+name + DataBaseTableToEntry.LINE;

		return name;
	}

}
