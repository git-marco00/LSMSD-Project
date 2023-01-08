package it.unipi.BGnet.repository.mongoDB;

import it.unipi.BGnet.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IPostRepository extends MongoRepository<Post, Integer> {
    // TO DO: Add all functions signatures involving a DB operation regarding this entity
}
