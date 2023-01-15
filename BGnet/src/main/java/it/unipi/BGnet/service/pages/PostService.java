package it.unipi.BGnet.service.pages;

import it.unipi.BGnet.model.Post;
import it.unipi.BGnet.DTO.PostDTO;
import it.unipi.BGnet.model.Comment;
import it.unipi.BGnet.DTO.CommentDTO;
import it.unipi.BGnet.Utilities.Constants;
import it.unipi.BGnet.repository.mongoDB.PostRepository;

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
        List<PostDTO> listView = new ArrayList<>();
        for(Post value : result) {
            PostDTO post = new PostDTO();
            post.setId(value.getId());
            post.setGame(value.getGame());
            post.setAuthor(value.getAuthor());
            post.setLikes(value.getLikes().size());
            post.setComments(value.getComments().size());
            post.setDate(value.getTimestamp());
            post.setText(value.getText());
            listView.add(post);
        }
        return listView;
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
