package com.winter.app.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.winter.app.members.MemberDTO;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final ObjectMapper mapper;
	private final AuthenticationManager authenticationManager;
	private final JwtTokenManager jwtTokenManager;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenManager jwtTokenManager) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenManager = jwtTokenManager;
		this.mapper = new ObjectMapper();
		this.setFilterProcessesUrl("/member/login");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			MemberDTO memberDTO = mapper.readValue(request.getInputStream(), MemberDTO.class);

			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					memberDTO.getUsername(),
					memberDTO.getPassword()
			);

			return authenticationManager.authenticate(token);

		} catch (Exception e) {
			e.printStackTrace();
			throw new org.springframework.security.authentication.InternalAuthenticationServiceException("인증 요청 처리 중 오류 발생", e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		String accessToken = jwtTokenManager.createAcessToken(authResult);
		String refreshToken = jwtTokenManager.createRefreshToken(authResult);

		JwtAuthDTO jwtAuthDTO = new JwtAuthDTO();
		jwtAuthDTO.setAccessToken(accessToken);
		jwtAuthDTO.setReferToken(refreshToken); 
		jwtAuthDTO.setUsername(authResult.getName());

		String result = mapper.writeValueAsString(jwtAuthDTO);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("{\"error\": \"Login Failed\", \"message\": \"" + failed.getMessage() + "\"}");
	}
	
}