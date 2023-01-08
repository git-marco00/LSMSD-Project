package it.unipi.BGnet.controller.api;

import it.unipi.BGnet.model.Post;
import it.unipi.BGnet.repository.mongoDB.PostRepository;
import it.unipi.BGnet.service.pages.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostFunctionController {

    @Autowired
    PostRepository postRepo;

    @RequestMapping("/api/addPost")
    public Post savePost(){
        Post post = new Post("idProva", "autoreProva", "giocoProva", "autoreProva");
        boolean result = postRepo.addPost(post);
        if(result)
            return post;
        else
            return null;
    }

    @RequestMapping("/api/getPost")
    public Iterable<Post> getPostList(){
        List<Post> result = postRepo.findAllPosts();
        return result;
    }
}
