package it.unipi.BGnet.repository;

import it.unipi.BGnet.DTO.AnalyticDTO;
import it.unipi.BGnet.DTO.GameDTO;
import it.unipi.BGnet.DTO.InCommonGenericDTO;
import it.unipi.BGnet.DTO.UserDTO;
import it.unipi.BGnet.Utilities.Constants;
import it.unipi.BGnet.model.Game;
import it.unipi.BGnet.model.Post;
import it.unipi.BGnet.model.User;
import it.unipi.BGnet.repository.mongoDB.IUserRepository;
import it.unipi.BGnet.repository.neo4j.UserNeo4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.neo4j.driver.Record;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class UserRepository {
    Logger logger = LoggerFactory.getLogger(UserRepository.class);
    @Autowired
    private IUserRepository userMongo;

    @Autowired
    private MongoOperations mongoOperations;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    public Optional<User> getUserByUsername(String username){
        Optional<User> user = Optional.empty();
        try {
            user = userMongo.findByUsername(username);
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
        if(list.size() > Constants.RECENT_SIZE)
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

    public boolean updatePost(String name, Post olderPost, Post newPost) {
        Optional<User> user = getUserByUsername(name);
        if(user.isEmpty())
            return false;
        List<Post> list = user.get().getMostRecentPosts();
        list.forEach((post) -> {
            if (post.getId().equals(olderPost.getId()))
                list.set(list.indexOf(post), newPost);
        });
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
    public boolean isFollowed(String myself, String username){
        return userNeo4j.isFollowed(myself, username).get(0).get("isFollowed").asBoolean();
    }
    public List<InCommonGenericDTO> findInCommonFollowers(String usernameA, String usernameB){
        List<InCommonGenericDTO> inCommonFollowers = new ArrayList<>();
        for(Record r: userNeo4j.findInCommonFollowers(usernameA, usernameB)){
            String name = r.get("name").asString();
            String imgUrl = r.get("imgUrl").asString();
            inCommonFollowers.add(new InCommonGenericDTO(name, imgUrl));
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

    public List<UserDTO> getSuggestedUserByTournaments(String username){
        List<UserDTO> suggestedUser = new ArrayList<>();
        for(Record r: userNeo4j.getSuggestedUsersByTournaments(username)){
            UserDTO user = new UserDTO();
            user.setUsername(r.get("suggestedUser").asString());
            user.setImg(r.get("imgUrl").asString());
            suggestedUser.add(user);
        }
        return suggestedUser;
    }

    public List<UserDTO> getSuggestedUserByFollower(String username){
        List<UserDTO> suggestedUser = new ArrayList<>();
        for(Record r: userNeo4j.getSuggestedUsersByFollowers(username)){
            UserDTO user = new UserDTO();
            user.setUsername(r.get("username").asString());
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

    public boolean checkAdmin(String name){
        Optional<User> result;
        try{
            result = getUserByUsername(name);
            if(result.isEmpty())
                return false;
            if(!result.get().isAdmin())
                return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<AnalyticDTO> analytic4() {
        List<Record> list = userNeo4j.analytic4();
        List<AnalyticDTO> listDTO = new ArrayList<>();
        int i=1;
        for(Record r:list){
            AnalyticDTO dto = new AnalyticDTO();
            dto.setField1(String.valueOf(i));
            dto.setField2(r.get("username").asString());
            dto.setField3(String.valueOf(r.get("popularity").asInt()));
            i++;
            listDTO.add(dto);
        }
        return listDTO;
    }

    public List<AnalyticDTO> getBestCountriesByYearRegistered(int year){
        MatchOperation getYear = match(new Criteria("yearregistered").is(year));
        GroupOperation getNumberOfJoining = group("continent", "stateorprovince")
                .count().as("count");

        SortOperation sortByCount = sort(Sort.by(Sort.Direction.DESC, "count"));

        GroupOperation getCountries = group("_id.continent")
                .first("_id.stateorprovince").as("country")
                .first("count").as("people");

        ProjectionOperation renameThings = project()
                .andExpression("_id").as("field1")
                .andExpression("country").as("field2")
                .andExpression("people").as("field3");

        Aggregation aggregation = newAggregation(getYear, getNumberOfJoining, sortByCount, getCountries, renameThings);
        AggregationResults<AnalyticDTO> result = mongoOperations
                .aggregate(aggregation, "user", AnalyticDTO.class);

        logger.warn(result.getMappedResults().get(0).toString());
        return result.getMappedResults();
    }
}
