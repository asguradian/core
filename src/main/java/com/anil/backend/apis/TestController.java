package com.anil.backend.apis;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anil.backend.apis.pojos.ResponseMesage;

/**
 * This is a test controller to see if the spring project is properly
 * configured or not.
 * @author ausguardian
 *
 */
@RestController
@RequestMapping("/")
public class TestController {	
	@GetMapping("/echo")
	public ResponseEntity<ResponseMesage> echo(@RequestParam(value = "name", defaultValue = "World") String name) {
	 return new ResponseEntity<>(ResponseMesage.of(name, null), HttpStatus.OK);
	}
}
