package hhn.aib.thesis.dbcreator;

import com.opencsv.exceptions.CsvValidationException;
import org.neo4j.driver.*;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class GraphDBCreator {

    private static final String dbUri = "neo4j://localhost";
    private static final String dbUser = "neo4j";
    private static final String dbPassword = "12345678";
    private static final String csvFilePath ="src/main/resources/mockdata.csv";
    private final Driver driver;

    public GraphDBCreator(String dbUri, String dbUser, String dbPassword) {
        this.driver = GraphDatabase.driver(dbUri, AuthTokens.basic(dbUser, dbPassword));
    }

    public void close(){
        driver.close();
    }

    public void importData(String csvFilePath) {
        try (Session session = driver.session()) {
            try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
                String[] header = reader.readNext(); // Spaltennamen überspringen
                String[] row;

                while ((row = reader.readNext()) != null) {
                    // Personen-Daten
                    String personId = row[0];
                    String firstName = row[1];
                    String lastName = row[2];
                    String email = row[3];

                    // Projekt-Daten
                    String projectId = row[4];
                    String projectTitle = row[5];
                    String projectCreatedAt = row[6];

                    // Issue-Daten
                    String issueId = row[7];
                    String issueTitle = row[8];
                    String issueCreatedAt = row[9];
                    String issueState = row[10];
                    String issueStateReason = row[11];

                    // Cypher-Abfragen
                    session.writeTransaction(tx -> {
                        // Person-Knoten erstellen
                        tx.run("MERGE (p:Person {ID: $id, firstName: $firstName, lastName: $lastName, email: $email})",
                                Values.parameters("id", personId, "firstName", firstName, "lastName", lastName, "email", email));

                        // Projekt-Knoten erstellen und mit Person verknüpfen
                        tx.run("MERGE (pr:Project {ID: $projectId, title: $title, createdAt: $createdAt}) " +
                                        "WITH pr " +
                                        "MATCH (owner:Person {ID: $ownerId}) " +
                                        "MERGE (owner)-[:OWNS]->(pr)",
                                Values.parameters("projectId", projectId, "title", projectTitle, "createdAt", projectCreatedAt, "ownerId", personId));

                        // Issue-Knoten erstellen und mit Projekt und Person verknüpfen
                        tx.run("MERGE (i:Issue {ID: $issueId, title: $title, createdAt: $createdAt, state: $state, stateReason: $stateReason}) " +
                                        "WITH i " +
                                        "MATCH (creator:Person {ID: $creatorId}), (pr:Project {ID: $projectId}) " +
                                        "MERGE (creator)-[:CREATED]->(i) " +
                                        "MERGE (i)-[:BELONGS_TO]->(pr)",
                                Values.parameters("issueId", issueId, "title", issueTitle, "createdAt", issueCreatedAt,
                                        "state", issueState, "stateReason", issueStateReason,
                                        "creatorId", personId, "projectId", projectId));
                        return null;
                    });
                }
            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String... args) {

        GraphDBCreator importer = new GraphDBCreator(dbUri,dbUser,dbPassword);

        try {
            System.out.println("Importiere Daten...");
            importer.importData(csvFilePath);
            System.out.println("Datenimport abgeschlossen.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            importer.close();
        }

    }
}