package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.repository.userRepository;
import com.example.demo.service.Impl.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	// private userRepository repository;

	// @Bean
    // UserDetailsService userDetailsService() {
    //     return new UserDetailServiceImpl(repository);
    // }
	
	@Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/","/home","/doLogin").permitAll()
				.anyRequest().authenticated()
			) 
			.formLogin((form) -> form
				.loginPage("/login")
				.loginProcessingUrl("/doLogin")
				.failureUrl("/error")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());
		return http.build();
	}

	@Bean
	UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("hach")
                .password(encoder.encode("hacheery"))
                .roles("USER")
                .build();
        UserDetails user = User.withUsername("user")
                .password(encoder.encode("pwd1"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }

	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
				return config.getAuthenticationManager();
    }

	// @Bean
    // AuthenticationProvider authenticationProvider(){
    //     DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    //     authenticationProvider.setUserDetailsService(userDetailsService());
    //     authenticationProvider.setPasswordEncoder(passwordEncoder());
    //     return authenticationProvider;
    // }

}
