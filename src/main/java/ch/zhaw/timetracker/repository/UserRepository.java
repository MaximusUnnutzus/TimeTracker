package ch.zhaw.timetracker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ch.zhaw.timetracker.entities.User;



public interface UserRepository extends MongoRepository<User, String> {
	
}
