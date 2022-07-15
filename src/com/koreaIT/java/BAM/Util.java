package com.koreaIT.java.BAM;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
	
	// 현재 날짜와 시간 리턴
	public static String getNowDateStr() {
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss");
		
		String regDate = now.format(formatter);
		return regDate;
		
		
	}
	
}
