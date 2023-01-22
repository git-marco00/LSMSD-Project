package it.unipi.BGnet.service.pages;

import it.unipi.BGnet.Utilities.SessionVariables;
import it.unipi.BGnet.model.Post;
import it.unipi.BGnet.DTO.PostDTO;
import it.unipi.BGnet.model.Comment;
import it.unipi.BGnet.DTO.CommentDTO;
import it.unipi.BGnet.Utilities.Constants;
import it.unipi.BGnet.repository.GameRepository;
import it.unipi.BGnet.repository.UserRepository;
import it.unipi.BGnet.repository.mongoDB.PostRepository;

import org.springframework.ui.Model;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("mainPostService")
@SessionAttributes("sessionVariables")
public class PostService {
    @Autowired
    PostRepository postRepo;
    @Autowired
    GameRepository gameRepo;
    @Autowired
    UserRepository userRepo;
    public boolean addPost(String game, String author, String text){
        Post toAdd = new Post(author, game, text);
        Post saved = null;
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
    public int likeUnlikePost(String id, String username, String game){
        Optional<Post> older = postRepo.getPostById(id);
        if(older.isEmpty())
            return -1;
        boolean flag = false;
        for(String like : older.get().getLikes())
            if(like.equals(username))
                flag = true;
        Post saved;
        if(flag)
            saved = postRepo.unlikePost(older.get(), username);
        else
            saved = postRepo.likePost(older.get(), username);
        if(saved == null)
            return -1;
        gameRepo.updatePost(game, older.get(), saved);
        userRepo.updatePost(username, older.get(), saved);
        return (flag) ? 2 : 1;
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
    public List<PostDTO> loadPostPage(Model model, String gameName, int pageNumber){
        AbstractPageRequest pageRequest = PageRequest.of(pageNumber, Constants.PAGE_SIZE);
        List<Post> result = postRepo.findByGame(gameName, pageRequest).getContent();
        List<PostDTO> listView = new ArrayList<>();
        SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
        for(Post value : result) {
            PostDTO post = new PostDTO(value.getId(), value.getGame(), value.getAuthor(), value.getLikes().size(), value.getComments().size(), value.getTimestamp(), value.getText(), false);
            if(sv != null && sv.myself != null)
                if(value.getLikes().contains(sv.myself))
                    post.setHasLiked(true);
            listView.add(post);
        }
        return listView;
    }
    public int loadNumberOfPages(String game){
        return postRepo.countPages(game);
    }
    public PostDTO loadComments(Model model, String _id) {
        Optional<Post> result = postRepo.getPostById(_id);
        if(result.isEmpty())
            return null;
        List<CommentDTO> comments = new ArrayList<>();
        for(Comment comment: result.get().getComments()) {
            CommentDTO tmp = new CommentDTO(comment.getAuthor(), comment.getText(), comment.getDateTime());
            comments.add(tmp);
        }
        boolean flag = false;
        SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
        if(sv != null && sv.myself != null)
            flag = result.get().getLikes().contains(sv.myself);
        return new PostDTO(result.get().getId(), result.get().getGame(), result.get().getAuthor(), result.get().getLikes().size(), comments, result.get().getTimestamp(), result.get().getText(), flag);
    }
}

