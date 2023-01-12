package it.unipi.BGnet.repository.mongoDB;

import it.unipi.BGnet.model.Comment;
import it.unipi.BGnet.model.Post;
import it.unipi.BGnet.model.User;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {
    @Autowired
    private IPostRepository postMongo;

    public IPostRepository getMongo(){
        return postMongo;
    }

    Logger logger = LoggerFactory.getLogger(PostRepository.class);

    // CRUD Methods
    //  -------------------------------------------------------------------------------------------------
    public boolean addPost(Post post){
        boolean result = true;
        try {
            postMongo.save(post);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public boolean deletePostById(String id) {
        boolean result = true;
        try {
            postMongo.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public boolean deletePost(Post post) {
        boolean result = true;
        try {
            postMongo.delete(post);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public Optional<Post> getPostById(String id){
        Optional<Post> post = Optional.empty();
        try {
            post = postMongo.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

    public List<Post> findAllPosts(){
        List<Post> result = new ArrayList<>();
        try {
            result.addAll(postMongo.findAll());
        } catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public List<Post> findByGame(String game){
        List<Post> result = new ArrayList<>();
        try{
            result.addAll(postMongo.findByGameOrderByTimestampDesc(game));
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public Page<Post> findByGame(String game, Pageable page){
        Page<Post> result = null;
        try {
            result = postMongo.findByGameOrderByTimestampDesc(game, page);
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    // ---------------------------------------------------------------------------------------------------


    // Application-Domain Methods
    // ---------------------------------------------------------------------------------------------------
    public boolean likePost(Post post, User user){
        boolean result = true;
        try{
            post.addLike(user.getUsername());
            postMongo.save(post);
        } catch(Exception e){
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public boolean unlikePost(Post post, User user){
        boolean result = true;
        try{
            post.removeLike(user.getUsername());
            postMongo.save(post);
        } catch(Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public boolean addComment(Post post, Comment comment){
        boolean result = true;
        try{
            post.addComment(comment);
            postMongo.save(post);
        } catch(Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public boolean deleteComment(Post post, Comment comment){
        boolean result = true;
        try{
            post.removeComment(comment);
            postMongo.save(post);
        } catch(Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
    // ---------------------------------------------------------------------------------------------------


}
