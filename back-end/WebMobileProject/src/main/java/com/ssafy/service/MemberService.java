package com.ssafy.service;

import java.util.List;

import com.ssafy.vo.Member;

public interface MemberService {	
	
	public void insertMemberInfo(Member member); //회원가입 정보 입력
	public void insertMemberInfoSns(String email, String name, String location); //외부 로그인 정보 입력
	public Member checkLogin(String email, String pw);// 회원 로그인 체크
	public List<Member> getMemberList(); // 관리자 입장에서 멤버 리스트 확인
	public Member getDetailMember(String email); //관리자 입장에서 멤버 디테일 확인
	public void updateMemberInfo(String password, String id); // 회원정보 수정
	public void deleteMemberList(String email); // 회원 탈퇴 및 삭제
}
