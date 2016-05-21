package com.liujun.io;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Assert;


public class TestGetTimes {

	private String sBeginDate = "2016-01-01 00:00:00";
	private String sPartionDay = "10";
	private String dateFormat = "yyyy-mm-dd hh:mm:ss";

	private static long beginDate;
	private long partionTime;

	private static final long oneDay = 86400000;

	public Long time() {
		try {
			beginDate = new SimpleDateFormat(dateFormat).parse(sBeginDate).getTime();
		} catch (ParseException e) {
			throw new java.lang.IllegalArgumentException(e);
		}
		partionTime = Integer.parseInt(sPartionDay) * oneDay;

		return partionTime;
	}

	public int calculate(String columnValue) {
		if (null == columnValue) {
			columnValue = "2016-02-03 00:00:00";
		}
		try {
			long targetTime = new SimpleDateFormat(dateFormat).parse(columnValue).getTime();
			int targetPartition = (int) ((targetTime - beginDate) / partionTime);
			return targetPartition;
		} catch (ParseException e) {
			throw new java.lang.IllegalArgumentException(e);
		}
	}

	public static void main(String[] args) {
		TestGetTimes time1 = new TestGetTimes();
		time1.time();
		System.out.println(time1.calculate(null));
		
		System.out.println(time1.calculate("2016-01-01 00:00:00"));
		System.out.println(time1.calculate("2016-01-10 00:00:00"));
		System.out.println(time1.calculate("2016-01-11 00:00:00"));
		System.out.println(time1.calculate("2016-01-22 00:00:00"));
		System.out.println( time1.calculate("2016-05-01 00:00:00"));


	}
}
