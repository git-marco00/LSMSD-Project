package it.unipi.BGnet.Utilities;

import org.springframework.web.bind.annotation.ModelAttribute;

public class SessionVariables {
    public String myself;
    public String gameToDisplay;
    public String userToDisplay;

    public int currentPage;

    @Override
    public String toString() {
        return "SessionVariables{" +
                "myself='" + myself + '\'' +
                ", gameToDisplay='" + gameToDisplay + '\'' +
                ", userToDisplay='" + userToDisplay + '\'' +
                '}';
    }
}
