package it.unipi.BGnet.repository;

import it.unipi.BGnet.DTO.GameDTO;
import it.unipi.BGnet.DTO.UserDTO;
import it.unipi.BGnet.model.Game;
import it.unipi.BGnet.model.Post;
import it.unipi.BGnet.model.User;
import it.unipi.BGnet.repository.mongoDB.IUserRepository;
import it.unipi.BGnet.repository.neo4j.UserNeo4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.neo4j.driver.Record;

@Repository
public class UserRepository {
    @Autowired
    private IUserRepository userMongo;

    UserNeo4j userNeo4j = new UserNeo4j();

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
            System.out.println("\t\tUserRepository::getUserByUsername(String username) >> " + username);
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

    public boolean removePost(String username, Post post){
        Optional<User> user = getUserByUsername(username);
        if(user.isEmpty())
            return false;

        List<Post> list = user.get().getMostRecentPosts();
        list.remove(post);
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

    ////////// NEO4J PART ///////////////
    public boolean followUserByUsername(String usernameA, String usernameB){
        return userNeo4j.followUserByUsername(usernameA, usernameB);
    }
    public boolean unfollowUserByUsername(String usernameA, String usernameB){
        return userNeo4j.unfollowUserByUsername(usernameA, usernameB);
    }
    public int findFollowerNumberByUsername(String username){
        return userNeo4j.findFollowerNumberByUsername(username).get(0).get("numFollowers").asInt();
    }
    public List<String> findInCommonFollowers(String usernameA, String usernameB){
        List<String> inCommonFollowers = new ArrayList<>();
        for(Record r: userNeo4j.findInCommonFollowers(usernameA, usernameB)){
            inCommonFollowers.add(r.get("name").asString());
        }
        return inCommonFollowers;
    }
    public boolean createNewUserNeo4j(String username){
        return userNeo4j.createNewUser(username);
    }

    public List<GameDTO> getSuggestedGames(String username){
        List<GameDTO> suggestedGames = new ArrayList<>();
        for(Record r: userNeo4j.getSuggestedGames(username)){
            GameDTO game = new GameDTO();
            game.setName(r.get("gameName").asString());
            game.setImage(r.get("imgUrl").asString());
            suggestedGames.add(game);
        }
        return suggestedGames;
    }

    public List<UserDTO> getSuggestedUser(String username){
        List<UserDTO> suggestedUser = new ArrayList<>();
        for(Record r: userNeo4j.getSuggestedUsers(username)){
            UserDTO user = new UserDTO();
            user.setUsername(r.get("gameName").asString());
            user.setImg(r.get("imgUrl").asString());
            suggestedUser.add(user);
        }
        return suggestedUser;
    }

    public List<UserDTO> getFamousUsers(){
        List<UserDTO> famousUser = new ArrayList<>();
        for(Record r: userNeo4j.getFamousUsers()){
            UserDTO user = new UserDTO();
            user.setUsername(r.get("user").asString());
            user.setImg(r.get("imgUrl").asString());
            famousUser.add(user);
        }
        return famousUser;
    }

    public boolean deleteUserNeo4j(String name){
        return userNeo4j.deleteUser(name);
    }
}
