package com.example.jpa.demo.jpa_practice.base.classes;

import java.time.LocalDateTime;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;

public class BaseRefType {
	@Column(name = "created_at", updatable = false)
	@CreatedDate
	private LocalDateTime createdAt;
	@Column(name = "updated_at", insertable = false)
	private LocalDateTime updatedAt;
}
