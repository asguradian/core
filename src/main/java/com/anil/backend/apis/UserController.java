package com.anil.backend.apis;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
import com.anil.backend.constants.ExceptionEnum;
import com.anil.backend.exception.UserNotFoundException;
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
	private static  Map<ExceptionEnum, String> messageMap=null;
	   private static Map<ExceptionEnum,HttpStatus> statusMap=null;
	   static {
	    messageMap= new HashMap<>();
	    statusMap=new HashMap<>();
	   }
	
	@GetMapping("/findById")
	public ResponseEntity<ResponseMesage> findById(@RequestParam(value = "Id", defaultValue = "1") Long id) {
	 Optional<User> user= userRepo.findById(id);
	 return  user.map(this::map).orElseThrow(()-> new UserNotFoundException(ExceptionEnum.USER100.getMessage(),ExceptionEnum.USER100));
	}
	
	@GetMapping("/findByUserName")
	public ResponseEntity<ResponseMesage> findByUserName(@RequestParam(value = "userName", defaultValue = "backend") String userName) {
	 Optional<User> user= userRepo.findByUserName(userName);
	 return  user.map(this::map).orElseThrow(()-> new UserNotFoundException(ExceptionEnum.USER100.getMessage(),ExceptionEnum.USER100));
	}
	@PostMapping("/createUser")
	public ResponseEntity<ResponseMesage> createUser(@RequestBody User user){
		user.setFeeds(new HashSet<>());
		userRepo.save(user);
		return this.map(user);
	}
	@Override
	public Map<ExceptionEnum, HttpStatus> getExceptionMap() {
		// TODO Auto-generated method stub
		return UserController.statusMap;
	}
	@Override
	public Map<ExceptionEnum, String> getMessageMap() {
		// TODO Auto-generated method stub
		return UserController.messageMap;
	}
}
