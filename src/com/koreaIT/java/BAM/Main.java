package com.koreaIT.java.BAM;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);
		int lastArticleId = 0;

		while (true) {

			System.out.printf("명령어 : ");
			String cmd = sc.nextLine();

			// 명령어 미입력시
			if (cmd.length() == 0) {
				System.out.println("!! 명령어를 입력 해 주세요 !!");
				continue;
			}

			// 종료시
			if (cmd.equals("exit")) {
				break;
			}

			// 게시글 작성 메서드
			if (cmd.equals("article write")) {

				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				int id = lastArticleId + 1;
				lastArticleId = id;

				System.out.printf("%d번 글이 생성되었습니다.\n", id);

			 } // 게시글 목록
			  else if (cmd.equals("article list")) {
				System.out.println("게시글이 없습니다.");
			 } // 그 외
			  else {
				System.out.println("존재하지 않는 명령어 입니다. :(");
			 }
			
			

		}

		System.out.println("== 프로그램 종료 ==");
		sc.close();

	}

}

class Article {
	int id;
	String title;
	String body;

	public Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}

}
