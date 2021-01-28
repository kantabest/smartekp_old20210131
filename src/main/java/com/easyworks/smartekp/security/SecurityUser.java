package com.easyworks.smartekp.security;

import com.easyworks.smartekp.member.model.Member;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class SecurityUser extends User {
    private static final long serialVersionUID = 1L;
    private Member member;

    

    public SecurityUser(Member member) {
        super(member.getMemberId(), member.getLoginPw(), member.isUsedYn(), true, true, true,
                AuthorityUtils.createAuthorityList(member.getMemberRole()));
                        
        this.member = member;
    }

    public Member getMember() {
        return member;
    }
}