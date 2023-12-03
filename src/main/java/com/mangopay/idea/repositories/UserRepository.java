package com.mangopay.idea.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mangopay.idea.model.IdeaDocument;
import com.mangopay.idea.model.UserDocument;

public interface UserRepository extends MongoRepository<UserDocument, String> {
	
	 UserDocument findByUsername(String username);

}
