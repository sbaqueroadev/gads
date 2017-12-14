package co.com.sbaqueroa.gads;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import co.com.sbaqueroa.gads.security.JWTAuthenticationFilter;
import co.com.sbaqueroa.gads.security.JWTAuthorizationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private UserDetailsService userDetailsService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public WebSecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET,"/users/access","/users/create","/error"
				,"/resources/**","/webjars/**").permitAll()
		.antMatchers(HttpMethod.POST,"/users/sign-up").permitAll()
		.antMatchers(HttpMethod.GET,"/class/**").hasAnyAuthority("TEACH_CLASS_PRIVILEGE",
				"VIEW_CLASS_PRIVILEGE")
		.antMatchers(HttpMethod.POST,"/class/**").hasAnyAuthority("TEACH_CLASS_PRIVILEGE",
				"VIEW_CLASS_PRIVILEGE")
		.anyRequest().hasAnyAuthority("READ_PRIVILEGE","WRITE_PRIVILEGE")
		.and()
		.formLogin()
		.loginPage("/users/access")
		.loginProcessingUrl("/login")
		.and()
		.addFilterAt(new JWTAuthenticationFilter(authenticationManager()),UsernamePasswordAuthenticationFilter.class)
		.addFilter(new JWTAuthorizationFilter(authenticationManager()))
		// this disables session creation on Spring Security
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
}