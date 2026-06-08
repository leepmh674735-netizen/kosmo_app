package com.example.app;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.notice.NoticeDTO;
import com.winter.app.notice.NoticeRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class NoticeRepositoryTest {

    @Autowired
    private NoticeRepository noticeRepository;

    @Test
    public void detailTest()throws Exception{
        NoticeDTO noticeDTO = new NoticeDTO();
        
        Optional<NoticeDTO> result = noticeRepository.findById(8L);
        
        noticeDTO = result.get();
        
        log.info("{}", noticeDTO.getAttaches().get(0).getStoreFileName());

        assertNotNull(noticeDTO);
    }

}