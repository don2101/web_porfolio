package com.ssafy.service;

import java.util.List;

import com.ssafy.vo.Member;

public interface MemberService {	
	
	public void insertMemberInfo(Member member); //
	public Member checkLogin(String email, String pw);// 
	public List<Member> getMemberList(); // 
	public Member getDetailMember(String email); //
	public void updateMemberInfo(String password, String id); //
	public void deleteMemberList(String id);
	/////////////////////////////////////////////////////////////////////////////////////////
	//admin method
	public void updateMemberGrade(String id, String grade);
	public String getMemberGrade(String mid);
}
