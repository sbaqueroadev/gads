package co.com.sbaqueroa.gads.security;

import static co.com.sbaqueroa.gads.security.SecurityConstants.EXPIRATION_TIME;
import static co.com.sbaqueroa.gads.security.SecurityConstants.HEADER_STRING;
import static co.com.sbaqueroa.gads.security.SecurityConstants.SECRET;
import static co.com.sbaqueroa.gads.security.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Scanner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.sbaqueroa.gads.model.implementation.ApplicationUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,
			HttpServletResponse res) throws AuthenticationException {
		ApplicationUser creds = new ApplicationUser();
		if(req.getParameter("username")==null || req.getParameter("password")==null)
			throw new RuntimeException();
		creds.setUsername(req.getParameter("username"));
		creds.setPassword(req.getParameter("password"));
		return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						creds.getUsername(),
						creds.getPassword())
				);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req,
			HttpServletResponse res,
			FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String token = Jwts.builder()
				.setSubject(((User) auth.getPrincipal()).getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
				.compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
		Cookie authcoo = new Cookie(HEADER_STRING, URLEncoder.encode(TOKEN_PREFIX+ token,"UTF-8"));
		authcoo.setSecure(true);
		authcoo.setMaxAge((int) EXPIRATION_TIME);
		res.addCookie(authcoo);
		chain.doFilter(req, res);
	}
}