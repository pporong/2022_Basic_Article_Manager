package com.koreaIT.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.controller.ArticleController;
import com.koreaIT.java.BAM.controller.Controller;
import com.koreaIT.java.BAM.controller.MemberController;
import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.dto.Member;
import com.koreaIT.java.BAM.util.Util;

public class App {

	private List<Article> articles;
	private List<Member> members;

	public App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void run() {

		System.out.println("== 프로그램 시작 ==");

		makeTestData();

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc, members);
		ArticleController articleController = new ArticleController(sc, articles);
		
		while (true) {
			
			System.out.printf("명령어 : ");
			String cmd = sc.nextLine().trim();

			/* 명령어 미입력시 */
			if (cmd.length() == 0) {
				System.out.println("!! 명령어를 입력 해 주세요 !!");
				continue;
			}

			/* 종료시 */
			if (cmd.equals("exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			
			String[] cmdBits = cmd.split(" ");  // article detail
			
			if(cmdBits.length == 1) {
				System.out.println("!! 명령어를 확인 해 주세요 !!");
				continue;
			}
			
			String controllerName = cmdBits[0]; // article
//			String actionMethodName = cmdBits[1];     // detail
			
			Controller controller = null;
			
			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("!! 존재하지 않는 명령어 입니다. !!");
				continue;
			}
			
			controller.doAction(cmd);
			
//			// Member
//			/* 회원가입시 */
//			if (cmd.equals("member join")) {
//				memberController.doJoin();
//			}
//
//			// Article
//			/* 게시글 작성 메서드 */
//			if (cmd.equals("article write")) {
//				articleController.doWrite();
//			} /* 게시글 목록 */
//			else if (cmd.startsWith("article list")) {
//				articleController.viewList(cmd);
//			} /* 게시글 상세보기 */
//			else if (cmd.startsWith("article detail ")) {
//				articleController.viewDetail(cmd);
//			} /* 게시글 수정 */
//			else if (cmd.startsWith("article modify ")) {
//				articleController.doModify(cmd);
//			} /* 게시글 삭제 */
//			else if (cmd.startsWith("article delete ")) {
//				articleController.doDelete(cmd);
//			} 
//			
//			// Else
//			/* 그 외 */
//			else {
//				System.out.println("!! 존재하지 않는 명령어 입니다. !! \n");
//			}
			
		}
		System.out.println("== 프로그램 종료 ==");
		sc.close();

	}

	
	/* 테스트 데이터 생성 */
	private void makeTestData() {

		System.out.println("테스트를 위한 데이터를 생성합니다.");

		articles.add(new Article(1, Util.getNowDateStr(), "제목1", "내용1", 11));
		articles.add(new Article(2, Util.getNowDateStr(), "제목2", "내용2", 22));
		articles.add(new Article(3, Util.getNowDateStr(), "제목2", "내용2", 33));
	}

}
