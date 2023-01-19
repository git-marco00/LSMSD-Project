package it.unipi.BGnet.repository;

import it.unipi.BGnet.model.Tournament;
import it.unipi.BGnet.repository.neo4j.TournamentNeo4j;
import org.springframework.stereotype.Repository;
import org.neo4j.driver.Record;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TournamentRepository {
    private TournamentNeo4j tournamentNeo4j = new TournamentNeo4j();

    public boolean createTournament(int maxPlayers, String date, String modalities, String playersPerMatch, String duration, String gamename, String creator){
        return tournamentNeo4j.createTournament(maxPlayers, date, modalities, playersPerMatch, duration, gamename, creator);
    }

    public boolean closeTournament(String tournamentId){
        return tournamentNeo4j.closeTournament(tournamentId);
    }

    public boolean addTournamentPartecipant(String username, String tournamentid){
        return tournamentNeo4j.addTournamentPartecipant(username, tournamentid);
    }

    public List<Tournament> getTournamentsByGamename(String gamename){
        List<Tournament> tournamentList= new ArrayList<>();
        for(Record r: tournamentNeo4j.getTournamentsByGamename(gamename)){
            String tid = r.get("id").asString();
            String date = r.get("date").asString();
            String duration = r.get("duration").asString();
            int maxPlayers = r.get("maxPlayers").asInt();
            String modalities = r.get("modalities").asString();
            String playersPerMatch = r.get("playersPerMatch").asString();
            Boolean isClosed = r.get("isClosed").asBoolean();
            Tournament t = new Tournament(tid, date, duration, maxPlayers, modalities, playersPerMatch, null, gamename, null, isClosed);
            tournamentList.add(t);
        }
        return tournamentList;
    }

    public List<String> getPartecipantsByTournamentId(String tournamentId){
        List<String> partecipantsList = new ArrayList<>();
        for(Record r: tournamentNeo4j.getPartecipantsByTournamentId(tournamentId)){
            partecipantsList.add(r.get("username").asString());
        }
        return partecipantsList;
    }

    public List<Tournament> getInCommonTournaments(String userA, String userB){
        List<Tournament> tournamentList= new ArrayList<>();
        for(Record r: tournamentNeo4j.getInCommonTournaments(userA, userB)){
            String tid = r.get("id").asString();
            String date = r.get("date").asString();
            String duration = r.get("duration").asString();
            int maxPlayers = r.get("maxPlayers").asInt();
            String modalities = r.get("modalities").asString();
            String playersPerMatch = r.get("playersPerMatch").asString();
            Boolean isClosed = r.get("isClosed").asBoolean();
            Tournament t = new Tournament(tid, date, duration, maxPlayers, modalities, playersPerMatch, null, null, null, isClosed);
            tournamentList.add(t);
        }
        return tournamentList;
    }

    public String getGameByTournamentId(String tournamentId){
        if(tournamentNeo4j.getGameByTournamentId(tournamentId)!=null){
            return tournamentNeo4j.getGameByTournamentId(tournamentId).get(0).get("gamename").asString();
        }
        return null;
    }

    public String getCreatorByTournamentId(String tournamentId){
        if(tournamentNeo4j.getCreatorByTournamentId(tournamentId)!=null){
            return tournamentNeo4j.getCreatorByTournamentId(tournamentId).get(0).get("gamename").asString();
        }
        return null;
    }
    public List<Tournament> getTournamentsByUser(String username){
        List<Tournament> tournamentList= new ArrayList<>();
        for(Record r: tournamentNeo4j.getTournamentsByUser(username)){
            String tid = r.get("id").asString();
            String date = r.get("date").asString();
            String duration = r.get("duration").asString();
            int maxPlayers = r.get("maxPlayers").asInt();
            String modalities = r.get("modalities").asString();
            String playersPerMatch = r.get("playersPerMatch").asString();
            Boolean isClosed = r.get("isClosed").asBoolean();
            Tournament t = new Tournament(tid, date, duration, maxPlayers, modalities, playersPerMatch, null, null, null, isClosed);
            tournamentList.add(t);
        }
        return tournamentList;
    }

    public boolean isParticipating(String username, String tournamentId){
        return tournamentNeo4j.isParticipating(username, tournamentId).get(0).get("isParticipating").asBoolean();
    }

    public boolean isCreator(String username, String tournamentId){
        return tournamentNeo4j.isCreator(username, tournamentId).get(0).get("isParticipating").asBoolean();
    }
}
