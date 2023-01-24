package it.unipi.BGnet.repository.mongoDB;

import it.unipi.BGnet.DTO.AnalyticDTO;
import it.unipi.BGnet.Utilities.Constants;
import it.unipi.BGnet.model.Post;
import it.unipi.BGnet.model.User;
import it.unipi.BGnet.model.Comment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.DateOperators.Month.month;
import static org.springframework.data.mongodb.core.aggregation.DateOperators.Year.year;

@Repository
public class PostRepository {
    Logger logger = LoggerFactory.getLogger(PostRepository.class);
    @Autowired
    private IPostRepository postMongo;

    @Autowired
    private MongoOperations mongoOperations;

    public IPostRepository getMongo(){
        return postMongo;
    }
    // CRUD Methods
    //  -------------------------------------------------------------------------------------------------
    public Post addPost(Post post){
        Post saved;
        try {
            saved = postMongo.save(post);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return saved;
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
    public Post likePost(Post post, String username){
        try{
            post.addLike(username);
            postMongo.save(post);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        return post;
    }
    public Post unlikePost(Post post, String username){
        try{
            post.removeLike(username);
            postMongo.save(post);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        return post;
    }
    public Post addComment(Post post, Comment comment){
        try{
            post.addComment(comment);
            postMongo.save(post);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        return post;
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
    public int countPages(String game) {
       return (int) Math.ceil((double)postMongo.countByGame(game)/ Constants.PAGE_SIZE);
    }
    // ---------------------------------------------------------------------------------------------------
    public List<AnalyticDTO> getPostAndCommentDistribution(int year){
        ProjectionOperation commentSize = project()
                .andExpression("$comments").size().as("howManyComments")
                .and(year(DateOperators.dateFromString("$timestamp"))).as("year")
                .and(month(DateOperators.dateFromString("$timestamp"))).as("month");

        MatchOperation matchYear = match(new Criteria("year").is(year));
        GroupOperation groupMonths = group("month")
                .count().as("posts")
                .sum("howManyComments").as("comments");
        ProjectionOperation projectFields = project()
                .andExpression("_id").as("field1")
                .andExclude("_id")
                .andExpression("posts").as("field2")
                .and(ArithmeticOperators.Divide.valueOf("comments").divideBy("posts")).as("field3");
        SortOperation sortByMonth = sort(Sort.by(Sort.Direction.ASC, "field1"));
        Aggregation aggregation = newAggregation(commentSize, matchYear, groupMonths, projectFields, sortByMonth);

        AggregationResults<AnalyticDTO> result = mongoOperations
                .aggregate(aggregation, "post", AnalyticDTO.class);
        logger.warn(result.getRawResults().toJson());
        return result.getMappedResults();
    }
}
