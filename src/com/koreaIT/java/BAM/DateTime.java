package com.koreaIT.java.BAM;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
	public static String regDate;

	public static void main(String[] args) {
		
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss");
			
			String regDate = now.format(formatter);
			

			System.out.println(regDate);
		
	}
}
