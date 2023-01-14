package it.unipi.BGnet.service.pages;

import it.unipi.BGnet.DTO.GamePage;
import it.unipi.BGnet.model.Game;
import it.unipi.BGnet.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return gameRep.existsById(gameName);
    }
}
