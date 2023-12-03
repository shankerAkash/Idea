package com.mangopay.idea.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mangopay.idea.model.User;
import com.mangopay.idea.service.CustomUserDetailsService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {

	private CustomUserDetailsService userService;
	
	private static final String BASE_URI = "/auth";
	
	@GetMapping(BASE_URI + "/users")
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok().body(userService.getUsers());
	}
	
	@PostMapping(BASE_URI + "/user")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		return ResponseEntity.ok().body(userService.addUser(user));
	}

}
