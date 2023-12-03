package com.mangopay.idea.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mangopay.idea.model.Idea;
import com.mangopay.idea.model.IdeaDocument;
import com.mangopay.idea.model.User;
import com.mangopay.idea.model.UserDocument;
import com.mangopay.idea.repositories.UserRepository;
import com.mangopay.idea.util.CustomUserDetails;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserDocument user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		return new CustomUserDetails(user);
	}

	public List<User> getUsers() {

		List<User> userList = new ArrayList<>();

		List<UserDocument> users = userRepository.findAll();
		for (UserDocument user : users) {
			userList.add(User.builder().id(user.getId()).username(user.getUsername()).password(user.getPassword())
					.role(user.getRole()).build());
		}
		return userList;

	}

	public User addUser(User user) {

		String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
		UserDocument userResponse = userRepository.save(UserDocument.builder().username(user.getUsername())
				.password(encodedPassword).role(user.getRole()).build());

		return User.builder().id(userResponse.getId()).username(userResponse.getUsername())
				.password(userResponse.getPassword()).role(userResponse.getRole()).build();
	}

}
