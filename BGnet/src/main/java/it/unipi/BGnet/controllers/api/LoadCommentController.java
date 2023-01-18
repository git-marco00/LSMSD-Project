package it.unipi.BGnet.controllers.api;

import com.google.gson.Gson;

import it.unipi.BGnet.DTO.PostDTO;
import it.unipi.BGnet.Utilities.SessionVariables;
import it.unipi.BGnet.service.pages.PostService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class LoadCommentController {
    @Autowired
    PostService postService;
    @GetMapping("api/loadPostComments")
    public @ResponseBody String comments(@RequestParam("id") String _id) {
        PostDTO post = postService.loadComments(_id);
        return new Gson().toJson(post);
    }
    @GetMapping("api/addComment")
    public @ResponseBody boolean addComment(Model model, @RequestParam("post") String post, @RequestParam("game") String game, @RequestParam("comment") String comment) {
        SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
        if(sv.myself == null)
            return false;
        if(postService.addComment(post, sv.myself, game, comment))
            return true;
        else return false;
    }
}
