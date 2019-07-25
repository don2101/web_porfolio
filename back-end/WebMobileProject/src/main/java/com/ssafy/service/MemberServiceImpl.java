package com.ssafy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.dao.MemberDAO;
import com.ssafy.vo.Member;

@Service("service")
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDAO dao;
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertMemberInfo(Member member) {
		dao.insertMemberInfo(member);
	}


	@Override
	public Member checkLogin(String email, String pw) {
		return dao.checkLogin(email, pw);
	}

	@Override
	public List<Member> getMemberList() {
		return dao.getMemberList();
	}

	@Override
	public Member getDetailMember(String email) {
		return dao.getDetailMember(email);
	}

	@Override
	public void updateMemberInfo(String password, String id) {
		dao.updateMemberInfo(password, id);
	}

	@Override
	public void deleteMemberList(String email) {
		dao.deleteMemberList(email);
	}




}
