/**
 * 作者：卢斌晖
 * 2014-04-21
 */
package com.picc.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
	public static final long DAY_IN_MILLISECOND = 24 * 60 * 60 * 1000;

	public DateTimeUtil() {
	}

	public static HtmlOption[] getPeriodUnitOptions() {
		HtmlOption options[] = new HtmlOption[PERIODUNIT_CN.length];
		options[0] = new HtmlOption(PERIODUNIT_CN[0], "day");
		options[1] = new HtmlOption(PERIODUNIT_CN[1], "week");
		options[2] = new HtmlOption(PERIODUNIT_CN[2], "month");
		options[3] = new HtmlOption(PERIODUNIT_CN[3], "year");
		return options;
	}

	public static String getPeriodUnitCN(String periodUnit) {
		String temp[] = { "day", "week", "month", "year" };
		for (int i = 0; i < temp.length; i++)
			if (periodUnit.equalsIgnoreCase(temp[i]))
				return PERIODUNIT_CN[i];

		return periodUnit;
	}

	public static HtmlOption[] getTimeUnitOptions() {
		HtmlOption options[] = new HtmlOption[3];
		options[0] = new HtmlOption(TIMEUNIT_CN[0], "M");
		options[1] = new HtmlOption(TIMEUNIT_CN[1], "H");
		options[2] = new HtmlOption(TIMEUNIT_CN[2], "D");
		return options;
	}

	public static HtmlOption[] getMonthOptions() {
		HtmlOption options[] = new HtmlOption[12];
		for (int i = 0; i < 12; i++)
			options[i] = new HtmlOption(MONTH_CN[i], String.valueOf(i));

		return options;
	}

	public static HtmlOption[] getWeekOptions() {
		HtmlOption options[] = new HtmlOption[7];
		for (int i = 1; i < 7; i++)
			options[i - 1] = new HtmlOption(WEEK_CN[i], String.valueOf(i));

		options[6] = new HtmlOption(WEEK_CN[0], "0");
		return options;
	}

	public static HtmlOption[] getWeekNoOptions() {
		HtmlOption options[] = new HtmlOption[5];
		options[0] = new HtmlOption("\u7B2C\u4E00\u5468", "0");
		options[1] = new HtmlOption("\u7B2C\u4E8C\u5468", "1");
		options[2] = new HtmlOption("\u7B2C\u4E09\u5468", "2");
		options[3] = new HtmlOption("\u7B2C\u56DB\u5468", "3");
		options[4] = new HtmlOption("\u6700\u540E\u4E00\u5468", "4");
		return options;
	}

	public static HtmlOption[] getDayOptions() {
		HtmlOption options[] = new HtmlOption[31];
		String name = "";
		String value = "";
		for (int i = 1; i <= 31; i++) {
			name = String.valueOf(i);
			value = String.valueOf(i);
			options[i - 1] = new HtmlOption(name, value);
		}

		return options;
	}

	public static HtmlOption[] getHourOptions() {
		HtmlOption options[] = new HtmlOption[24];
		String name = "";
		String value = "";
		for (int i = 0; i < 24; i++) {
			if (i < 10)
				name = "0" + String.valueOf(i);
			else
				name = String.valueOf(i);
			value = String.valueOf(i);
			options[i] = new HtmlOption(name, value);
		}

		return options;
	}

	public static HtmlOption[] getMinuteOptions() {
		HtmlOption options[] = new HtmlOption[60];
		String name = "";
		String value = "";
		for (int i = 0; i < 60; i++) {
			if (i < 10)
				name = "0" + String.valueOf(i);
			else
				name = String.valueOf(i);
			value = String.valueOf(i);
			options[i] = new HtmlOption(name, value);
		}

		return options;
	}

	public static String getCurDate() {
		return formatDate(new Date());
	}

	public static String getCurDateTime() {
		return formatDateTime(new Date());
	}

	public static String formatDate(Date date) {
		if (null == date) {
			return "";
		} else {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			return df.format(date);
		}
	}

	public static String formatDateTime(Date date) {
		return formatDateTime(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String formatDateTime2Minute(Date date) {
		return formatDateTime(date, "yyyy-MM-dd HH:mm");
	}

	public static String formatDateTime(Date date, String format) {
		if (null == date) {
			return "";
		} else {
			DateFormat df = new SimpleDateFormat(format);
			return df.format(date);
		}
	}

	public static int getHour(Date date) {
		if (null == date)
			return 0;
		else
			return date.getHours();
	}

	public static int getMinute(Date date) {
		if (null == date)
			return 0;
		else
			return date.getMinutes();
	}

	public static Date getDate(String dateTime) {
		if (null == dateTime || dateTime.length() == 0)
			return null;
		DateFormat df = null;
		if (dateTime.length() == "yyyy-MM-dd HH:mm:ss".length())
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		else if (dateTime.length() == "yyyy-MM-dd HH:mm".length())
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		else if (dateTime.length() == "yyyy-MM-dd".length())
			df = new SimpleDateFormat("yyyy-MM-dd");
		else
			return null;
		try {
			return df.parse(dateTime);
		} catch (ParseException pe) {
			return null;
		}
	}

	public static String buildDateTime(String date, String hour, String minute) {
		if (null == date || date.length() == 0)
			return "";
		if (null == hour)
			return date;
		String hm = hour.length() != 1 ? hour : "0" + hour;
		if (null != minute) {
			hm = hm + ":";
			hm = hm + (minute.length() != 1 ? minute : "0" + minute);
		}
		return date + " " + hm;
	}

	public static Date getStartDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(11, 0);
		calendar.set(12, 0);
		calendar.set(13, 0);
		return calendar.getTime();
	}

	public static Date getEndDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(11, 23);
		calendar.set(12, 59);
		calendar.set(13, 59);
		return calendar.getTime();
	}

	public static Date addDay(Date date, int day) {
		return org.apache.commons.lang.time.DateUtils.addDays(date, day);
	}

	public static Date addWeek(Date date, int week) {
		return org.apache.commons.lang.time.DateUtils.addWeeks(date, week);
	}

	public static Date addMonth(Date date, int month) {
		return org.apache.commons.lang.time.DateUtils.addMonths(date, month);
	}

	public static Date addTime(Date date, int hours, int mins, int seconds) {
		Date d = org.apache.commons.lang.time.DateUtils.addHours(date, hours);
		d = org.apache.commons.lang.time.DateUtils.addMinutes(d, mins);
		return org.apache.commons.lang.time.DateUtils.addSeconds(d, seconds);
	}

	public static Date getPreMonthDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month - 1);
		return calendar.getTime();
	}

	public static Date getPreYearDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		calendar.set(Calendar.YEAR, year - 1);
		return calendar.getTime();
	}

    public static int get(Date date, int field)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(field);
    }
    
	public static Date parseDate(String dateString, String pattern) {
		try {
			return org.apache.commons.lang.time.DateUtils.parseDate(dateString,
					new String[] { pattern });
		} catch (Exception e) {
			return null;
		}
	}

	public static Date parseDate(String dateString, String[] patterns) {
		try {
			return org.apache.commons.lang.time.DateUtils.parseDate(dateString,
					patterns);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date newDate(int year, int month, int date) {
		return parseDate("" + year + month + date, month >= 10 ? "yyyyMMdd"
				: "yyyyMdd");
	}

	public static boolean isLeapYear(int year) {
		if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
			return true;
		}
		return false;
	}

	public static int getYearDays(int year) {
		if (isLeapYear(year)) {
			return 366;
		}
		return 365;
	}

	public static boolean isSameDay(Date date1, Date date2) {
		return org.apache.commons.lang.time.DateUtils.isSameDay(date1, date2);
	}

	public static Date truncate(Date d, int field) {
		return org.apache.commons.lang.time.DateUtils.truncate(d, field);
	}

	public static boolean isLastDayOfMonth(Date date) {
		return isFirstDayOfMonth(addDay(date, 1));
	}

	public static boolean isFirstDayOfMonth(Date date) {
		return get(date, Calendar.DAY_OF_MONTH) == 1;
	}
	
    public static Date getLastMonthDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();   
        calendar.set(Calendar.YEAR,year);   
        calendar.set(Calendar.MONTH,month - 1);   
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));   
        return calendar.getTime();   
    }   
       
    public static Date getLastMonthDay(Date date) {   
        Calendar calendar = Calendar.getInstance();   
        calendar.setTime(date);   
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));   
        return calendar.getTime();   
    }   
       
    public static Date getFirstMonthDay(Date date) {
        Calendar calendar = Calendar.getInstance();   
        calendar.setTime(date);   
        calendar.set(Calendar.DAY_OF_MONTH, 1);   
        return calendar.getTime();   
    }   
       
    public static Date getFirstMonthDay(int year, int month) {   
        Calendar calendar = Calendar.getInstance();   
        calendar.set(Calendar.YEAR,year);   
        calendar.set(Calendar.MONTH,month - 1);   
        calendar.set(Calendar.DAY_OF_MONTH, 1);   
        return calendar.getTime();   
    }   
       
    public static Date getFirstWeekDay(int year, int week) {   
        Calendar calendar = Calendar.getInstance();   
        calendar.set(Calendar.YEAR,year);   
        calendar.set(Calendar.WEEK_OF_YEAR, week);   
        calendar.set(calendar.DAY_OF_WEEK,1);   
        return addDay(calendar.getTime(), 1);   
    }   
       
    public static Date getFirstWeekDay(Date date) {   
        Calendar calendar = Calendar.getInstance();    
        calendar.setTime(date);   
        calendar.set(calendar.DAY_OF_WEEK,1);   
        return addDay(calendar.getTime(), 1);   
    }   
       
    public static Date getLastWeekDay(int year, int week) {   
        Calendar calendar = Calendar.getInstance();   
        calendar.set(Calendar.YEAR,year);   
        calendar.set(Calendar.WEEK_OF_YEAR, week);   
        calendar.set(calendar.DAY_OF_WEEK,7);   
        return addDay(calendar.getTime(), 1);   
    }   
       
    public static Date getLastWeekDay(Date date) {   
        Calendar calendar = Calendar.getInstance();   
        calendar.setTime(date);   
        calendar.set(calendar.DAY_OF_WEEK,7);   
        return addDay(calendar.getTime(), 1);   
    }   
       
    public static int getWeek(Date date) {   
        Calendar calendar = Calendar.getInstance();   
        calendar.setFirstDayOfWeek(Calendar.MONDAY);   
        calendar.setTime(date);   
        return calendar.get(Calendar.WEEK_OF_YEAR);   
    }   
       
    public static int getMonth(Date date) {   
        Calendar calendar = Calendar.getInstance();   
        calendar.setTime(date);   
        return calendar.get(Calendar.MONTH)+1;   
    }   
       
    public static int getYear(Date date) {   
        Calendar calendar = Calendar.getInstance();   
        calendar.setTime(date);   
        return calendar.get(Calendar.YEAR);   
    }   
       
    public static int getInterval(Date d1, Date d2) {   
        int betweenDays = 0;    
        Calendar c1 = Calendar.getInstance();    
        Calendar c2 = Calendar.getInstance();    
        c1.setTime(d1);    
        c2.setTime(d2);    
        // 保证第二个时间一定大于第一个时间    
        if(c1.after(c2)){    
        c1 = c2;    
        c2.setTime(d1);    
        }    
        int betweenYears = c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);    
        betweenDays = c2.get(Calendar.DAY_OF_YEAR)-c1.get(Calendar.DAY_OF_YEAR);    
        for(int i=0;i<betweenYears;i++){    
        c1.set(Calendar.YEAR,(c1.get(Calendar.YEAR)+1));   
        betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);    
        }   
           
        return betweenDays;   
    }   
       
    public static int getMonthInterval(Date startDate, Date endDate) {   
        int betweenMonths = 0;   
        Calendar c1 = Calendar.getInstance();    
        Calendar c2 = Calendar.getInstance();    
           
        c1.setTime(startDate);   
        c2.setTime(endDate);   
        // 保证第二个时间一定大于第一个时间    
        if(c1.after(c2)){    
            c1 = c2;    
            c2.setTime(startDate);    
        }    
           
        int y1 = c1.get(Calendar.YEAR);   
        int y2 = c2.get(Calendar.YEAR);   
           
        int m1 = c1.get(Calendar.MONTH);   
        int m2 = c2.get(Calendar.MONTH);   
           
        if (y2 > y1) {   
            betweenMonths += (y2 - y1) * 12;   
        }   
        betweenMonths += (m2 - m1);   
           
        return betweenMonths;   
    }   
       
    public static int getWeekInterval(Date startDate, Date endDate) {   
        int betweenWeeks = 0;   
        Calendar c1 = Calendar.getInstance();    
        Calendar c2 = Calendar.getInstance();    
           
        c1.setTime(startDate);   
        c2.setTime(endDate);   
        // 保证第二个时间一定大于第一个时间    
        if(c1.after(c2)){    
            c1 = c2;    
            c2.setTime(startDate);    
        }   
           
        int y1 = c1.get(Calendar.YEAR);   
        int y2 = c2.get(Calendar.YEAR);   
           
        int w1 = c1.get(Calendar.WEEK_OF_YEAR);   
        int w2 = c2.get(Calendar.WEEK_OF_YEAR);   
           
        betweenWeeks += (w2 - w1);   
        int betweenYears = y2 - y1;   
        for (int i = 0; i < betweenYears; i++) {   
            c1.set(Calendar.YEAR,(c1.get(Calendar.YEAR)+1));   
            betweenWeeks += c1.getMaximum(Calendar.WEEK_OF_YEAR);   
        }   
           
        return betweenWeeks;   
    }

	public static final int JANUARY = 0;

	public static final int FEBRUARY = 1;

	public static final int MARCH = 2;

	public static final int APRIL = 3;

	public static final int MAY = 4;

	public static final int JUNE = 5;

	public static final int JULY = 6;

	public static final int AUGUST = 7;

	public static final int SEPTEMBER = 8;

	public static final int OCTOBER = 9;

	public static final int NOVEMBER = 10;

	public static final int DECEMBER = 11;

	private static final String MONTH_CN[] = { "\u4E00\u6708", "\u4E8C\u6708",
			"\u4E09\u6708", "\u56DB\u6708", "\u4E94\u6708", "\u516D\u6708",
			"\u4E03\u6708", "\u516B\u6708", "\u4E5D\u6708", "\u5341\u6708",
			"\u5341\u4E00\u6708", "\u5341\u4E8C\u6708" };

	public static final int SUNDAY = 0;

	public static final int MONDAY = 1;

	public static final int TUESDAY = 2;

	public static final int WEDNESDAY = 3;

	public static final int THURSDAY = 4;

	public static final int FRIDAY = 5;

	public static final int SATURDAY = 6;

	private static final String WEEK_CN[] = { "\u661F\u671F\u5929",
			"\u661F\u671F\u4E00", "\u661F\u671F\u4E8C", "\u661F\u671F\u4E09",
			"\u661F\u671F\u56DB", "\u661F\u671F\u4E94", "\u661F\u671F\u516D" };

	public static final String TIMEUNIT_MINUTE = "M";

	public static final String TIMEUNIT_HOUR = "H";

	public static final String TIMEUNIT_DAY = "D";

	private static final String TIMEUNIT_CN[] = {"\u5206\u949F","\u5C0F\u65F6","\u5929"};

	public static final String PERIODUNIT_DAY = "day";

	public static final String PERIODUNIT_WEEK = "week";

	public static final String PERIODUNIT_MONTH = "month";

	public static final String PERIODUNIT_YEAR = "year";

	private static final String PERIODUNIT_CN[] = {"\u5929","\u5468","\u6708","\u5E74"};
}
