package com.winter.app.notice;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "notice_attachments")
public class NoticeFileDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String contentType;
	
	@Column(updatable = false)
	@CreatedDate
	private LocalDateTime createdAt;
	@Column
	private Long filesize;
	@Column
	private String originalFileName;
	@Column
	private String storeFileName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "notice_id")
	private NoticeDTO noticeDTO;
	
}
