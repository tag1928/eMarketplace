package eMarketplace.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig
{
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		return http
			.authorizeHttpRequests(request -> request
				.requestMatchers(HttpMethod.POST, "/listing", "/listing/*", "/user", "/user/*")
				.permitAll()
				.requestMatchers(HttpMethod.GET, "/", "/*.html", "/*.css", "/js/*.js")
				.permitAll()
				.anyRequest()
				.permitAll())
			.build();
	}
}