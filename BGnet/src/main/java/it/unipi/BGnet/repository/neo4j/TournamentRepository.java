package it.unipi.BGnet.repository.neo4j;

import it.unipi.BGnet.model.Tournament;
import org.neo4j.driver.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.neo4j.driver.Values.parameters;

public class TournamentRepository {
    private final static Logger logger = LoggerFactory.getLogger(TournamentRepository.class);

    private final GraphNeo4j graphNeo4j;

    public TournamentRepository(GraphNeo4j graphNeo4j) {
        this.graphNeo4j = graphNeo4j;
    }

    public GraphNeo4j getGraphNeo4j() {
        return graphNeo4j;
    }

    public boolean addTournamentPartecipant(String userName, int tournamentId){
        boolean result = true;
        try{
            graphNeo4j.write("MATCH (u:User) WHERE u.name=$userName" +
                    " MATCH (t:Tournament) WHERE t.id=$tournamentId" +
                    " MERGE (u)-[:PARTICIPATE]->(t)",
                    parameters("userName", userName, "tournamentId", tournamentId));
        } catch (Exception e){
            e.printStackTrace();
            result=false;
        }
        return result;
    }

    public boolean addTournamentCreator(String userName, int tournamentId){
        boolean result = true;
        try{
            graphNeo4j.write("MATCH (u:User) WHERE u.name=$userName" +
                            " MATCH (t:Tournament) WHERE t.id=$tournamentId" +
                            " MERGE (u)-[:CREATED]->(t)",
                    parameters("userName", userName, "tournamentId", tournamentId));
        } catch (Exception e){
            e.printStackTrace();
            result=false;
        }
        return result;
    }

    public boolean createTournament(Tournament t){
        boolean result = true;
        try{
            graphNeo4j.write("CREATE (t: Tournament {id:$id, maxPlayers: $maxPlayers, date: $date, modalities: $modalities, playersPerMatch: $playersPerMatch, duration: $duration})",
                    parameters("id", t.getId(), "maxPlayers", t.getMaxPlayers(), "date", t.getDate(), "modalities", t.getModalities(), "playersPerMatch", t.getPlayersPerMatch(), "duration", t.getDuration()));
            graphNeo4j.write("MATCH (t:Tournament) WHERE t.id=$id" +
                            " MATCH (g:Game) WHERE g.name=$gameName" +
                            " CREATE (t)-[:TOURNAMENT_GAME]->(g)",
                    parameters("id", t.getId(), "gameName", t.getTournamentGame()));
        } catch (Exception e){
            e.printStackTrace();
            result=false;
        }
        return result;
    }

    public List<Record> getTournamentsByGame(String gameName){
        try{
            return graphNeo4j.read("MATCH (t:Tournament)-[:TOURNAMENT_GAME]->(g:Game)" +
                            " WHERE g.name=$gameName" +
                            " RETURN t.id as id, t.date as date, t.duration as duration," +
                            " t.maxPlayers as maxPlayers, t.modalities as modalities, t.playersPerMatch as playersPerMatch",
                    parameters("gameName", gameName));

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Record> getPartecipantsByTournamentId(int tournamentId){
        try{
            return graphNeo4j.read("MATCH (u:User)-[:PARTICIPATE]->(t:Tournament)" +
                            " WHERE t.id=tournamentId" +
                            " RETURN u.name as username",
                    parameters("tournamentId", tournamentId));

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Record> getInCommonTournaments(String userA, String userB){
        try{
            return graphNeo4j.read("MATCH (uA:User)-[:PARTICIPATE]->(t:Tournament)" +
                            "<-[:PARTICIPATE]-(uB:User)" +
                            " WHERE uA.name=$userA AND uB.name=$userB" +
                            " RETURN t.id as id, t.date as date, t.duration as duration," +
                            " t.maxPlayers as maxPlayers, t.modalities as modalities, t.playersPerMatch as playersPerMatch",
                    parameters("userA", userA, "userB", userB));

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    public List<Record> getGameByTournamentId(int tournamentId){
        try{
            return graphNeo4j.read("MATCH (u:User)-[:PARTICIPATE]->(t:Tournament)" +
                            " WHERE t.id=tournamentId" +
                            " RETURN u.name as username",
                    parameters("tournamentId", tournamentId));

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Record> getCreatorByTournamentId(int tournamentId){
        try{
            return graphNeo4j.read("MATCH (u:User)-[:PARTICIPATE]->(t:Tournament)" +
                            " WHERE t.id=tournamentId" +
                            " RETURN u.name as username",
                    parameters("tournamentId", tournamentId));

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Record> getTournamentsByUser(String username){

    }

    public boolean closeTournament(Tournament t){

    }

}
