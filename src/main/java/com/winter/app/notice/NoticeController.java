package com.winter.app.notice;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

	private final NoticeService noticeService;

	@GetMapping("/detail")
	public NoticeDTOResponseDetail detail(@RequestParam("id") Long id) throws Exception {
		return noticeService.detail(id);
	}

	@GetMapping("/list")
	public List<NoticeDTOResponseDTO> list() throws Exception {
		return noticeService.list();
	}

	@PostMapping("/add")
	public NoticeDTO add(NoticeDTORequestDTO dto, @RequestParam(value = "attach", required = false) MultipartFile[] attach) throws Exception {
		dto.setUsername("user2"); 
		return noticeService.add(dto, attach);
	}

}