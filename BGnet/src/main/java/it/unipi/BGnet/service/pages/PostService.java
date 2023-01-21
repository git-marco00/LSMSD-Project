package it.unipi.BGnet.service.pages;

import it.unipi.BGnet.model.Post;
import it.unipi.BGnet.DTO.PostDTO;
import it.unipi.BGnet.model.Comment;
import it.unipi.BGnet.DTO.CommentDTO;
import it.unipi.BGnet.Utilities.Constants;
import it.unipi.BGnet.repository.GameRepository;
import it.unipi.BGnet.repository.UserRepository;
import it.unipi.BGnet.repository.mongoDB.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("mainPostService")
public class PostService {
    @Autowired
    PostRepository postRepo;
    @Autowired
    GameRepository gameRepo;
    @Autowired
    UserRepository userRepo;
    Logger logger = LoggerFactory.getLogger(PostService.class);
    public boolean addPost(String game, String author, String text){
        Post toAdd = new Post(author, game, text);
        Post saved;

        boolean resultOnGame, resultOnUser;
        saved = postRepo.addPost(toAdd);
        if(saved == null)
            return false;

        resultOnGame = gameRepo.addPost(game, saved);
        if(!resultOnGame){
            postRepo.deletePostById(saved.getId());
            return false;
        }

        resultOnUser = userRepo.addPost(author, saved);
        if(!resultOnUser) {
            postRepo.deletePostById(saved.getId());
            gameRepo.removePost(game, saved);
            return false;
        }
        return true;
    }

    public boolean likePost(String id, String username, String game){
        Optional<Post> older = postRepo.getPostById(id);
        if(older.isEmpty())
            return false;
        for(String like : older.get().getLikes())
            if(like.equals(username))
                return false;
        Post saved = postRepo.likePost(older.get(), username);
        if(saved == null)
            return false;
        gameRepo.updatePost(game, older.get(), saved);
        userRepo.updatePost(username, older.get(), saved);
        return true;
    }

    public boolean addComment(String id, String username, String game, String text){
        Optional<Post> older = postRepo.getPostById(id);
        if(older.isEmpty())
            return false;

        Comment comment = new Comment(username, text);
        Post saved = postRepo.addComment(older.get(), comment);
        if(saved == null)
            return false;

        gameRepo.updatePost(game, older.get(), saved);
        userRepo.updatePost(username, older.get(), saved);
        return true;
    }


    public List<PostDTO> loadPostPage(String gameName, int pageNumber){
        AbstractPageRequest pageRequest = PageRequest.of(pageNumber, Constants.PAGE_SIZE);
        List<Post> result = postRepo.findByGame(gameName, pageRequest).getContent();
        logger.warn(Integer.toString(result.size()));
        List<PostDTO> listView = new ArrayList<>();
        for(Post value : result) {
            PostDTO post = new PostDTO(value.getId(), value.getGame(), value.getAuthor(), value.getLikes().size(), value.getComments().size(), value.getTimestamp(), value.getText());
            listView.add(post);
        }
        return listView;
    }

    public int loadNumberOfPages(String game){
        return postRepo.countPages(game);
    }
    public PostDTO loadComments(String _id) {
        Optional<Post> result = postRepo.getPostById(_id);
        if(result.isEmpty())
            return null;
        List<CommentDTO> comments = new ArrayList<>();
        for(Comment comment: result.get().getComments()) {
            CommentDTO tmp = new CommentDTO(comment.getAuthor(), comment.getText(), comment.getDateTime());
            comments.add(tmp);
        }
        return new PostDTO(result.get().getId(), result.get().getGame(), result.get().getAuthor(), result.get().getLikes().size(), comments, result.get().getTimestamp(), result.get().getText());
    }
}

