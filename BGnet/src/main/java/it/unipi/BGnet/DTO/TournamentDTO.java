package it.unipi.BGnet.DTO;

import it.unipi.BGnet.model.Tournament;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
public class TournamentDTO {
    // DA RIEMPIRE GUARDANDO tournamentPage.html //
    private int id;
    private String date;
    private String duration;
    private int maxPlayers;
    private String modalities;
    private String playersPerMatch;
    private int numPartecipants;
    private String tournamentGame;
    private String creator;
    private boolean isClosed;
    private boolean isParticipating;

    private boolean isCreator;


    public TournamentDTO(int id, String date, String duration, int maxPlayers, String modalities, String playersPerMatch, int numPartecipants, String tournamentGame, String creator, boolean isClosed, boolean isParticipating, boolean isCreator) {
        this.id = id;
        this.date = date;
        this.duration = duration;
        this.maxPlayers = maxPlayers;
        this.modalities = modalities;
        this.playersPerMatch = playersPerMatch;
        this.numPartecipants = numPartecipants;
        this.tournamentGame = tournamentGame;
        this.creator = creator;
        this.isClosed = isClosed;
        this.isParticipating = isParticipating;
        this.isCreator = isCreator;
    }

    public boolean isCreator() {
        return isCreator;
    }

    public void setIsCreator(boolean creator) {
        isCreator = creator;
    }

    public boolean isParticipating() {
        return isParticipating;
    }

    public void setParticipating(boolean participating) {
        isParticipating = participating;
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

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
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

    public int getNumPartecipants() {
        return numPartecipants;
    }

    public void setNumPartecipants(int numPartecipants) {
        this.numPartecipants = numPartecipants;
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

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }
}
