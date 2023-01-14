package it.unipi.BGnet.service.pages;

import it.unipi.BGnet.DTO.GameDTO;
import it.unipi.BGnet.DTO.GamePage;
import it.unipi.BGnet.model.Game;
import it.unipi.BGnet.repository.GameRepository;
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
        if(game.isEmpty()){
            return null;
        }
        else {
            gamePage.setGameName(game.get().getName());
            gamePage.setDesigner(game.get().getDesigner());
            gamePage.setYearPublished(game.get().getYearPublished());
            gamePage.setMinPlayers(game.get().getMinPlayers());
            gamePage.setMaxPlayers(game.get().getMaxPlayers());
            gamePage.setCategories(game.get().getCategories());
            gamePage.setDescription(game.get().getDescription());
            gamePage.setImageUrl(game.get().getImg());
            gamePage.setRatings(game.get().getRatings());
            gamePage.setRated(game.get().haveIVoted(myself));
            gamePage.setMostRecentPosts(game.get().getMostRecentPosts());
            //followers, se io lo seguo, in common followers
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
        List<String> gamesTitles = new ArrayList<>();
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
            result.add(target);
        }
        return result;
    }

}
