package com.ssafy.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.mapper.MemberMapper;
import com.ssafy.vo.Member;
@Repository
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	MemberMapper mapper;
	


	@Override
	public void insertMemberInfo(Member member) {
		mapper.insertMemberInfo(member);
	}


	@Override
	public Member checkLogin(String email, String pw) {
		return mapper.checkLogin(email, pw);
	}

	@Override
	public List<Member> getMemberList() {
		return mapper.getMemberList();
	}

	@Override
	public Member getDetailMember(String id) {
		return mapper.getDetailMember(id);
	}


	@Override
	public void updateMemberInfo(String password, String id) {
		 mapper.updateMemberInfo(password, id);
	}

	@Override
	public void deleteMemberList(String email) {
		mapper.deleteMemberList(email);
	}


	@Override
	public void updateMemberGrade(String id, String grade) {
		mapper.updateMemberGrade(id, grade);
	}

}
