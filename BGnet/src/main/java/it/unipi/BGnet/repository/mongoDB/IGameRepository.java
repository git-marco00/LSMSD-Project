package it.unipi.BGnet.repository.mongoDB;

import it.unipi.BGnet.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IGameRepository extends MongoRepository<Game, String> {
    // TO DO: Add all functions signatures involving a DB operation regarding this entity
    Optional<Game> findByName(String name);
    boolean existsByName(String name);
    List<Game> findAll();
    boolean existsByNameRegex(String name);

    int countByNameRegex(String name);

    List<Game> findByNameRegex(String pattern);
}
