package it.unipi.BGnet.repository.neo4j;

import it.unipi.BGnet.model.Tournament;
import org.neo4j.driver.Record;

import java.util.List;

import static org.neo4j.driver.Values.parameters;

public class TournamentNeo4j {
    private final GraphNeo4j graphNeo4j;

    public TournamentNeo4j() {
        this.graphNeo4j = GraphNeo4j.getIstance();
    }

    public GraphNeo4j getGraphNeo4j() {
        return graphNeo4j;
    }

    public boolean addTournamentPartecipant(String userName, int tournamentId){
        boolean result = true;
        try{
            graphNeo4j.write("MATCH (u:User) WHERE u.name=$userName" +
                    " MATCH (t:Tournament) WHERE id(t)=$tournamentId" +
                    " MERGE (u)-[:PARTICIPATE]->(t)",
                    parameters("userName", userName, "tournamentId", tournamentId));
        } catch (Exception e){
            e.printStackTrace();
            result=false;
        }
        return result;
    }

    public boolean removeTournamentPartecipant(String username, int tournamentId){
        boolean result = true;
        try{
            graphNeo4j.write("MATCH (u:User) WHERE u.name=$userName" +
                            " MATCH (t:Tournament) WHERE id(t)=$tournamentId" +
                            " MATCH (u)-[r:PARTICIPATE]->(t)" +
                            " DELETE r",
                    parameters("userName", username, "tournamentId", tournamentId));
        } catch (Exception e){
            e.printStackTrace();
            result=false;
        }
        return result;
    }

    public Boolean createTournament(int maxPlayers, String date, String modalities, String playersPerMatch, String duration, String gamename, String creator){
        Boolean result = null;
        try{
            graphNeo4j.write("MATCH (g:Game) WHERE g.name=$gamename" +
                            " MATCH (u:User) WHERE u.name=$username" +
                            " CREATE (t: Tournament {maxPlayers: $maxPlayers, date: $date, modalities: $modalities, playersPerMatch: $playersPerMatch, duration: $duration})" +
                            " CREATE (t)-[:TOURNAMENT_GAME]->(g)" +
                            " CREATE (u)-[:CREATED]->(t)",
                    parameters("maxPlayers", maxPlayers, "date", date, "modalities", modalities, "playersPerMatch", playersPerMatch, "duration", duration, "gamename", gamename, "username", creator));
        } catch (Exception e){
            e.printStackTrace();
            result=false;
        }
        return result;
    }

    public List<Record> getTournamentsByGamename(String gameName){
        try{
            return graphNeo4j.read("MATCH (t:Tournament)-[:TOURNAMENT_GAME]->(g:Game)" +
                            " WHERE g.name=$gameName" +
                            " RETURN id(t) as id, t.date as date, t.duration as duration," +
                            " t.maxPlayers as maxPlayers, t.modalities as modalities, t.playersPerMatch as playersPerMatch," +
                            " t.isClosed as isClosed",
                    parameters("gameName", gameName));

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Record> getPartecipantsByTournamentId(int tournamentId){
        try{
            return graphNeo4j.read("MATCH (u:User)-[:PARTICIPATE]->(t:Tournament)" +
                            " WHERE id(t)=$tournamentId" +
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
                            " RETURN id(t) as id, t.date as date, t.duration as duration," +
                            " t.maxPlayers as maxPlayers, t.modalities as modalities, t.playersPerMatch as playersPerMatch" +
                            " t.isClosed as isClosed",
                    parameters("userA", userA, "userB", userB));

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Record> getGameByTournamentId(int tournamentId){
        try{
            return graphNeo4j.read("MATCH (t:Tournament)-[:TOURNAMENT_GAME]->(g:Game)" +
                            " WHERE id(t)=$tournamentId" +
                            " RETURN g.name as gamename",
                    parameters("tournamentId", tournamentId));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Record> getCreatorByTournamentId(int tournamentId){
        try{
            return graphNeo4j.read("MATCH (u:User)-[:CREATED]->(t:Tournament)" +
                            " WHERE id(t)=$tournamentId" +
                            " RETURN u.name as username",
                    parameters("tournamentId", tournamentId));

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Record> getTournamentsByUser(String username){
        try{
            return graphNeo4j.read("MATCH (u:User)-[:PARTICIPATE]->(t:Tournament)" +
                            " WHERE u.name=$username" +
                            " RETURN id(t) as id, t.date as date, t.duration as duration," +
                            " t.maxPlayers as maxPlayers, t.modalities as modalities, t.playersPerMatch as playersPerMatch," +
                            " t.isClosed as isClosed",
                    parameters("username", username));

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean closeTournament(int tournamentId){
        boolean result = true;
        boolean isClosed=true;
        try {
            graphNeo4j.write("MATCH (t:Tournament)" +
                            " WHERE id(t)=$id" +
                            " SET t.isClosed = $isClosed",
                    parameters("id", tournamentId, "isClosed", isClosed));
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public List<Record> isParticipating(String username, int tournamentId){
        try{
            return graphNeo4j.read("MATCH (u:user {name: $username})," +
                            " (t:Tournament{id(t):$tournamentId})" +
                            " RETURN EXISTS((u)-[:PARTICIPATE]-(t)) AS isParticipating",
                    parameters("username", username, "tournamentId", tournamentId));

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Record> isCreator(String username, int tournamentId){
        try{
            return graphNeo4j.read("MATCH (u:user {name: $username})," +
                            " (t:Tournament{id(t):$tournamentId})" +
                            " RETURN EXISTS((u)-[:CREATED]-(t)) AS isParticipating",
                    parameters("username", username, "tournamentId", tournamentId));

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
