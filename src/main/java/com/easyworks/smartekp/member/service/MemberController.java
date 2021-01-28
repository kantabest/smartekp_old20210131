package com.easyworks.smartekp.member.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easyworks.smartekp.common.model.CustomDateTime;
import com.easyworks.smartekp.member.model.Member;
import com.easyworks.smartekp.security.SecurityUser;


@Controller
public class MemberController {
	
	private final MemberService memberService;
	
	@Autowired
	private SessionRegistry sessionRegistry;
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/member/memberInfo")
	public String memberInfo(Model model, @AuthenticationPrincipal SecurityUser loginUser, Member member) {
		member.setTeam(loginUser.getMember().getTeam());
		member.setName(loginUser.getMember().getName());
		member.setId(loginUser.getMember().getId());
		model.addAttribute("memberInfo", member);
		return "member/memberInfo";
	}
	
	@PostMapping("/member/updatePassword")
	public String changePassword(Member member) {
		memberService.updatePassword(member);
		return "redirect:/";
	}
	
	
    
    public List<Member> getListLoggedInUsers() {
        final List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        List<Member> loggedInMemberList = new ArrayList<>();
        
        for(final Object principal : allPrincipals) {
            if(principal instanceof SecurityUser) {
                final SecurityUser loginUser = (SecurityUser) principal;
                loggedInMemberList.add(loginUser.getMember());
            }
        }
        return loggedInMemberList;
    }
	
	@PostMapping("/admin/getMemberList_ajax")
	@ResponseBody
	public List<Member> getMemberList() {
		return memberService.getMemberList();
	}
	
	@GetMapping("/admin/memberList")
	public String getMemberList(Model model) {
		model.addAttribute("memberList", getMemberList());
		return "admin/memberList";
	}
	
	// update 후 페이지 갱신을 위해  view return
	@PostMapping("/admin/updateMember")
	public String updateMember(Member member) {
		memberService.updateMember(member);
		return "redirect:memberList";
	}
	
	@PostMapping("/admin/deleteMember")
	public String deleteMember(Member member) {
		memberService.deleteMember(member);
		return "redirect:memberList";
	}
	
	// insert 후 페이지 갱신을 위해  view return
	@PostMapping("/admin/insertMember")
	public String insertMember(Member member) {
		memberService.insertMember(member);
		return "redirect:memberList";
	}
	
	@GetMapping("/admin/pushMessage")
	public String pushMessage(Model model) {
		model.addAttribute("memberList", getMemberList());
		model.addAttribute("loggedInMembers", getListLoggedInUsers());
		return "admin/pushMessage";
	}
	
	@PostMapping("/admin/getListLoggedInUsers_ajax")
	@ResponseBody
	public List<Member> getListLoggedInMembers() {
		return getListLoggedInUsers();
	}
	
	@PostMapping("/admin/setPushMessage_ajax")
	@ResponseBody
	public String setPushMessage(String regId, String pushMessage) {
		return memberService.setPushMessage(regId, pushMessage);
	}
	
	@GetMapping("/admin/getCurrentDateTime_ajax")
	@ResponseBody
	public String getCurrentDateTime() {
		return CustomDateTime.getCurrentDateTime();
	}
}
