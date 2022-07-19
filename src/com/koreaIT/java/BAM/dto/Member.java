package com.koreaIT.java.BAM.dto;

public class Member extends Dto {
	
	public int id;
	public String regDate;
	public String loginId;
	public String loginPw;
	public String userName;
	
	public Member(int id, String regDate, String loginId, String loginPw, String userName) {
		this.id = id;
		this.regDate = regDate;
		this.loginId = loginId;
		this.loginPw =loginPw;
		this.userName = userName;
	}
	
}


