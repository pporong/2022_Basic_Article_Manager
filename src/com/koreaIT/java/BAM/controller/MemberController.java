package com.koreaIT.java.BAM.controller;

import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.dto.Member;
import com.koreaIT.java.BAM.util.Util;

public class MemberController {
	private Scanner sc;
	private List<Member> members;
	
	public MemberController(Scanner sc, List<Member> members) {
		this.sc = sc;
		this.members = members;
	}
	
	public void doJoin() {
		
			int id = members.size() + 1;
			String regDate = Util.getNowDateStr();
			
			// 회원가입
			System.out.println("< 회원가입 >");
			System.out.printf("* 이름 : ");
			String userName = sc.nextLine().trim();
			
			// 아이디
			String loginId = null;
			// 회원가입 중 아이디 중복체크 
			while (true) {
				System.out.printf("* 아이디 : ");
				loginId = sc.nextLine().trim();
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
				System.out.printf("* 비밀번호 확인 : ");
				loginPwConfirm = sc.nextLine().trim();			
					// 일치 X
				if (loginPw.equals(loginPwConfirm) == false) {
					System.out.println("!! 비밀번호가 일치하지 않습니다. 다시 입력 해 주세요. !!");
					continue;
				}
				break;
			}
			
			Member member = new Member(id, userName, regDate, loginId, loginPw);
			members.add(member);
			
//			System.out.println("환영합니다, " + id + "번 째" + userName +" 회원님 !");
			System.out.println("환영합니다! " + userName + "님! 회원가입이 완료되었습니다! :) \n");
		}
		
	
	
	/* 로그인 아이디 중복체크 */
	private boolean isJoinableLoginId(String loginId) {

		int index = getMemberIndexByLoginId(loginId);

		// -1 = 0 = '없다'의 의미
		if (index == -1) {
			return true;
		}
		return false;
	}

	/* 로그인 아이디 인덱스 중복체크 */
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


}
