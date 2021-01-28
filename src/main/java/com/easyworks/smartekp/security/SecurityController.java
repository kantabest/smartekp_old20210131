package com.easyworks.smartekp.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class SecurityController {
	@RequestMapping("/login")
	public void login() { }
	
	@RequestMapping("/logout")
	public void logout() { }
	
	@RequestMapping("/error/403")
	public void accessDenied() { }
	
	@RequestMapping("/error/404")
	public void notExists() { }
	
	@RequestMapping("/sessionExpired")
	public String sessionExpired() { return "sessionExpired"; }
	
	@RequestMapping("/afterLogout")
	public String afterLogout() { return "afterLogout"; }
	
	@RequestMapping("/")
	public String indexRedirect() { return "redirect:main"; }
	
	// 권한별로 화면 분기 처리
	@GetMapping("/main")
	public String main(@AuthenticationPrincipal SecurityUser loginUser) {
		switch (loginUser.getMember().getMemberRole()) {
		case "admin":
			return "main";
			
		// case "logistics":
		// 	return "logistics/logisticsMain";
			
		// case "packing":
		// 	return "packing/packingMain";
			
		// case "process":
		// 	return "process/processMain";
		
		// case "subdivision":
		// 	return "subdivision/subdivisionMain";
			
		// case "sales":
		// 	return "sales/salesMain";
			
		default:
			return "error/403";
		}
	}
}
