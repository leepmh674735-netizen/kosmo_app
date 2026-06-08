package com.winter.app.notice;

import java.time.LocalDateTime;
import java.util.List;

public record NoticeDTOResponseDetail(

		Long id,

		String content,

		boolean isPinned,

		String title,

		LocalDateTime updateAt,

		String username,

		Long views,

		List<NoticeFileDetailDTO> attaches

) {

}
