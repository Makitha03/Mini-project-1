package org.user.app.service;

import java.util.List;
import org.user.app.model.User;

import java.util.Optional;

public interface UserService {

	public User addUser(User user);
	public List<User> getUsers();
	public Optional<User> getUserById(Long id);
	public User updateUser(User user);
	public void deleteUserById(Long id);
}