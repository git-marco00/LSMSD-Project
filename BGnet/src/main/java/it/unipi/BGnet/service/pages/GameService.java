package it.unipi.BGnet.service.pages;

import it.unipi.BGnet.DTO.GameDTO;
import it.unipi.BGnet.DTO.GamePage;
import it.unipi.BGnet.controllers.api.GameController;
import it.unipi.BGnet.model.Game;
import it.unipi.BGnet.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("mainGameService")
public class GameService {
    @Autowired
    private GameRepository gameRep;

    public GamePage getGamePage(String myself, String gameName){
        GamePage gamePage = new GamePage();
        Optional<Game> game = gameRep.getGameByName(gameName);
        Logger logger = LoggerFactory.getLogger(GameService.class);
        /////// NEO4J ///////
        List<String> inCommonFollowers = null;
        boolean isFollowing = false;

        if(myself != null) {
            inCommonFollowers = gameRep.findInCommonFollowers(myself, gameName);
            isFollowing = gameRep.isFollowing(myself, gameName);
        }
        int followers = gameRep.getFollowersNumberByGamename(gameName);

        if(game.isEmpty()){
            return null;
        }
        else {
            ///////// MONGODB ////////
            gamePage.setGameName(game.get().getName());
            gamePage.setDesigner(game.get().getDesigner());
            logger.warn(String.valueOf(game.get().getYearPublished()));
            gamePage.setYearPublished(game.get().getYearPublished());
            gamePage.setMinPlayers(game.get().getMinPlayers());
            gamePage.setMaxPlayers(game.get().getMaxPlayers());
            gamePage.setCategories(game.get().getCategories());
            gamePage.setDescription(game.get().getDescription());
            gamePage.setImageUrl(game.get().getImg());
            gamePage.setAvgRate(game.get().getAvgRate());
            gamePage.setRated(game.get().haveIVoted(myself));
            gamePage.setMostRecentPosts(game.get().getMostRecentPosts());

            ///////// NEO4J //////////
            gamePage.setFollowed(isFollowing);
            gamePage.setInCommonFollowers(inCommonFollowers);
            gamePage.setFollowers(followers);
            return gamePage;
        }
    }
    public boolean getExistence(String gameName){
        return gameRep.searchGame(gameName);
    }
    public int countExistence(String gameName){
        return gameRep.countGames(gameName);
    }
    public List<Game> checkExistence(String pattern){
        List<Game> games = gameRep.searchGames(pattern);
        return games;
    }
    public List<GameDTO> getResultPage(List<Game> toShow){
        List<GameDTO> result = new ArrayList<>();
        for(Game g: toShow){
            GameDTO target = new GameDTO();
            target.setName(g.getName());
            target.setDesigner(g.getDesigner());
            target.setMinPlayers(g.getMinPlayers());
            target.setMaxPlayers(g.getMaxPlayers());
            target.setCategories(g.getCategories());
            target.setImage(g.getImageUrl());
            result.add(target);
        }
        return result;
    }

    public boolean follow(String gameName, String username){
        return gameRep.followGameByGamename(username, gameName);
    }
}
