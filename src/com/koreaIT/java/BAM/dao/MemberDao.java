package com.koreaIT.java.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.java.BAM.dto.Member;

public class MemberDao extends Dao {
	
	public List<Member> members;
	
	public MemberDao() {
		members = new ArrayList<>();
	}
	
	public void add(Member member) {
		members.add(member);
		lastId ++;
	}
	
	/* 회원가입 아이디 중복체크 */
	public boolean isJoinableLoginId(String loginId) {

		int index = getMemberIndexByLoginId(loginId);
		// -1 = 0 = '없다'의 의미
		if (index == -1) {
			return true;
		}
		return false;
	}

	/* 회원가입 아이디 인덱스 중복체크 */
	public int getMemberIndexByLoginId(String loginId) {

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
	public Member getMemberByLoginId(String loginId) {

		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return null;
		}

		return members.get(index);
	}

	public List<Member> getMembers() {
		return members;
	}

	public String getMemberNameById(int memberid) {
		for (Member member : members) {
			if (memberid == member.id) {
				return member.userName;
			}
		}
		return null;
	}

}
