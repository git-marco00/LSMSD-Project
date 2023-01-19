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

}
