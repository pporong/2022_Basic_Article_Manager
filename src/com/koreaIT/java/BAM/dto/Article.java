package com.koreaIT.java.BAM.dto;

public class Article extends Dto {
	public int id;
	public String title;
	public String body;
	public String regDate;
	// 조회수
	public int hit;
	
	public Article(int id, String regDate, String title, String body) {
		this(id, regDate, title, body, 0);
	}
	

	// 인자, 매개변수
	public Article(int id, String regDate, String title, String body, int hit) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.hit = hit;
		
	}
	
	public void increaseHit() {
		hit ++;
	}

}
