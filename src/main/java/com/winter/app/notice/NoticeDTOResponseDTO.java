package com.winter.app.notice;

import java.time.LocalDateTime;
import java.util.List;

public record NoticeDTOResponseDTO(
	Long id,
	String username,
	String title,
	Long views,
	LocalDateTime CreateAt
		
		
		
		) {

	
}
