package it.unipi.BGnet.controllers.api;

import com.google.gson.Gson;
import it.unipi.BGnet.DTO.PostDTO;
import it.unipi.BGnet.Utilities.Constants;
import it.unipi.BGnet.Utilities.SessionVariables;
import it.unipi.BGnet.service.pages.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SessionAttributes("sessionVariables")
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

    @RequestMapping("/api/getPost")
    public String getPostList(Model model, @RequestParam(value = "page") int pageNumber){
        if(model.getAttribute(Constants.CURRENT_GAME) == null){
            return null;
        }
        SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
        List<PostDTO> postList = postService.loadPostPage(sv.gameToDisplay, pageNumber);
        sv.currentPage = pageNumber;
        model.addAttribute("sessionVariables", sv);
        Gson gson = new Gson();
        String result = gson.toJson(postList);
        System.out.println(result);
        return result;
    }
}
