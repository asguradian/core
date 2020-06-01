package com.anil.backend.repository;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.anil.backend.models.User;
@Repository
public interface UserRepo extends CrudRepository<User,Long> {
	public Optional<User> findByUserName(String userName);
}
