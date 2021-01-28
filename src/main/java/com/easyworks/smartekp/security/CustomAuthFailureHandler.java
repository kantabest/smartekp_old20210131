package com.easyworks.smartekp.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthFailureHandler implements AuthenticationFailureHandler {
	private String defaultFailureUrl;
	
	public CustomAuthFailureHandler(String defaultFailureUrl) {
		this.defaultFailureUrl = defaultFailureUrl;
	}
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
										AuthenticationException exception) throws IOException, ServletException {
		String errorMsg = "";
		
		if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
			errorMsg = "ID(사원번호)나 비밀번호가 틀렸습니다.";
		} else if(exception instanceof DisabledException) {
			errorMsg = "사용 중지 처리된 ID 입니다.";
		} else {
			errorMsg = "로그인 시스템 에러입니다.";
		}
		
		request.setAttribute("errorMsg", errorMsg);
		request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
	}
}
