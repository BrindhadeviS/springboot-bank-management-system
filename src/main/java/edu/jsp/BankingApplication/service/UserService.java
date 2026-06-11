package edu.jsp.BankingApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.jsp.BankingApplication.entity.User;
import edu.jsp.BankingApplication.exception.ResourcesNotFoundException;
import edu.jsp.BankingApplication.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User getUserById(long id) {
		return userRepository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("User", "Id", id));
	}

	public User createUser(User u) {
		return userRepository.save(u);
	}

	public String deleteUserById(Long id) {
		User u = userRepository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("User", "Id", id));

		userRepository.deleteById(id);

		return "Data deleted";
	}

	public List<User> searchUsersByName(String name) {
		return userRepository.findByName(name);
	}

}
