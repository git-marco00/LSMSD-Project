package it.unipi.BGnet.service.pages;

import it.unipi.BGnet.model.Game;
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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("mainPostService")
public class PostService {
    @Autowired
    PostRepository postRepo;
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
    public List<CommentDTO> loadComments(String _id) {
        Optional<Post> result = postRepo.getPostById(_id);
        if(result.isEmpty())
            return null;
        List<CommentDTO> comments = new ArrayList<>();
        for(Comment comment: result.get().getComments()) {
            CommentDTO tmp = new CommentDTO(comment.getAuthor(), comment.getText(), comment.getDateTime());
            comments.add(tmp);
        }
        return comments;
    }
}
