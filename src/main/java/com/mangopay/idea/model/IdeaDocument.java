package com.mangopay.idea.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Document(collection = "ideas")
@Data
@Builder
public class IdeaDocument {
	
	@Id
    private String id = UUID.randomUUID().toString();
    private String title;
    private String description;
    private String createdBy;
    private LocalDateTime createdAt;
    private Integer likes;
    private Integer comments;

}
