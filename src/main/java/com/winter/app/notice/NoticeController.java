package com.witer.app.notice;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
@RequestMapping("/notice/**")
public class NoticeController {

    private NoticeService noticeService;

    @GetMapping("detail")
    public NoticeDTOResponseDetail detail() throws Exception {

        return noticeService.detail(8L);
    }

    @GetMapping("list")
    public List<NoticeDTOResponseDTO> list() throws Exception {
        return noticeService.list();
    }

    @PostMapping("add")
    public NoticeDTO add(NoticeDTORequestDTO dto, MultipartFile[] attach) throws Exception {
        dto.setUsername("user2");
        return noticeService.add(dto, attach);
    }

}