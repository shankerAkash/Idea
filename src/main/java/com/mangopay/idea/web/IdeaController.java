package com.mangopay.idea.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.mangopay.idea.api.IdeaApi;
import com.mangopay.idea.model.Idea;
import com.mangopay.idea.service.IdeaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class IdeaController implements IdeaApi {

	private IdeaService ideaService;

	@Override
	public ResponseEntity<List<Idea>> getIdeas() {
		return ResponseEntity.ok().body(ideaService.getIdeas());
	}

	@Override
	public ResponseEntity<Idea> getIdea(String id) {
		return ResponseEntity.ok().body(ideaService.getIdea(id));
	}

	@Override
	public ResponseEntity<Idea> addIdea(Idea idea) {
		return ResponseEntity.ok().body(ideaService.addIdea(idea));
	}

	@Override
	public ResponseEntity<Void> deleteIdea(String id) {
		boolean isDeleted = ideaService.deleteIdea(id);
		if (isDeleted) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	public ResponseEntity<Idea> updateIdea(Idea idea) {
		return ResponseEntity.ok().body(ideaService.updateIdea(idea));
	}

}
