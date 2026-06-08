package com.winter.app.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.winter.app.members.MemberRepository;
import com.winter.app.members.MemberDTO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
	
	private final MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberDTO memberDTO = memberRepository.findById(username)
				.orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 사용자입니다: " + username));
		return memberDTO;
	}
}