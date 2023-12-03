package com.mangopay.idea.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mangopay.idea.model.IdeaDocument;

public interface IdeaRepository extends MongoRepository<IdeaDocument, String> {

}
