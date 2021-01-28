package com.easyworks.smartekp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
    private final SecurityUserDetailsService userDetailsService;
    
    @Autowired
	public SecurityConfig(SecurityUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void configure(HttpSecurity security) throws Exception {
		// 로그인, 로그아웃, 세션만료 페이지는 허용.
		security.authorizeRequests().antMatchers("/login", "/afterLogout", "/sessionExpired").permitAll();
		
		// 정적 리소스들에 대한 접근 허가를 처리함.
		// security.authorizeRequests().antMatchers("/login", "/css/**", "/js/**", "favicon.ico").permitAll();
		security.authorizeRequests().requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();
		
		/*
		 * 각 폴더별로 권한 설정.
		 * admin : 관리자
		 * logistics : 물류
		 * process : 생산 공정 작업자
		 * packing : 생산 포장 작업자, 공정 작업자도 사용 가능.
		 * subdivision : 소분 작업자
		 * sales : 물류의 판매출하처리 메뉴만 영남영업소에서 사용.
		 * inventory : inventory의 모든 메뉴는  sales를 제외한 모든 권한
		 * inventory/inventorySearch : sales도 사용 가능.
		 * 
		 * 정해진 폴더에 정해진 권한만 설정해야 하는데 특정 페이지에는 다른 권한도 접근을 해야 된다면 거기에 접근해야 하는 모든 권한들을 모두 작성해줘야 한다.
		 * 아래 sales가 /logistics/logisticsShipment, /inventory/inventorySearch 페이지에 접근해야 하기 때문에 다른 권한들도 모두 작성했다.
		 */
		
		// security.authorizeRequests().antMatchers("/admin/**").hasAuthority("admin");
		// security.authorizeRequests().antMatchers("/logistics/logisticsShipment").hasAnyAuthority("admin", "logistics", "sales");
		// security.authorizeRequests().antMatchers("/logistics/**").hasAnyAuthority("admin", "logistics");
		// security.authorizeRequests().antMatchers("/process/**").hasAnyAuthority("admin", "process");
		// security.authorizeRequests().antMatchers("/packing/**").hasAnyAuthority("admin", "process", "packing");
		// security.authorizeRequests().antMatchers("/subdivision/**").hasAnyAuthority("admin", "subdivision");
		// security.authorizeRequests().antMatchers("/inventory/inventorySearch").hasAnyAuthority("admin", "logistics", "process", "packing", "subdivision", "sales");
		// security.authorizeRequests().antMatchers("/inventory/**").hasAnyAuthority("admin", "logistics", "process", "packing", "subdivision");
		// security.authorizeRequests().antMatchers("/sales/**").hasAnyAuthority("admin", "sales");
		
		
		// 권한 없는 페이지 접근시 페이지 설정.
		security.exceptionHandling().accessDeniedPage("/error/403");
		
		// 기본적으로 모든 권한이 있어야 사용 가능 --> 로그인 필요.
		security.authorizeRequests().antMatchers("/**").authenticated();
		
		// 사이트 위변조 방지 기능은 개발할 때는 해제.
		security.csrf().disable();
		
		// 로그인 인증 관련 설정.
		security.formLogin().loginPage("/login").defaultSuccessUrl("/main", true)
				.usernameParameter("id").passwordParameter("pw")
				.failureHandler(new CustomAuthFailureHandler("/login"));
		
		// 스프링시큐리티에 유저 정보 전달.
		security.userDetailsService(userDetailsService);
		
		// 세션 설정
		security.sessionManagement()
				.invalidSessionUrl("/sessionExpired") // 세션 타임아웃 시 페이지 이동, 로그아웃할 때에도 실행됨. logoutSuccessUrl 메서드가 무시된다.
				.maximumSessions(1)                   // 로그인 세션 최대 개수는 1개
				.sessionRegistry(sessionRegistry())
				.expiredUrl("/sessionExpired")        // 중복 로그인시 기존 세션은 파기시키고  페이지 이동
				.maxSessionsPreventsLogin(false);     // 중복 로그인 시 기존 사용자 세션 종료.
		
		// 로그아웃 설정, 로그아웃 시 세션제거, 쿠키 제거
		// sessionExpired페이지에서 afterLogout로 이동하므로, securityController에 작성되어야 한다.
		security.logout()
				// .logoutSuccessUrl("/afterLogout") // invalidSessionUrl 메서드가 먼저 실행되서 이건 실행이 되지 않음.
				.invalidateHttpSession(true).deleteCookies("JSESSIONID").clearAuthentication(true);
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
	
	// session 관련 추가. 아래 2개의 bean이 없으면 세션 중복 에러 해결이 안 된다.
	// 세션 중복 에러: A 유저가 로그인 후 루그아웃 -> 다시 로그인할 때 'Maximum sessions of 1 for this principal exceeded' 에러가 난다.
	@Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    } // Register HttpSessionEventPublisher
	
    @Bean
    public static ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
    }
}
