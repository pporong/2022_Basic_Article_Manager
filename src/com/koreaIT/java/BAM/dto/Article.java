package com.koreaIT.java.BAM.dto;

public class Article extends Dto {
	public int id;
	public String title;
	public String body;
	public String regDate;
	// 조회수
	public int hit;
	public int memberId; // 신짱구 남도일 최자두
	
	public Article(int id, String regDate, int memberId, String title, String body) {
		this(id, regDate, memberId, title, body, 0);
	}
	
	// 인자, 매개변수
	public Article(int id, String regDate, int memberId, String title, String body, int hit) {
		this.id = id;
		this.regDate = regDate;
		this.memberId = memberId;
		this.title = title;
		this.body = body;
		this.hit = hit;
		
	}
	
	public void increaseHit() {
		hit ++;
	}

}
