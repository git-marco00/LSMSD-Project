package it.unipi.BGnet.repository.mongoDB;

import it.unipi.BGnet.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IPostRepository extends MongoRepository<Post, String> {
    Page<Post> findByGameOrderByTimestampDesc(String game, Pageable pageable);
    List<Post> findByGameOrderByTimestampDesc(String game);

    int countByGame(String game);
}
