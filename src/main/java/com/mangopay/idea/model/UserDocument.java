package com.mangopay.idea.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Document(collection = "users")
@Data
@Builder
public class UserDocument {

	@Id
	private String id = UUID.randomUUID().toString();
	private String username;
	private String password;
	private String role;

}
