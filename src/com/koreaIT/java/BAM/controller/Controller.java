package com.koreaIT.java.BAM.controller;

import com.koreaIT.java.BAM.dto.Member;

public abstract class Controller {
		
	public static Member loginedMember;
	
	public abstract void doAction(String cmd, String actionMethodName);

	public abstract void makeTestData();
	
	/* 로그인 여부 => O */
	public static boolean isLogined() {
		return loginedMember != null;
	}
	
	
	
}
