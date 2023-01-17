package it.unipi.BGnet.repository;

import it.unipi.BGnet.model.Game;
import it.unipi.BGnet.model.Post;
import it.unipi.BGnet.model.User;
import it.unipi.BGnet.repository.mongoDB.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    @Autowired
    private IUserRepository userMongo;

    // CRUD Methods
    // ----------------------------------------------------------------------------------------
    public boolean addUser(User user){
        boolean result = true;
        try{
            userMongo.save(user);
        } catch (Exception e){
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public boolean deleteUser(User user) {
        boolean result = true;
        try {
            userMongo.delete(user);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public Optional<User> getUserByEmail(String email){
        Optional<User> user = Optional.empty();
        try {
            user = userMongo.findByEmail(email);
            System.out.println("\t\tUserRepository::getUserByEmail(String email) >> " + user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public Optional<User> getUserByUsername(String username){
        Optional<User> user = Optional.empty();
        try {
            user = userMongo.findByUsername(username);
            System.out.println("\t\tUserRepository::getUserByUsername(String username) >> " + user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean addPost(String username, Post post){
        Optional<User> user = getUserByUsername(username);
        if(user.isEmpty())
            return false;

        List<Post> list = user.get().getMostRecentPosts();
        list.add(0, post);
        list.remove(list.size() - 1);
        user.get().setMostRecentPosts(list);
        try{
            userMongo.save(user.get());
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public boolean updatePost(String name, Post olderPost, Post newPost){
        Optional<User> user = getUserByUsername(name);
        if(user.isEmpty())
            return false;

        List<Post> list = user.get().getMostRecentPosts();
        if(!list.contains(olderPost))
            return true;

        list.set(list.indexOf(olderPost), newPost);
        user.get().setMostRecentPosts(list);
        try{
            userMongo.save(user.get());
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
