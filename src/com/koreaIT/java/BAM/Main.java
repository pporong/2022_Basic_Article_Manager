package com.koreaIT.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);
		int lastArticleId = 0;

		List<Article> articles = new ArrayList<>();
//		LocalDateTime regDate = LocalDateTime.now();

		while (true) {

			System.out.printf("명령어 : ");
			String cmd = sc.nextLine().trim();

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
				int id = lastArticleId + 1;
				lastArticleId = id;

				System.out.println("< 게시글 작성 >");
				System.out.printf("제목 : ");
				String title = sc.nextLine();
//				System.out.printf("작성자 : ");
//				String writer = sc.nextLine();
//				System.out.println(DateTime.regDate);
				String regDate = Util.getNowDateStr();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, regDate, title, body);
				articles.add(article);

				System.out.printf("%d번 글이 등록되었습니다. :)\n", id);
			}
			// 게시글 목록
			else if (cmd.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다. :(");
					continue;
				}
				System.out.println("* 번호  | 조회수 |        작성일        |  제목  *");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("  %d번   |  %d  | %s | %s \n", 
							article.id, article.hit, article.regDate, article.title);
				}

			}
			// 게시글 상세보기
			else if (cmd.startsWith("article detail ")) {

				String[] cmdBits = cmd.split(" ");
				// cmdBits[0]; -> article
				// cmdBits[1]; -> detail
				// cmdBits[2]; -> ~

				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					// 0,1,2,3,4 ```
					Article article = articles.get(i);

					if (article.id == id) {
						foundArticle = article;

						foundArticle.increaseHit();
						
						// 상세보기 할 게시글이 있을 때
						System.out.printf("[ %d번 게시글에 대한 정보입니다. ]\n", foundArticle.id);
						System.out.printf("* 번호 : %d번 \n", foundArticle.id);
						System.out.printf("* 조회 : %d번 \n", foundArticle.hit);
//						System.out.printf("* 작성자 : %s \n", foundArticle.writer);
						System.out.printf("* 날짜 : %s \n", foundArticle.regDate);
						System.out.printf("* 제목 : %s \n", foundArticle.title);
						System.out.printf("* 내용 : %s \n", foundArticle.body);
						break;
					}

				}
				// 상세보기 할 게시글이 없을 때
				if (foundArticle == null) {
					System.out.printf("%d번 게시글은 존재하지 않습니다. :( \n", id);
					continue;
				}

			}
			// 게시글 수정
			else if (cmd.startsWith("article modify ")) {

				String[] cmdBits = cmd.split(" ");

				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						foundArticle = article;
						
						// 수정 할 게시글이 있을 때
						System.out.printf("[ %d번 게시글에 대한 정보입니다. ] \n", foundArticle.id);
						System.out.printf("* 현재 %d번 게시글 제목 : %s \n", foundArticle.id, foundArticle.title);
						System.out.println("수정 할 제목을 입력 해 주세요.");
						System.out.printf("-> 제목 : ");
						String title = sc.nextLine();
						System.out.printf("* 현재 %d번 게시글 내용 : %s \n", foundArticle.id, foundArticle.body);
						System.out.println("수정 할 내용을 입력 해 주세요.");
						System.out.printf("*-> 내용 : ");
						String body = sc.nextLine();
						
						// 덮어쓰기
						foundArticle.title = title;
						foundArticle.body = body;
						
						System.out.printf("%d번 게시물 수정이 완료되었습니다 :) \n", foundArticle.id);
						break;
					}
				}
				// 수정 할 게시글이 없을 때
				if (foundArticle == null) {
					System.out.printf("%d번 게시글은 존재하지 않습니다. :( \n", id);
					continue;
				}

			}

			// 게시글 삭제
			else if (cmd.startsWith("article delete ")) {

				String[] cmdBits = cmd.split(" ");

				int id = Integer.parseInt(cmdBits[2]);

				int foundIndex = -1;
				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					// size() : 3
					// index : 0 1 2
					// id : 1 2 3
					if (article.id == id) {
						foundIndex = i;
						
						// 삭제 할 게시글이 있을때
						articles.remove(foundIndex);
						System.out.printf("%d번 게시글을 삭제했습니다. :) \n", id);
						continue;
					}
				}
				// 삭제 할 게시글이 없을때
				if (foundIndex == -1) {
					System.out.printf("%d번 게시글은 존재하지 않습니다. :( \n", id);
					System.out.println("다시 한 번 확인해 주세요.");
					continue;
				}

			}
			// 그 외
			else {
				System.out.println("!! 존재하지 않는 명령어 입니다. !! \n");
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
//	String writer;
	String regDate;
	// 조회수
	int hit;

	public Article(int id, String regDate, String title, String body) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
//		this.writer = writer;
		this.hit = 0;
		
	}
	
	public void increaseHit() {
		hit ++;
	}

}

