package onetech.onetech.security;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;
import onetech.onetech.services.UserService;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
	
	private final UserService userService;
	private final BCryptPasswordEncoder encoder;
 
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(encoder);
        return authProvider;
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/user/retrieveAll").hasAuthority("ROLE_ADMIN")
			.antMatchers("/user/add").hasAuthority("ROLE_ADMIN")
			.anyRequest().authenticated()
            .and()
            	.formLogin()
            	.permitAll()
            	.defaultSuccessUrl("/user/retrieveAll")
            .and()
            	.rememberMe()
            	.tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30))
            .and()
            	.logout()
            	.clearAuthentication(true)
            	.invalidateHttpSession(true)
            	.deleteCookies("JSESSIONID", "remember-me")
            	.logoutSuccessUrl("/login"); 
		

        http.headers().frameOptions().sameOrigin();
 
        return http.build();
    }
 
}
