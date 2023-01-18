package it.unipi.BGnet.model;

import java.util.ArrayList;
import java.util.List;

public class Tournament {
    private int id;
    private String date;
    private String duration;
    private String maxPlayers;
    private String modalities;
    private String playersPerMatch;
    private List<String> partecipants;
    private String tournamentGame;
    private String creator;

    private boolean isClosed;

    public Tournament(int id, String date, String duration, String maxPlayers, String modalities, String playersPerMatch, List<String> partecipants, String tournamentGame, String creator, boolean isClosed) {
        this.id = id;
        this.date = date;
        this.duration = duration;
        this.maxPlayers = maxPlayers;
        this.modalities = modalities;
        this.playersPerMatch = playersPerMatch;
        this.partecipants = partecipants;
        this.tournamentGame = tournamentGame;
        this.creator = creator;
        this.isClosed = isClosed;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(String maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public String getModalities() {
        return modalities;
    }

    public void setModalities(String modalities) {
        this.modalities = modalities;
    }

    public String getPlayersPerMatch() {
        return playersPerMatch;
    }

    public void setPlayersPerMatch(String playersPerMatch) {
        this.playersPerMatch = playersPerMatch;
    }

    public List<String> getPartecipants() {
        return partecipants;
    }

    public void setPartecipants(List<String> partecipants) {
        this.partecipants = partecipants;
    }

    public String getTournamentGame() {
        return tournamentGame;
    }

    public void setTournamentGame(String tournamentGame) {
        this.tournamentGame = tournamentGame;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
