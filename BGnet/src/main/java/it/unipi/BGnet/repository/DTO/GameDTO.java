package it.unipi.BGnet.repository.DTO;

import it.unipi.BGnet.repository.DAO.GameDAO;

public class GameDTO {
    String gameName;
    int followers;
    int rating;
    String designer;
    String categories;
    String minpmaxp;
    String description;

    public GameDTO(){
        gameName="gioco1";
        followers=3;
        rating=5;
        designer="marco";
        categories="culo";
        minpmaxp="10/12";
        description="test di prova";
    }
}
