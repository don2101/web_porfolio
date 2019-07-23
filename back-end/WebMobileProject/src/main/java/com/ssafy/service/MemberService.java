package com.ssafy.service;

import java.util.List;

import com.ssafy.vo.Member;

public interface MemberService {	
	
	public void insertMemberInfo(Member member); //ȸ������ ���� �Է�
	public void insertMemberInfoSns(String email, String name, String location); //�ܺ� �α��� ���� �Է�
	public Member checkLogin(String email, String pw);// ȸ�� �α��� üũ
	public List<Member> getMemberList(); // ������ ���忡�� ��� ����Ʈ Ȯ��
	public Member getDetailMember(String email); //������ ���忡�� ��� ������ Ȯ��
	public void updateMemberInfo(String password, String id); // ȸ������ ����
	public void deleteMemberList(String email); // ȸ�� Ż�� �� ����
}
