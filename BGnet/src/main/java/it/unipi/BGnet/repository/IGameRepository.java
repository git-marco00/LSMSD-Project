package it.unipi.BGnet.repository;

import it.unipi.BGnet.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IGameRepository extends MongoRepository<Game, String> {
    // TO DO: Add all functions signatures involving a DB operation regarding this entity

}
