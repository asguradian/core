package com.anil.backend.apis;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anil.backend.apis.pojos.ResponseMesage;
import com.anil.backend.constants.ExceptionEnum;
import com.anil.backend.exception.UserNotFoundException;
import com.anil.backend.models.Feed;
import com.anil.backend.models.User;
import com.anil.backend.repository.FeedRepository;
import com.anil.backend.repository.UserRepo;

@RestController
@RequestMapping("/")
public class FeedController extends AbstractController<Feed> {
   private static  Map<ExceptionEnum, String> messageMap=null;
   private static Map<ExceptionEnum,HttpStatus> statusMap=null;
   static {
    messageMap= new HashMap<>();
    statusMap=new HashMap<>();
   }
	@Autowired 
	private UserRepo userRepo;
	@Autowired
	private FeedRepository feedRepository;
	
	@GetMapping("/feeds")
	public ResponseEntity<ResponseMesage> getAllFeeds(@RequestParam(name="UserId") Long userId){
	    Optional<User> user= userRepo.findById(userId);
		return user.map(feedRepository::findByUser).map(this::map).orElseThrow(()->new UserNotFoundException(ExceptionEnum.FEED100.getMessage(), ExceptionEnum.FEED100));
	}
	@PostMapping("/feeds/create")
	public ResponseEntity<ResponseMesage> createFeed(@RequestParam(name="Discription") String discription, @RequestParam(name="DocumentId") Long documentId,@RequestParam(name="UserId") Long userId){
		Optional<User> userOptional= userRepo.findById(userId);
		User user= userOptional.orElseThrow(()->new UserNotFoundException(ExceptionEnum.FEED101.getMessage(),ExceptionEnum.FEED101));
		Feed feed= Feed.by(user, discription,documentId);
		feedRepository.save(feed);
		return new ResponseEntity<ResponseMesage>(ResponseMesage.of("The feed successfully registered at the database.",null), HttpStatus.OK);
	}
	@Override
	public Map<ExceptionEnum, HttpStatus> getExceptionMap() {
		// TODO Auto-generated method stub
		return FeedController.statusMap;
	}
	@Override
	public Map<ExceptionEnum, String> getMessageMap() {
		// TODO Auto-generated method stub
		return FeedController.messageMap;
	}
}
