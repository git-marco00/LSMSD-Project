package it.unipi.BGnet.repository.mongoDB;

import it.unipi.BGnet.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {
    @Autowired
    private IPostRepository postMongo;

    public IPostRepository getMongo(){
        return postMongo;
    }

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

    public List<Post> findAllPosts(){
        List<Post> result =  new ArrayList<>();
        result = postMongo.findAll();
        return result;
    }

}
