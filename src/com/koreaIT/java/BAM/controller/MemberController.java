package com.koreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.dto.Member;
import com.koreaIT.java.BAM.util.Util;

public class MemberController extends Controller {
	private Scanner sc;
	private List<Member> members;
	private String cmd;
	private String actionMethodName;

	public MemberController(Scanner sc) {
		this.sc = sc;
		members = new ArrayList<>();

	}

	public void doAction(String cmd, String actionMethodName) {
		this.cmd = cmd;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "join": // 회원가입
			doJoin();
			break;
		case "login": // 로그인
			doLogin();
			break;
		case "profile": // 프로필
			showProfile();
			break;
		case "logout": // 로그아웃
			doLogout();
			break;
		default:
			System.out.println("!!! 존재하지 않는 명령어입니다 !!!");
			break;
		}

	}

	/* 회원가입 */
	private void doJoin() {

		int id = members.size() + 1;
		String regDate = Util.getNowDateStr();

		System.out.println("< 회원가입 >");
		System.out.printf("* 이름 : ");
		String userName = sc.nextLine().trim();

		// 아이디
		String loginId = null;
		// 회원가입 중 아이디 중복체크
		while (true) {
			System.out.printf("* 아이디 : ");
			loginId = sc.nextLine().trim();
			// id 미입력시
			if(loginId == "") {
				System.out.println("!! ID가 입력 되지 않았습니다 !!");
				continue;
			}
			// 중복 O
			if (isJoinableLoginId(loginId) == false) {
				System.out.printf("!! %s (은)는 이미 사용중인 아이디입니다.", loginId);
				System.out.println(" 다시 입력 해 주세요. !!");
				continue;
			}
			// 아이디 조건
			if (loginId.length() < 3) {
				System.out.println("!! 아이디는 3글자 이상 입력해야 합니다. !!");
				continue;
			}
			break;
		}

		// 비밀번호
		String loginPw = null;
		String loginPwConfirm = null;
		// 회원가입 중 비밀번호 확인
		while (true) {
			System.out.printf("* 비밀번호 : ");
			loginPw = sc.nextLine().trim();
			// 비밀번호 미입력시
			if(loginPw == "") {
				System.out.println("!! 비밀번호를 입력 해 주세요 !!");
				continue;
			}
			System.out.printf("* 비밀번호 확인 : ");
			loginPwConfirm = sc.nextLine().trim();
			// 일치 X
			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("!! 비밀번호가 일치하지 않습니다. 다시 입력 해 주세요. !!");
				continue;
			}
			break;
		}

		Member member = new Member(id, regDate, userName, loginId, loginPw);
		members.add(member);

//			System.out.println("환영합니다, " + id + "번 째" + userName +" 회원님 !");
		System.out.println("환영합니다! " + userName + "님! 회원가입이 완료되었습니다! :)");
		System.out.println("[ 아이디 : " + loginId + " / 비밀번호 : " + loginPw + " ]");
	}

	/* 로그인 */
	private void doLogin() {
		
		System.out.println("< 로그인 >");
		System.out.printf("★ 아이디 : ");
		String loginId = sc.nextLine().trim();
		System.out.printf("★ 비밀번호 : ");
		String loginPw = sc.nextLine().trim();
		
		Member member = getMemberByLoginId(loginId);
		
		// 사용자에게 입력받은 아이디에 해당하는 회원이 존재하는지?
		if (member == null) {
			System.out.println("!! 존재하지 않는 아이디입니다. !!");
			return;
		}
		// 비밀번호 일치 X
		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("!! 비밀번호가 올바르지 않습니다. !!");
			return;
		}

		loginedMember = member;
		System.out.println("반갑습니다 ! " + loginedMember.userName + "님 !");
//		System.out.printf("%s님! 환영합니다 ! \n", loginId);
	}

	/* 현재 유저 프로필 */
	private void showProfile() {
		if (loginedMember == null) {
			System.out.println("!! 로그인 후 확인 할 수 있습니다 !!");
		} else {
			System.out.println("== 현재 로그인 회원 정보 ==");
			System.out.println("* ID : " + loginedMember.loginId);
			System.out.println("* NAME : " + loginedMember.userName);
		}
		return;
	}

	/* 로그아웃 */
	private void doLogout() {
		loginedMember = null;
		System.out.println("로그아웃 되었습니다. 또 오세요!");

	}

//	===============================================================

	/* 회원가입 아이디 중복체크 */
	private boolean isJoinableLoginId(String loginId) {

		int index = getMemberIndexByLoginId(loginId);
		// -1 = 0 = '없다'의 의미
		if (index == -1) {
			return true;
		}
		return false;
	}

	/* 회원가입 아이디 인덱스 중복체크 */
	private int getMemberIndexByLoginId(String loginId) {

		int i = 0;

		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	/* 회원 아이디 가입 존재여부 */
	private Member getMemberByLoginId(String loginId) {

		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return null;
		}

		return members.get(index);
	}
	
	/* 회원가입 테스트 데이터 생성 */
	public void makeTestData() {
		System.out.println("Start for Test to Member data");

		members.add(new Member(1, Util.getNowDateStr(), "짱구", "id1", "pw1"));
		members.add(new Member(2, Util.getNowDateStr(), "철수", "id2", "pw2"));
		members.add(new Member(3, Util.getNowDateStr(), "유리", "id3", "pw3"));
	}

}
