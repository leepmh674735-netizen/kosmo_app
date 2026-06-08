package com.winter.app.notice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.winter.app.members.MemberDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
@Table(name = "notices")
public class NoticeDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@Column(updatable = false)
	@CreatedDate
	private LocalDateTime createdAt;
	
	@Column(name = "is_pinned")
	private boolean isPinned = false; // 👈 r 제거 (Service의 isPinned()와 연결)
	
	@Column
	private String title;
	
	@LastModifiedDate
	private LocalDateTime updatedAt; // 👈 d 추가 (Service의 getUpdatedAt()과 연결)
	
	@Column
	private Long views = 0L; // 👈 s 추가 (Service의 getViews()와 연결)
	
	@ManyToOne
	@JoinColumn(name = "username") 
	private MemberDTO memberDTO;
	
	@OneToMany(mappedBy = "noticeDTO") // 👈 mappendBy -> mappedBy로 수정
	private List<NoticeFileDTO> attaches = new ArrayList<>();
	
}