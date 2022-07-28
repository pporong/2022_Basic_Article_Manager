package com.koreaIT.java.BAM.service;

import java.util.List;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dao.Dao;
import com.koreaIT.java.BAM.dao.MemberDao;
import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.dto.Member;

public class MemberService {
	
	private MemberDao memberDao;

	public MemberService() {
		this.memberDao = Container.memberDao;
	}

	public int setNewId() {
		int id = memberDao.setNewId();
		
		return id;
	}

	public void add(Member member) {
		memberDao.add(member);
	}

	public boolean isJoinableLoginId(String loginId) {
		return memberDao.isJoinableLoginId(loginId);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}
	
	public List<Member> getMembers() {
		return memberDao.getMembers();
	}
	
}
