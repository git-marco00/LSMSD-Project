package it.unipi.BGnet.repository;

import it.unipi.BGnet.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IGameRepository extends MongoRepository<Game, String> {
    // TO DO: Add all functions signatures involving a DB operation regarding this entity
    Optional<Game> findByName(String name);
}
