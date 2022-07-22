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

			String[] cmdBits = cmd.split(" "); // article detail

			if (cmdBits.length == 1) {
				System.out.println("!! 명령어를 확인 해 주세요 !!");
				continue;
			}

			String controllerName = cmdBits[0]; // article
			String actionMethodName = cmdBits[1]; // detail

			Controller controller = null;

			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("!! 존재하지 않는 명령어 입니다. !!");
				continue;
			}


			controller.doAction(cmd, actionMethodName);

		}
		System.out.println("== 프로그램 종료 ==");
		sc.close();

	}
	
}
	

