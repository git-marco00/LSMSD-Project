package it.unipi.BGnet.service.pages;

import it.unipi.BGnet.DTO.PostDTO;
import it.unipi.BGnet.Utilities.Constants;
import it.unipi.BGnet.model.Post;
import it.unipi.BGnet.repository.mongoDB.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("mainPostService")
public class PostService {
    @Autowired
    PostRepository postRepo;

    Logger logger = LoggerFactory.getLogger(PostService.class);
    public List<PostDTO> loadPostPage(String gameName, int pageNumber){
        AbstractPageRequest pageRequest = PageRequest.of(pageNumber, Constants.PAGE_SIZE);
        List<Post> result = postRepo.findByGame(gameName, pageRequest).getContent();
        logger.warn(Integer.toString(result.size()));
        List<PostDTO> listView = new ArrayList<>();
        for (Post value : result) {
            PostDTO post = new PostDTO();
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
}
