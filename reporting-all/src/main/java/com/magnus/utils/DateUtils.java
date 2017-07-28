package com.magnus.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static final String DATE_FORMAT_UNDERLINE = "yyyy_MM_dd";
	public static final String DATE_TIME_FORMAT_UNDERLINE = "yyyy_MM_dd_HH_mm";
	public static final String DATE_FORMAT_DASH = "yyyy-MM-dd";
	public static final String DATE_TIME_FORMAT_DASH = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT = "yyyy/MM/dd";
	public static final String DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm";
	public static final String DATE_TIME_FORMAT_WITH_SECONDS = "yyyy/MM/dd HH:mm:ss";
	private static SimpleDateFormat formatter;

	static {
		formatter = new SimpleDateFormat();
	}

	// * FORMAT FUNCTIONS: date -> string
	
	private static String format(String pattern, Date date) {
		formatter.applyPattern(pattern);
		return formatter.format(date);
	}

	public static String formatInDateFormatWithDash(Date date) {
		return format(DATE_FORMAT_DASH, date);
	}

	public static String formatInDateTimeFormatWithDash(Date date) {
		return format(DATE_TIME_FORMAT_DASH, date);
	}

	public static String formatInDateFormatWithUnderline(Date date) {
		return format(DATE_FORMAT_UNDERLINE, date);
	}

	public static String formatInDateTimeFormatWithUnderline(Date date) {
		return format(DATE_TIME_FORMAT_UNDERLINE, date);
	}

	public static String formatInDateFormat(Date date) {
		return format(DATE_FORMAT, date);
	}

	public static String formatInDateTimeFormat(Date date) {
		return format(DATE_TIME_FORMAT, date);
	}

	public static String formatInDateTimeFormatWithSeconds(Date date) {
		return format(DATE_TIME_FORMAT_WITH_SECONDS, date);
	}


	// * PARSE FUNCTIONS: string -> date

	private static Date parse(String pattern, String date) throws ParseException {
		formatter.applyPattern(pattern);
		return formatter.parse(date);
	}

	public static Date parseInDateFormatWithDash(String date) throws ParseException {
		return parse(DATE_FORMAT_DASH, date);
	}

	public static Date parseInDateTimeFormatWithDash(String date) throws ParseException {
		return parse(DATE_TIME_FORMAT_DASH, date);
	}

	public static Date parseInDateFormatWithUnderline(String date) throws ParseException {
		return parse(DATE_FORMAT_UNDERLINE, date);
	}

	public static Date parseInDateTimeFormatWithUnderline(String date) throws ParseException {
		return parse(DATE_TIME_FORMAT_UNDERLINE, date);
	}

	public static Date parseInDateFormat(String date) throws ParseException {
		return parse(DATE_FORMAT, date);
	}

	public static Date parseInDateTimeFormat(String date) throws ParseException {
		return parse(DATE_TIME_FORMAT, date);
	}

	public static Date parseInDateTimeFormatWithSeconds(String date) throws ParseException {
		return parse(DATE_TIME_FORMAT_WITH_SECONDS, date);
	}


}