package it.unipi.BGnet.service.user;

import it.unipi.BGnet.model.Post;
import it.unipi.BGnet.repository.GameRepository;
import it.unipi.BGnet.repository.UserRepository;
import it.unipi.BGnet.repository.mongoDB.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
