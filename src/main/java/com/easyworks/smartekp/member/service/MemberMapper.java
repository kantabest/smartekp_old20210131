package com.easyworks.smartekp.member.service;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.easyworks.smartekp.member.model.Member;

@Mapper
public interface MemberMapper {
	Optional<Member> getMemberInfo(String id);
	List<Member> getMemberList();
	void insertMember(Member member);
	void updateMember(Member member);
	void deleteMember(Member member);
	void updatePassword(Member member);
	String setPushMessage(String regId, String pushMessage);
}
