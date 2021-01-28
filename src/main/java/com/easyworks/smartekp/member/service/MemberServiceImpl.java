package com.easyworks.smartekp.member.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.easyworks.smartekp.member.model.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	public MemberServiceImpl(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	
	public Optional<Member> getMemberInfo(String id) {
		return memberMapper.getMemberInfo(id);
	}
	
	public List<Member> getMemberList() {
		return memberMapper.getMemberList();
	}
	
	public void insertMember(Member member) {
		// member.setPw(encoder.encode(member.getPw()));
		// Use database encryt function for login password
		member.setEnabled(true);
		memberMapper.insertMember(member);
	}
	
	public void updateMember(Member member) {
		if(!member.getPw().equals(""))
			member.setPw(encoder.encode(member.getPw()));
		memberMapper.updateMember(member);
	}
	
	public void deleteMember(Member member) {
		memberMapper.deleteMember(member);
	}
	
	public void updatePassword(Member member) {
		member.setPw(encoder.encode(member.getPw()));
		memberMapper.updatePassword(member);
	}
	
	public String setPushMessage(String regId, String pushMessage) {
		return memberMapper.setPushMessage(regId, pushMessage);
	}
}
