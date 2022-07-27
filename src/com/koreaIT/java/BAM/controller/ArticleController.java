package com.koreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.dto.Member;
import com.koreaIT.java.BAM.util.Util;

public class ArticleController extends Controller {
	private Scanner sc;
	private List<Article> articles;
	private String cmd;
	private String actionMethodName;

	public ArticleController(Scanner sc) {
		this.sc = sc;
		
		articles = Container.articleDao.articles;
	}

	public void doAction(String cmd, String actionMethodName) {
		this.cmd = cmd;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "write":
			doWrite();
			break;
		case "list":
			viewList();
			break;
		case "detail":
			viewDetail();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		default:
			System.out.println("!!! 존재하지 않는 명령어입니다 !!!");
			break;

		}
	}

	/* 게시글 작성 */
	private void doWrite() {

		int id = Container.articleDao.setNewId();

		System.out.println("< 게시글 작성 >");
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		String regDate = Util.getNowDateStr();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		Article article = new Article(id, regDate, loginedMember.id, title, body);

		Container.articleDao.add(article);

		System.out.printf("%d번 글이 등록되었습니다. :)\n", id);

	}

	/* 게시글 목록 */
	private void viewList() {
		if (articles.size() == 0) {
			// 게시글 없을 경우
			System.out.println("게시글이 없습니다. :(");
			return;
		}
		// 작성된 게시글 특정 검색
		String searchKeyword = cmd.substring("article list".length()).trim();

		List<Article> forPrintArticles = articles;

		if (searchKeyword.length() > 0) {
			forPrintArticles = new ArrayList<>();

			System.out.printf("검색어 : '%s' (이)가 포함된 결과입니다. \n", searchKeyword);
			// 존재 할 때
			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					forPrintArticles.add(article);
				}
			}
			// 존재하지 않는 경우
			if (forPrintArticles.size() == 0) {
				System.out.println("해당 검색어를 포함하는 게시글이 없습니다. :( ");
				return;
			}
		}

		System.out.println("*  글번호  |  작 성 자  |  제    목  |       작 성 일       | 조회수 *");
		// 역순으로 정렬
		for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
			Article article = forPrintArticles.get(i);
			
			String writerName = null;
			
			List<Member> members = Container.memberDao.members;
			
			for(Member member : members) {
				if(article.memberId == member.id) {
										// 이름이나 loginId 로 해도 됨
					writerName = member.userName;
					break;
				}
			}
			
			System.out.printf("    %d번   |   %s   |   %s   | %s | %d \n",
					article.id, writerName, article.title,
					article.regDate, article.hit);
		}
	}

	/* 게시글 상세보기 */
	private void viewDetail() {
		String[] cmdBits = cmd.split(" ");
		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleById(id);

		// 상세보기 할 게시글이 없을 때
		if (foundArticle == null) {
			System.out.printf("%d번 게시글은 존재하지 않습니다. :( \n", id);
			return;
		}

		foundArticle.increaseHit();

		// 상세보기 할 게시글이 있을 때
		System.out.printf("[ %d번 게시글에 대한 정보입니다. ]\n", foundArticle.id);
		System.out.printf("* 번호 : %d번 \n", foundArticle.id);
		System.out.printf("* 작성자 : %d번 \n", foundArticle.memberId);
		System.out.printf("* 조회 : %d번 \n", foundArticle.hit);
		System.out.printf("* 날짜 : %s \n", foundArticle.regDate);
		System.out.printf("* 제목 : %s \n", foundArticle.title);
		System.out.printf("* 내용 : %s \n", foundArticle.body);

	}

	/* 게시글 수정 */
	private void doModify() {

		String[] cmdBits = cmd.split(" ");

		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleById(id);

		// 수정 할 게시글이 없을 때
		if (foundArticle == null) {
			System.out.printf("%d번 게시글은 존재하지 않습니다. :( \n", id);
			return;
		}

		// 타계정 글 수정 시도 시
		if (foundArticle.memberId != loginedMember.id ) {
			System.out.println("!! 수정 권한이 없습니다 !!");
			return;
		}

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

	}

	/* 게시글 삭제 */
	private void doDelete() {

		String[] cmdBits = cmd.split(" ");

		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleById(id);

		// 삭제 할 게시글이 없을때
		if (foundArticle == null) {
			System.out.printf("%d번 게시글은 존재하지 않습니다. :( \n", id);
			System.out.println("다시 한 번 확인해 주세요.");
			return;
		}

		// 타계정 글 삭제 시도시
		if (foundArticle.memberId != loginedMember.id) {
			System.out.println("!! 삭제 권한이 없습니다 !!");
			return;
		}

		// 삭제 할 게시글이 있을때
		articles.remove(foundArticle);
		System.out.printf("%d번 게시글을 삭제했습니다. :) \n", id);
		return;

	}

//	===============================================================

	/* Article ArrayList 순회 로직 중복제거 (detail, modify) */
	private Article getArticleById(int id) {

		int index = getArticleIndexById(id);

		if (index != -1) {
			return articles.get(index);
		}
		return null;
	}

	/* Article ArrayList 순회 로직 중복제거 (delete) */
	private int getArticleIndexById(int id) {

		int i = 0;

		for (Article article : articles) {
			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

	/* 글쓰기 테스트 데이터 생성 */
	public void makeTestData() {

		System.out.println("Start for Test to Article data");

//								public Article						( id,         regDate,     memberId, title, body, hit)
		Container.articleDao.add(new Article(Container.articleDao.setNewId(), Util.getNowDateStr(), 1, "제목1", "내용1", 11));
		Container.articleDao.add(new Article(Container.articleDao.setNewId(), Util.getNowDateStr(), 2, "제목2", "내용2", 22));
		Container.articleDao.add(new Article(Container.articleDao.setNewId(), Util.getNowDateStr(), 2, "제목2", "내용2", 33));
	}

}
