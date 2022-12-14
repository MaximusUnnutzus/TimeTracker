package ch.zhaw.timetracker.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.timetracker.model.User;
import ch.zhaw.timetracker.model.UserCreateDTO;
import ch.zhaw.timetracker.repository.UserRepository;


@RestController
@RequestMapping("api/user")
public class UserRestController {
	@Autowired
	UserRepository UserRepository;

	    // what is a DAO
		@PostMapping("/create")
		public ResponseEntity<User> createUser(
				@RequestBody UserCreateDTO uDTO) {
			User uDAO = new User(uDTO.getLoginname(), uDTO.getSurname(), uDTO.getName(), uDTO.getPassword(), uDTO.getEmail());
			//
			User u = UserRepository.save(uDAO);
			return new ResponseEntity<>(u, HttpStatus.CREATED);
		}
	
		// Get all Jobs
		@GetMapping("/all")
		public ResponseEntity<List<User>> getAllUsers() {
			List<User> allFree = UserRepository.findAll();
			return new ResponseEntity<>(allFree, HttpStatus.OK);
		}
	
		// Get specific Job
		@GetMapping("/{id}")
		public ResponseEntity<User> getJobById(@PathVariable String id) {
			Optional<User> optUser = UserRepository.findById(id);
			if (optUser.isPresent()) {
				return new ResponseEntity<>(optUser.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}

	
}


