package com.anil.backend.apis;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anil.backend.apis.pojos.ResponseMesage;
import com.anil.backend.models.User;
import com.anil.backend.repository.UserRepo;

/**
 * This is a test controller to see if the spring project is properly
 * configured or not.
 * @author ausguardian
 *
 */
@RestController
@RequestMapping("/")
public class UserController extends AbstractController<User> {
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping("/findById")
	public ResponseEntity<ResponseMesage> findById(@RequestParam(value = "Id", defaultValue = "1") Long id) {
	 Optional<User> user= userRepo.findById(id);
	 ResponseMesage responseMessage=ResponseMesage.of("The user does not exists on the database", null);
	 ResponseEntity<ResponseMesage> local= new ResponseEntity<>(responseMessage,HttpStatus.BAD_REQUEST);
	 return  user.map(this::map).orElse(local);
	}
	
	@GetMapping("/findByUserName")
	public ResponseEntity<ResponseMesage> findByUserName(@RequestParam(value = "userName", defaultValue = "backend") String userName) {
	 Optional<User> user= userRepo.findByUserName(userName);
	 ResponseMesage responseMessage=ResponseMesage.of("The user does not exists on the database", null);
	 ResponseEntity<ResponseMesage> local= new ResponseEntity<>(responseMessage,HttpStatus.BAD_REQUEST);
	 return  user.map(this::map).orElse(local);
	}
	@PostMapping("/createUser")
	public ResponseEntity<ResponseMesage> createUser(@RequestBody User user){
		user.setFeeds(new HashSet<>());
		userRepo.save(user);
		return this.map(user);
	}
}
