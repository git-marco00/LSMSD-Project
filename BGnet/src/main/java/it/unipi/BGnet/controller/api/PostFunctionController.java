package it.unipi.BGnet.controller.api;

import com.google.gson.Gson;
import it.unipi.BGnet.Utilities.Constants;
import it.unipi.BGnet.model.Post;
import it.unipi.BGnet.repository.mongoDB.PostRepository;
import it.unipi.BGnet.service.pages.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@RestController
@SessionAttributes(Constants.CURRENT_PAGE_NUMBER)
public class PostFunctionController {

    @Autowired
    PostService postService;
/*
    @RequestMapping("/api/addPost")
    public Post savePost(){
        Post post = new Post("idProva", "autoreProva", "giocoProva", "autoreProva");
        boolean result = postRepo.addPost(post);
        if(result)
            return post;
        else
            return null;
    }

 */

    @RequestMapping("/api/getPost/{page}")
    public String getPostList(Model model, @RequestParam(value = "page") int pageNumber){
        if(model.getAttribute(Constants.CURRENT_GAME) == null){
            return null;
        }
        List<Post> postList = postService.loadPostPage((String)model.getAttribute(Constants.CURRENT_GAME), pageNumber);
        model.addAttribute(Constants.CURRENT_PAGE_NUMBER, pageNumber);
        Gson gson = new Gson();
        String result = gson.toJson(postList);
        return result;
    }
}
