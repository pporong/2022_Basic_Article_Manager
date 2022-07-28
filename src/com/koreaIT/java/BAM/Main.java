package com.koreaIT.java.BAM;

public class Main {
	// 고객 Main => App <=> ArticleController <=> ArticleDao <=> articles
	// 고객 Main => App <=> MemberController <=> MemberDao <=> members
	
//										↓
										
	// 고객 Main => App <=> ArticleController <=> ArticleService <=> ArticleDao <=> articles
//	       시작     라우팅        고객 응대                실무팀             창고지기         창고
	// 고객 Main => App <=> MemberController <=> MemberService <=> MemberDao <=> members
	
	public static void main(String[] args) {
		new App().run();
	}
}