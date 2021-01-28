package com.easyworks.smartekp.common.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomDateTime {
	public static String getCurrentDateTime() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	
	public static String getCurrentDate() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	public static String getCurrentTime() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
	
	public static String getCurrentTimeHourMinute() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
	}
}
