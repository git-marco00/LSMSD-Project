package it.unipi.BGnet.Utilities;

import it.unipi.BGnet.model.Game;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public class SessionVariables {
    public String myself;
    public String gameToDisplay;
    public String userToDisplay;

    public List<Game> current_results;
    public int currentPage;
    public boolean lastPage;

    @Override
    public String toString() {
        return "SessionVariables{" +
                "myself='" + myself + '\'' +
                ", gameToDisplay='" + gameToDisplay + '\'' +
                ", userToDisplay='" + userToDisplay + '\'' +
                '}';
    }
}
