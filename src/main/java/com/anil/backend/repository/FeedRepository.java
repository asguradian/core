package com.anil.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.anil.backend.models.Feed;
import com.anil.backend.models.User;

@Repository
public interface FeedRepository  extends CrudRepository<Feed, Long>{

public List<Feed> findByUser(User user);	
	
}
