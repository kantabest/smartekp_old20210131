package com.easyworks.smartekp.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.easyworks.smartekp.member.model.Member;
import com.easyworks.smartekp.member.service.MemberService;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

	private final MemberService memberService;
	
	@Autowired
	public SecurityUserDetailsService(MemberService memberService) {
		this.memberService = memberService;
	}
    
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
    	Optional<Member> optional = memberService.getMemberInfo(id);
        
        if(!optional.isPresent()) {
            throw new UsernameNotFoundException(id);
        } else {
            return new SecurityUser(optional.get());
        }
    }
}