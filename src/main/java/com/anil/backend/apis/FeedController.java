package com.anil.backend.apis;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anil.backend.apis.pojos.ResponseMesage;
import com.anil.backend.models.Feed;
import com.anil.backend.models.User;
import com.anil.backend.repository.FeedRepository;
import com.anil.backend.repository.UserRepo;

@RestController
@RequestMapping("/")
public class FeedController extends AbstractController<Feed> {
   private static ResponseMesage defaultMessage= ResponseMesage.of("There is no post done by this Users Yet.", null);
   private static ResponseEntity<ResponseMesage> defaultResponseMessage= new ResponseEntity<>(defaultMessage, HttpStatus.BAD_REQUEST);
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private FeedRepository feedRepository;
	
	
	
	@GetMapping("/feeds")
	public ResponseEntity<ResponseMesage> getAllFeeds(@RequestParam(name="UserId") Long userId){
	    Optional<User> user= userRepo.findById(userId);
		return user.map(feedRepository::findByUser).map(this::map).orElse(FeedController.defaultResponseMessage);
	}
}
