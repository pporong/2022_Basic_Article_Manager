package com.koreaIT.java.BAM;

import java.util.Scanner;

import com.koreaIT.java.BAM.controller.ArticleController;
import com.koreaIT.java.BAM.controller.Controller;
import com.koreaIT.java.BAM.controller.MemberController;

public class App {

	public App() {
		
	}

	public void run() {

		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);
		
		// 테스트 데이터
		memberController.makeTestData();
		articleController.makeTestData();
		
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

			String[] cmdBits = cmd.split(" ");

			if (cmdBits.length == 1) {
				System.out.println("!! 명령어를 확인 해 주세요 !!");
				continue;
			}

			String controllerName = cmdBits[0];
			String actionMethodName = cmdBits[1];

			Controller controller = null;

			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("!! 존재하지 않는 명령어 입니다. !!");
				continue;
			}
			
			// filter (login check)
			String actionName = controllerName + "/" + actionMethodName;
			switch (actionName) {
			case "article/write" :
				if (Controller.isLogined() == false) {
					System.out.println("!! 로그인 후 이용 할 수 있습니다 !!");
					continue;
				}
			case "article/modify" :
			case "article/delete" :
			case "article/detail" :
				if (Controller.isLogined() == false) {
					System.out.println("!! 로그인 후 이용 할 수 있습니다 !!");
					continue;
				}
			case "member/logout" :	
			case "member/profile" :	
				if (Controller.isLogined() == false) {
					System.out.println("!! 로그인 후 이용 해 주세요 !!");
					continue;
				}
				break;
			}
			
			// filter (logout check)
			switch (actionName) {
			case "member/join" :
			case "member/login" :
				if (Controller.isLogined()) {
					System.out.println("!! 로그아웃 후 이용 해 주세요 !!");
					continue;
				}
				break;
			}
			
			controller.doAction(cmd, actionMethodName);

		}
		System.out.println("== 프로그램 종료 ==");
		sc.close();

	}
	
}
	

