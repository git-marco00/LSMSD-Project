package it.unipi.BGnet.controllers.api;

import com.google.gson.Gson;

import it.unipi.BGnet.DTO.PostDTO;
import it.unipi.BGnet.Utilities.SessionVariables;
import it.unipi.BGnet.service.pages.PostService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SessionAttributes("sessionVariables")
public class LoadPostController {
    @Autowired
    PostService postService;
    Logger logger = LoggerFactory.getLogger(LoadPostController.class);

    @RequestMapping("/api/addPost")
    public boolean savePost(Model model, @RequestParam("game") String game, @RequestParam("text")String text){
        String author = ((SessionVariables) model.getAttribute("sessionVariables")).myself;
        return postService.addPost(game, author, text);
    }


    @GetMapping("/api/getPost")
    public String getPostList(Model model, @RequestParam(value = "page") int pageNumber){
        if(((SessionVariables) model.getAttribute("sessionVariables")).gameToDisplay == null)
            return null;
        SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
        List<PostDTO> postList = postService.loadPostPage(sv.gameToDisplay, pageNumber);
        sv.currentPage = pageNumber;
        model.addAttribute("sessionVariables", sv);
        Gson gson = new Gson();
        String result = gson.toJson(postList);
        return result;
    }

    @RequestMapping("/api/getPages")
    public String getHowManyPages(Model model){
        if(((SessionVariables) model.getAttribute("sessionVariables")).gameToDisplay == null){
            return null;
        }
        SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
        int pages = postService.loadNumberOfPages(sv.gameToDisplay);
        logger.warn("PAGES: " + Integer.toString(pages));
        Gson gson = new Gson();
        String result = gson.toJson(pages);
        return result;
    }
}
