package edu.jsp.BankingApplication.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.jsp.BankingApplication.entity.User;
import edu.jsp.BankingApplication.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserContoller {

	@Autowired
	private UserService userService;

	@GetMapping("/getById/{id}")
	public User getUserById( @PathVariable long id) {
		return userService.getUserById(id);
	}

	@PostMapping("/saveuser")
	public User createUser(@Valid @RequestBody User u) {
		return userService.createUser(u);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteUserById(@PathVariable Long id) {
		return userService.deleteUserById(id);
	}

	@GetMapping("/searchByName/{name}")
	public List<User> searchUsersByName(@PathVariable String name) {
		return userService.searchUsersByName(name);
	}

}
