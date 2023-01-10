package it.unipi.BGnet.service.pages;

import it.unipi.BGnet.Utilities.Constants;
import it.unipi.BGnet.model.Post;
import it.unipi.BGnet.repository.mongoDB.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("mainPostService")
public class PostService {
    @Autowired
    PostRepository postRepo;

    public List<Post> loadPostPage(String gameName, int pageNumber){
        AbstractPageRequest pageRequest = PageRequest.of(pageNumber, Constants.PAGE_SIZE);
        Page<Post> result = postRepo.findByGame(gameName, pageRequest);
        return result.getContent();
    }
}
