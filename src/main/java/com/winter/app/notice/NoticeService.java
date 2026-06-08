package com.winter.app.notice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.members.MemberDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeService {

	private final NoticeRepository noticeRepository;

	public NoticeDTOResponseDetail detail(Long id) throws Exception {
		NoticeDTO noticeDTO = noticeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 공지사항이 존재하지 않습니다. ID: " + id));

		List<NoticeFileDetailDTO> ar = noticeDTO.getAttaches().stream().map(f -> {
			NoticeFileDetailDTO detailDTO = new NoticeFileDetailDTO();
			detailDTO.setOriginalFileName(f.getOriginalFileName());
			detailDTO.setStoreFileName(f.getStoreFileName());
			return detailDTO;
		}).collect(Collectors.toList());

		return new NoticeDTOResponseDetail(
				noticeDTO.getId(),
				noticeDTO.getContent(),
				noticeDTO.getCreatedAt(),
				noticeDTO.isPinned(), 
				noticeDTO.getTitle(), 
				noticeDTO.getUpdatedAt(),
				noticeDTO.getMemberDTO().getUsername(),
				noticeDTO.getViews(), 
				ar);
	}

	@Transactional
	public NoticeDTO add(NoticeDTORequestDTO n, MultipartFile[] attach) throws Exception {
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setTitle(n.getTitle());
		noticeDTO.setContent(n.getContent());
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setUsername(n.getUsername());
		noticeDTO.setMemberDTO(memberDTO);

		noticeDTO = noticeRepository.save(noticeDTO);

		if (attach != null) {
			for (MultipartFile file : attach) {
				if (!file.isEmpty()) {
					// 파일 저장 로직 구현부
				}
			}
		}

		return noticeDTO;
	}

	public List<NoticeDTOResponseDTO> list() throws Exception {
		List<NoticeDTO> ar = noticeRepository.findAll();
		
		return ar.stream().map(n -> new NoticeDTOResponseDTO(
				n.getId(), 
				n.getMemberDTO().getUsername(), 
				n.getTitle(),
				n.getViews(), 
				n.getCreatedAt()
		)).collect(Collectors.toList());
	}

}