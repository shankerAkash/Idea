package com.mangopay.idea.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mangopay.idea.model.Idea;
import com.mangopay.idea.model.IdeaDocument;
import com.mangopay.idea.repositories.IdeaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IdeaService {

	private IdeaRepository ideaRepository;

	public List<Idea> getIdeas() {

		List<Idea> ideaList = new ArrayList<>();

		List<IdeaDocument> ideas = ideaRepository.findAll();
		for (IdeaDocument idea : ideas) {
			ideaList.add(Idea.builder().id(idea.getId()).title(idea.getTitle()).description(idea.getDescription())
					.createdBy(idea.getCreatedBy()).createdAt(idea.getCreatedAt().toString()).likes(idea.getLikes())
					.comments(idea.getComments()).build());
		}
		return ideaList;
	}

	public Idea getIdea(String id) {

		Optional<IdeaDocument> idea = ideaRepository.findById(id);

		if (!idea.isEmpty()) {
			return Idea.builder().id(idea.get().getId()).title(idea.get().getTitle())
					.description(idea.get().getDescription()).createdBy(idea.get().getCreatedBy())
					.createdAt(idea.get().getCreatedAt().toString()).likes(idea.get().getLikes())
					.comments(idea.get().getComments()).build();
		} else {
			return null;
		}
	}

	public Idea addIdea(Idea idea) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime createdAt = LocalDateTime.now();

		IdeaDocument ideaResponse = ideaRepository.save(IdeaDocument.builder().title(idea.getTitle())
				.description(idea.getDescription()).createdBy(idea.getCreatedBy()).createdAt(createdAt)
				.likes(idea.getLikes()).comments(idea.getComments()).build());

		return Idea.builder().id(ideaResponse.getId()).title(ideaResponse.getTitle())
				.description(ideaResponse.getDescription()).createdBy(ideaResponse.getCreatedBy())
				.createdAt(ideaResponse.getCreatedAt().toString()).likes(ideaResponse.getLikes())
				.comments(ideaResponse.getComments()).build();
	}

	public Boolean deleteIdea(String id) {

		Optional<IdeaDocument> idea = ideaRepository.findById(id);

		if (!idea.isEmpty()) {
			ideaRepository.deleteById(id);
			Optional<IdeaDocument> ideaRes = ideaRepository.findById(id);
			if (ideaRes.isEmpty()) {
				return true;
			}
		}

		return false;
	}

	public Idea updateIdea(Idea idea) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime createdAt = LocalDateTime.parse(idea.getCreatedAt(), formatter);

		IdeaDocument ideaResponse = ideaRepository.save(IdeaDocument.builder().id(idea.getId()).title(idea.getTitle())
				.description(idea.getDescription()).createdBy(idea.getCreatedBy()).createdAt(createdAt)
				.likes(idea.getLikes()).comments(idea.getComments()).build());

		return Idea.builder().id(ideaResponse.getId()).title(ideaResponse.getTitle())
				.description(ideaResponse.getDescription()).createdBy(ideaResponse.getCreatedBy())
				.createdAt(ideaResponse.getCreatedAt().toString()).likes(ideaResponse.getLikes())
				.comments(ideaResponse.getComments()).build();
	}

}
