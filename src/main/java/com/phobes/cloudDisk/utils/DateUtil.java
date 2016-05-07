package com.phobes.cloudDisk.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author pangchao E-mail: pangchao620@163.com
 * @date : 2016�?1�?11�? 下午5:16:46
 * @Description : 日期类型解析�?
 * @version 1.0
 */
public class DateUtil {

	/**
	 * @Description:字符串转成日�? 格式// Fri Feb 24 00:00:00 CST 2012
	 * @exception:
	 */
	public static Date parseDate(String date) {
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate2 = null;
		try {
			myDate2 = dateFormat2.parse(date);
		} catch (ParseException e) {
			//
			e.printStackTrace();
			return null;
		}
		return myDate2;
	}

	/**
	 * @Description:字符串转成日�? 格式//2015-01-11
	 * @exception:
	 */
	public static Date stringToDate(String str) {
		// DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			// 2012-02-24
			date = java.sql.Date.valueOf(str);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * @Description:日期转为字符�?
	 * @exception:
	 */
	public static String DateToString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		return dateString;
	}

	// test
	public static void main(String[] args) {

		/*
		 * System.out.println(DateToString(new Date()));
		 * 
		 * Date date = parseDate("2016-01-22 13:48:341");
		 * System.out.println(date.getDate());
		 */

		// Date date = stringToDate("2016-01-11");
		// Date date = stringToDate("2016-01-22 13:48:34");
		// System.out.println(date.toString());

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date utilDate = new Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		java.sql.Time sTime = new java.sql.Time(utilDate.getTime());
		java.sql.Timestamp stp = new java.sql.Timestamp(utilDate.getTime());
		System.out.println(sqlDate);
		System.out.println(sTime);
		System.out.println(stp);
		System.out.println(f.format(sqlDate));
		System.out.println(f.format(sTime));
		System.out.println(f.format(sTime));
	}

}
