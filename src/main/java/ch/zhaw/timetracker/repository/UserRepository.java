package ch.zhaw.timetracker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ch.zhaw.timetracker.model.User;

public interface UserRepository extends MongoRepository<User, String> {

}
