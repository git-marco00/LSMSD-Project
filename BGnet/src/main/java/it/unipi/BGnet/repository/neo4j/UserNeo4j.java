package it.unipi.BGnet.repository.neo4j;

import java.util.List;

import org.neo4j.driver.Record;

import static org.neo4j.driver.Values.parameters;

public class UserNeo4j {
    private final GraphNeo4j graphNeo4j;

    public UserNeo4j(){
        this.graphNeo4j = GraphNeo4j.getIstance();
    }

    public GraphNeo4j getGraphNeo4j() {
        return graphNeo4j;
    }

    public boolean followUserByUsername(String usernameA, String usernameB){
        boolean result = true;
        try{
            graphNeo4j.write("MATCH (uA:User) WHERE uA.name=$usernameA" +
                            " MATCH (uB:User) WHERE uB.name=$usernameB" +
                            " MERGE (uA)-[:FOLLOWS]->(uB)",
                    parameters("usernameA", usernameA, "usernameB", usernameB));
        } catch (Exception e){
            e.printStackTrace();
            result=false;
        }
        return result;
    }

    public boolean unfollowUserByUsername(String usernameA, String usernameB){
        boolean result = true;
        try{
            graphNeo4j.write("MATCH (uA:User) WHERE uA.name=$usernameA" +
                            " MATCH (uB:User) WHERE uB.name=$usernameB" +
                            " DELETE (uA)-[:FOLLOWS]->(uB)",
                    parameters("usernameA", usernameA, "usernameB", usernameB));
        } catch (Exception e){
            e.printStackTrace();
            result=false;
        }
        return result;
    }

    public List<Record> findFollowerNumberByUsername(String username){
        try{
            return graphNeo4j.read("MATCH ()-[:FOLLOWS]->(u:User)" +
                            " WHERE u.name=$username" +
                            " RETURN count(*) as numFollowers",
                    parameters("username", username));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Record> findInCommonFollowers(String usernameA, String usernameB){
        // forse da rivedere
        try{
            return graphNeo4j.read("MATCH (uA:User)-[:FOLLOWS]->(uTarget:User)" +
                            "-[:FOLLOWS]->(uB:User)" +
                            " WHERE uA.name=$userA AND uB.name=$userB" +
                            " RETURN uTarget.name as name",
                    parameters("userA", usernameA, "userB", usernameB));

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean createNewUser(String username){
        boolean result = true;
        try{
            graphNeo4j.write("CREATE (u: User {name: $username})",
                    parameters("username", username));
        } catch (Exception e){
            e.printStackTrace();
            result=false;
        }
        return result;
    }

    public List<Record> getSuggestedUsers(String username){
        try{
            return graphNeo4j.read("MATCH(u:User{name:$username})-[:PARTICIPATE]->(t:Tournament)<-[:PARTICIPATE]-(inCommonPartecipants:User) " +
                            "WHERE NOT (inCommonPartecipants)<-[:FOLLOWS]-(u) " +
                            "MATCH (inCommonGamers)-[:FOLLOWS]->(g:Game)<-[:FOLLOWS]-(u) " +
                            "WHERE inCommonGamers.name IN inCommonPartecipants.name " +
                            "RETURN DISTINCT(inCommonGamers) AS suggestedUser, COUNT(*) AS numGames " +
                            "ORDER BY numGames DESC " +
                            "LIMIT 4",
                    parameters("username", username));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Record> getFamousUsers(){
        try{
            return graphNeo4j.read("MATCH ()-[r:FOLLOWS]->(u:User) " +
                            "RETURN DISTINCT(u.name) AS user, u.imgUrl AS imgUrl, COUNT(DISTINCT((r))) AS cardinality  " +
                            "ORDER BY cardinality DESC " +
                            "LIMIT 4");
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public List<Record> getSuggestedGames(String username){
        try{
            return graphNeo4j.read("MATCH (u:User{name:$username})-[:FOLLOWS]->(g:Game) " +
                            "UNWIND g.category AS categories " +
                            "WITH collect(categories) AS bestCategoriesList, COUNT(*) AS numGames " +
                            "ORDER BY numGames DESC " +
                            "LIMIT 4 " +
                            "MATCH (u:User{name:$username})-[:FOLLOWS]->(following:User) " +
                            "MATCH (following)-[:FOLLOWS]->(g:Game) " +
                            "WHERE NOT (u)-[:FOLLOWS]->(g) AND g.category[0] IN bestCategoriesList " +
                            "RETURN g.name AS gameName, g.imgUrl AS imgUrl, COUNT(g) AS numFollowers " +
                            "ORDER BY numFollowers DESC " +
                            "LIMIT 4 ",
                    parameters("username", username));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteUser(String username){
        try{
            graphNeo4j.write("MATCH (u:User{name:$username})" +
                            "DETACH DELETE u",
                    parameters("username", username));
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
