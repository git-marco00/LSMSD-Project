package it.unipi.BGnet.controllers.api;

import com.google.gson.Gson;

import it.unipi.BGnet.DTO.CommentDTO;
import it.unipi.BGnet.service.pages.PostService;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class LoadCommentController {
    @Autowired
    PostService postService;
    @GetMapping("api/loadPostComments")
    public @ResponseBody String comments(@RequestParam("id") String _id) {
        List<CommentDTO> comments = postService.loadComments(_id);
        return new Gson().toJson(comments);
    }
}
