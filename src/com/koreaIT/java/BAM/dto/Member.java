package com.koreaIT.java.BAM.dto;

public class Member extends Dto {
	
	public int id;
	public String regDate;
	public String userName;
	public String loginId;
	public String loginPw;
	
	public Member(int id, String regDate, String userName, String loginId, String loginPw) {
		this.id = id;
		this.regDate = regDate;
		this.userName = userName;
		this.loginId = loginId;
		this.loginPw =loginPw;
	}
	
}


