package com.kayrin.checklist.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
public class DateTimeUtil {
	
	public static final String DATE_TIME_DATE_FORMAT = "yyyy-MM-dd'T'HH:mmZ";
	
	public static final String DATE_ONLY_DATE_FORMAT = "yyyy-MM-dd"; 
	
	public static final String DATE_DISPLAY_DATE_FORMAT = "E, d MMM yyyy"; 
	
	public static final TimeZone DEFAULT_TIME_ZONE = TimeZone.getTimeZone("NZ"); 
	
	private DateFormat dateOnlyFormat; 
	private DateFormat dateTimeFormat; 
	private DateFormat displayDateFormat; 
	
	public DateTimeUtil(){
		dateTimeFormat = new SimpleDateFormat(DATE_TIME_DATE_FORMAT);
		dateOnlyFormat = new SimpleDateFormat(DATE_ONLY_DATE_FORMAT);
		displayDateFormat = new SimpleDateFormat(DATE_DISPLAY_DATE_FORMAT); 
		dateOnlyFormat.setTimeZone(DEFAULT_TIME_ZONE);
		dateTimeFormat.setTimeZone(DEFAULT_TIME_ZONE);
		displayDateFormat.setTimeZone(DEFAULT_TIME_ZONE);
	}
	
	
	public String getFormattedCurrentDateTime(){		
		return getFormattedDate(new Date());		
	}
	
	public String getFormattedCurrentDate(){		
		return dateOnlyFormat.format(new Date());		
	}
	
	public String getFormattedDate(Date date){		
		return dateTimeFormat.format(date);	
	}
	
	public String getFormattedCurrentDisplayDate(){
		return displayDateFormat.format(new Date());
	}
	
}
