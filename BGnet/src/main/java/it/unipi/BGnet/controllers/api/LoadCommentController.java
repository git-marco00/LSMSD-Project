package it.unipi.BGnet.controllers.api;

import com.google.gson.Gson;

import it.unipi.BGnet.DTO.PostDTO;
import it.unipi.BGnet.service.pages.PostService;

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
}
