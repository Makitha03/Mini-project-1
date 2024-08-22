package org.user.app.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.user.app.model.User;
import org.user.app.repository.UserRepository;

@Service
public  class UserServiceImplementation implements UserService  {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User addUser(User user)
	{
		return this.userRepository.save(user);
	}

	@Override
	public List<User> getUsers() {
		return this.userRepository.findAll();
		
	}

	@Override
	public Optional<User> getUserById(Long id) {
		return this.userRepository.findById(id);
		
	}

	@Override
	public User updateUser(User user) {
		
		return this.userRepository.save(user);
	}

	@Override
	public void deleteUserById(Long id) {
		this.userRepository.deleteById(id);
		
	}
	
}