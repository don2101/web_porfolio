package com.ssafy.dao;

import java.util.List;

import com.ssafy.vo.Member;

public interface MemberDAO {

	//User ���� 
	public void insertMemberInfo(Member member);
	public Member checkLogin(String email, String pw);
	public List<Member> getMemberList();
	public Member getDetailMember(String email);
	public void updateMemberInfo(String password, String id);
	public void deleteMemberList(String id);
	/////////////////////////////////////////////////////////////////////////////////////////
	//admin method
	public void updateMemberGrade(String id, String grade);
}
