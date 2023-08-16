package com.example.demo.config;

import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
	// private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final UserService service;
	
	@Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		HeaderWriterLogoutHandler clearSiteData = new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter());
		http.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/","/home","/register","/doRegister").permitAll()
						.requestMatchers("changeProduct").hasAuthority("ADMIN")
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.loginProcessingUrl("/doLogin")
				.failureUrl("/register")
				.permitAll()
			)
			.logout((logout) -> logout
					.logoutSuccessUrl("/logout")
					.logoutSuccessHandler((request, response, authentication) -> {
						HttpSession session = request.getSession(false);
						if (session != null) {
							session.invalidate();
						}
						response.sendRedirect("/login?logout");
					})
//					.addLogoutHandler(clearSiteData)
					.permitAll()
			);
			// .authenticationProvider(authenticationProvider())
			// 	.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
		return http.build();
	}

	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
				return config.getAuthenticationManager();
    }

	@Bean
	AuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(service.setupUserDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

}
