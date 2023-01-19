package it.unipi.BGnet.controllers.api;

import com.google.gson.Gson;

import it.unipi.BGnet.DTO.PostDTO;
import it.unipi.BGnet.Utilities.SessionVariables;
import it.unipi.BGnet.service.pages.PostService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@SessionAttributes("sessionVariables")
public class LoadCommentController {
    @Autowired
    PostService postService;
    @GetMapping("api/loadPostComments")
    public @ResponseBody String comments(@RequestParam("id") String _id) {
        PostDTO post = postService.loadComments(_id);
        return new Gson().toJson(post);
    }
    @PostMapping("api/addComment")
    public @ResponseBody boolean addComment(Model model, @RequestParam("post_id") String post, @RequestParam("game_name") String game, @RequestParam("text") String comment) {
        SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
        if(sv == null || sv.myself == null) {
            System.out.println("USER NOT LOGGED");
            return false;
        }
        if(postService.addComment(post, sv.myself, game, comment)) {
            return true;
        }
        else {
            System.out.println("WRITING HAS FAILED");
            return false;
        }
    }
}
