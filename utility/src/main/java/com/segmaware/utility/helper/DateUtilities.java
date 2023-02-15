/**
 * 
 */
package com.segmaware.utility.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.time.*;

/**
 * @author mohamed.hanafy
 *
 */
public class DateUtilities {

	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	
	public static boolean isThisDateValid(String dateToValidate){
		
		final Logger log = LoggerFactory.getLogger(DateUtilities.class);
		
		if(dateToValidate == null){
			return false;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		sdf.setLenient(false);
		
		try {
			
			//if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);
			
		
		} catch (ParseException e) {
			
			log.error("ParseException :::: ", e);
			return false;
		}
		
		return true;
	}
	
	public static String dateFormat(String date) throws ParseException {
		DateFormat inputFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT, Locale.US);
	
		return dateFormat(inputFormat.parse(date));
	}

	public static String dateFormat(String date, String inputFormat, String outputFormat) throws ParseException {
		DateFormat inputDateFormat = new SimpleDateFormat(inputFormat, Locale.US);
		return new SimpleDateFormat(outputFormat).format(date);
	}

	public static String dateFormat(String date, String inputFormat) throws ParseException {
		DateFormat inputDateFormat = new SimpleDateFormat(inputFormat, Locale.US);
		return dateFormat(inputDateFormat.parse(date));
	}
	public static String dateFormat(Date date) throws ParseException {
		return new SimpleDateFormat(DateUtilities.DEFAULT_DATE_FORMAT).format(date);
	}

	public static Date convertToDate(String date) throws ParseException {
		return convertToDate(date, DEFAULT_DATE_FORMAT);
	}

	public static Date convertToDate(String date, String format) throws ParseException {
		DateFormat inputFormat = new SimpleDateFormat(format);
		return inputFormat.parse(date);
	}
	
	/**
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * 1 date1 is before date2 <br>
	 * -1 date1 is after date2 <br>
	 * 0 date1 and date2 are equal <br>
	 */
	public static int compareTwoDates(Date date1, Date date2) {
		Date sDate = getZeroTimeDate(date1);
		Date eDate = getZeroTimeDate(date2);
		if (sDate.before(eDate)) {
			return 1;
		}
		if (sDate.after(eDate)) {
			return -1;
		}
		return 0;
	}

	public static Date getZeroTimeDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		date = calendar.getTime();
		return date;
	}
	
	public static Date increaseDateByHours(Date date, int hours) {
	    Calendar cal = Calendar.getInstance(); // creates calendar
	    cal.setTime(date); // sets calendar time/date
	    cal.add(Calendar.HOUR_OF_DAY, hours); // adds one hour
	    
		return cal.getTime();
	}

	public static Date increaseDateByDays(Date date, int days) {
		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTime(date); // sets calendar time/date
		cal.add(Calendar.DATE, days); // adds one hour

		return cal.getTime();
	}

	public static Date decreaseDateByHours(Date date, int hours) {
	    Calendar cal = Calendar.getInstance(); // creates calendar
	    cal.setTime(date); // sets calendar time/date
	    cal.add(Calendar.HOUR_OF_DAY, -hours); // adds one hour
	    
		return cal.getTime();
	}

	public static Date decreaseDateByDays(Date date, int days) {
		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTime(date); // sets calendar time/date
		cal.add(Calendar.HOUR_OF_DAY, -days); // adds one hour

		return cal.getTime();
	}
	
	public static Date calendarYear(int years) {
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		cal.add(Calendar.YEAR, years); // to get previous year add -1
		Date nextYear = cal.getTime();
		
		return nextYear;
	}

	public static Date todayStartOfDay(){
		Date date = new Date();
		Instant inst = date.toInstant();
		LocalDate localDate = inst.atZone(ZoneId.systemDefault()).toLocalDate();
		OffsetTime time = OffsetTime.now();
		LocalDateTime localDateTime = localDate.atTime(00, 00, 00, 00);
		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		Date todayMorning = Date.from(instant);
		return todayMorning;
	}


	public static Date dateStartOfDay(Date date){
		Instant inst = date.toInstant();
		LocalDate localDate = inst.atZone(ZoneId.systemDefault()).toLocalDate();
		OffsetTime time = OffsetTime.now();
		LocalDateTime localDateTime = localDate.atTime(00, 00, 00, 00);
		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		Date endOfDay = Date.from(instant);
		return endOfDay;
	}


	public static Date todayEndOfDay(){
		Date date = new Date();
		Instant inst = date.toInstant();
		LocalDate localDate = inst.atZone(ZoneId.systemDefault()).toLocalDate();
		OffsetTime time = OffsetTime.now();
		LocalDateTime localDateTime = localDate.atTime(23, 59, 59, 99);
		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		Date endOfDay = Date.from(instant);
		return endOfDay;
	}


	public static Date dateEndOfDay(Date date){
		Instant inst = date.toInstant();
		LocalDate localDate = inst.atZone(ZoneId.systemDefault()).toLocalDate();
		OffsetTime time = OffsetTime.now();
		LocalDateTime localDateTime = localDate.atTime(23, 59, 59, 99);
		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		Date endOfDay = Date.from(instant);
		return endOfDay;
	}

	public static boolean isValidScheduleDate(String date) throws ParseException {
		return isValidScheduleDate(convertToDate(date));
	}

	public static boolean isValidScheduleDate(Date date){
		if (date.compareTo(todayStartOfDay()) < 0)
			return false;
		return true;
	}
}
