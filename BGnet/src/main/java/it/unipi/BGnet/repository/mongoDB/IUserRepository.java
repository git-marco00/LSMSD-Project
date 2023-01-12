package it.unipi.BGnet.repository.mongoDB;

import it.unipi.BGnet.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<User, String> {
    // TO DO: Add all functions signatures involving a DB operation regarding this entity
    User findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
