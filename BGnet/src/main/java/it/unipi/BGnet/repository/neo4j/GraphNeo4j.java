package it.unipi.BGnet.repository.neo4j;

import org.neo4j.driver.*;
import org.neo4j.driver.Record;

import java.util.ArrayList;
import java.util.List;

import static org.neo4j.driver.Values.parameters;

public class GraphNeo4j implements AutoCloseable{
    private final Driver driver;
    private final String uri = "bolt://localhost:7687";
    private final String user = "neo4j";
    private final String pass = "123";

    private static GraphNeo4j graphNeo4j;


    private GraphNeo4j() {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, pass));
    }

    public static GraphNeo4j getIstance(){
        if(graphNeo4j == null){
            graphNeo4j = new GraphNeo4j();
        }
        return graphNeo4j;
    }
    @Override
    public void close() {
        driver.close();
    }
    public void write(final String query, final Value parameters) {
        try (Session session = driver.session()) {
            session.executeWrite(tx ->
            {
                tx.run(query, parameters).consume();
                return null;
            });
        }
    }
    public void write(String query) { write(query, parameters()); }
    public List<Record> read(final String query, final Value parameters) {
        List<Record> recordsList;
        try (Session session = driver.session()) {
            recordsList = session.executeRead(tx -> {
                Result result = tx.run( query, parameters );
                List<Record> records = new ArrayList<>();
                while (result.hasNext()) {
                    Record r = result.next();
                    records.add(r);
                }
                return records;
            });
        }
        return recordsList;
    }
    public List<Record> read(String query) {
        return read(query, parameters());
    }
}
