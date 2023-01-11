package it.unipi.BGnet.repository;

import it.unipi.BGnet.model.Game;
import it.unipi.BGnet.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class GameRepository{
    @Autowired
    private IGameRepository gameMongo;

    public IGameRepository getMongo(){ return gameMongo; }

    // CRUD Methods
    // ----------------------------------------------------------------------------------------
    public boolean addGame(Game game){
        boolean result = true;
        try{
            gameMongo.save(game);
        } catch (Exception e){
            e.printStackTrace();
            result=false;
        }
        return result;
    }
    public boolean deleteGameById(String id) {
        boolean result = true;
        try {
            gameMongo.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public boolean deleteGame(Game game) {
        boolean result = true;
        try {
            gameMongo.delete(game);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public Optional<Game> getGameById(String id){
        Optional<Game> game = Optional.empty();
        try {
            game = gameMongo.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return game;
    }

    public Optional<Game> getGameByName(String name){
        Optional<Game> game = Optional.empty();
        try {
            game = gameMongo.findByName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return game;
    }

    public List<Game> findAllGames(){
        List<Game> result = new ArrayList<>();
        try {
            result.addAll(gameMongo.findAll());
        } catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean addPost(Post post){
        return false;
    }

    public boolean existsById(String gameName) {
        return gameMongo.existsByName(gameName);
    }
}
