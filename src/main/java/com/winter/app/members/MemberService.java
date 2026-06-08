package com.winter.app.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public MemberDTO join(MemberDTO memberDTO, MultipartFile profile) throws Exception {
		memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
		//파일을 HDD에 저장하고, 파일정보를 Table Insert 해야 함
		System.out.println(profile.getOriginalFilename());
		System.out.println(profile.getSize());
		return memberRepository.save(memberDTO);
 	}
		
}
