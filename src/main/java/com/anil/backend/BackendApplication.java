package com.anil.backend;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import org.assertj.core.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.anil.backend.models.Demography;
import com.anil.backend.models.Orientation;
import com.anil.backend.models.User;
import com.anil.backend.models.UserAddress;
import com.anil.backend.models.UserInfo;
import com.anil.backend.repository.UserRepo;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	@Bean
    public CommandLineRunner bookDemo(UserRepo userRepo) {
        return (args) -> {
          Demography demography= Demography.from(Orientation.MALE,false);
          UserInfo userInfo= UserInfo.create("Anil","Acharya","anil.acharya6851@gmail.com");
          UserAddress userAddress= UserAddress.create("USA","Arizona");
          User user= new User();
          user.setActive(true);
          user.setCreatedDate(new Date());
          user.setDemography(demography);
          user.setUserAddress(userAddress);
          user.setUserInfo(userInfo);
          user.setUserName("Anil6851");
          user.setFeeds(new HashSet<>());
          User userSaved=userRepo.save(user);
          user= userRepo.findById(userSaved.getId()).get();
          System.out.println(user.getId());
        //  System.out.println(user.toString());
        };
	}
}


