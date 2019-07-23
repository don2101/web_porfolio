package com.ssafy.mapper;

import java.util.List;

import com.ssafy.vo.Member;

public interface MemberMapper {	
	//////////////////////////////////////////////////////////////////////////////////////	
	//User °ü¸®
	public void insertMemberInfoSns(String email, String name, String location);
	public void insertMemberInfo(Member member);
	public Member checkLogin(String email, String pw);
	public List<Member> getMemberList();
	public Member getDetailMember(String email);
	public void updateMemberInfo(String password, String id);
	public void deleteMemberList(String email);
	/////////////////////////////////////////////////////////////////////////////////////////
}
