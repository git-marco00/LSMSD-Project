package it.unipi.BGnet.service.user;

import it.unipi.BGnet.model.Game;
import it.unipi.BGnet.model.Post;
import it.unipi.BGnet.model.User;
import it.unipi.BGnet.repository.GameRepository;
import it.unipi.BGnet.repository.UserRepository;
import it.unipi.BGnet.repository.mongoDB.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("mainManagerService")
public class ManagerService {
    @Autowired
    private GameRepository gameRepo;
    @Autowired
    private PostRepository postRepo;
    @Autowired
    private UserRepository userRepo;

    public boolean deletePost(String id){
        Optional<Post> toDelete = postRepo.getPostById(id);
        if(toDelete.isEmpty())
            return false;

        if(!postRepo.deletePostById(id))
            return false;

        if(!userRepo.removePost(toDelete.get().getAuthor(), toDelete.get())) {
            postRepo.addPost(toDelete.get());
            return false;
        }
        if(!gameRepo.removePost(toDelete.get().getGame(), toDelete.get())){
            postRepo.addPost(toDelete.get());
            userRepo.addPost(toDelete.get().getAuthor(), toDelete.get());
            return false;
        }
        return true;
    }

    public boolean deleteUser(String name){
        Optional<User> toDelete = userRepo.getUserByUsername(name);
        if(toDelete.isEmpty())
            return false;

        if(!userRepo.deleteUser(toDelete.get()))
            return false;

        return true;
    }

    public boolean deleteGame(String name){
        Optional<Game> toDelete = gameRepo.getGameByName(name);
        if(toDelete.isEmpty())
            return false;

        if(!gameRepo.deleteGameMongo(toDelete.get()))
            return false;

        return true;
    }

    public boolean addGame(String name, String designer, int yearPublished, int minPlayers, int maxPlayers,
                           String playingTime, String minPlayTime, String maxPlayTime, List<String> categories,
                           String description, String img){
        Game newGame = new Game(name, designer, yearPublished, minPlayers, maxPlayers, playingTime, minPlayTime, maxPlayTime, categories, description, img);
        if(!gameRepo.addGameMongo(newGame))
            return false;

        if(!gameRepo.createNewGameNeo4j(name)) {
            gameRepo.deleteGameMongo(newGame);
            return false;
        }

        return true;
    }

}
