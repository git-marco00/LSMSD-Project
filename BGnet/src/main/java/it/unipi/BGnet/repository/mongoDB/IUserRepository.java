package it.unipi.BGnet.repository.mongoDB;

import it.unipi.BGnet.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IUserRepository extends MongoRepository<User, String> {
    // TO DO: Add all functions signatures involving a DB operation regarding this entity
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
