package hhn.aib.thesis.dbcreator;

import com.opencsv.exceptions.CsvValidationException;
import org.neo4j.driver.*;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String[] header = reader.readNext(); // Spaltennamen überspringen
                String[] row;

                while ((row = reader.readNext()) != null) {
                    // Personen-Daten
                    String pid = row[0];
                    String firstname = row[1];
                    String lastname = row[2];
                    String email = row[3];

                    // Projekt-Daten
                    String prid = row[4];
                    String projectTitle = row[5];
                    LocalDateTime projectCreatedAt = (LocalDateTime.parse(row[6], inputFormatter));

                    // Issue-Daten
                    String iid = row[7];
                    String issueTitle = row[8];
                    LocalDateTime issueCreatedAt = (LocalDateTime.parse(row[9], inputFormatter));
                    String issueState = row[10];
                    String issueStateReason = row[11];

                    // Cypher-Abfragen
                    session.writeTransaction(tx -> {
                        // Person-Knoten erstellen
                        tx.run("MERGE (p:Person {pid: $pid})" +
                                        "SET p.firstname = $firstname, p.lastname = $lastname, p.email = $email",
                                Values.parameters("pid", pid, "firstname", firstname, "lastname", lastname, "email", email));

                        // Projekt-Knoten erstellen und mit Person verknüpfen
                        tx.run("MERGE (pr:Project {prid: $prid})" +
                                        "SET pr.title = $title, pr.createdAt = $createdAt",
                                Values.parameters("prid", prid, "title", projectTitle, "createdAt", projectCreatedAt, "pid", pid));

                        // Issue-Knoten erstellen und mit Projekt und Person verknüpfen
                        tx.run("MERGE (i:Issue {iid: $iid})" +
                                        "SET i.title = $title, i.createdAt = $createdAt, i.state = $state, i.stateReason = $stateReason",
                                Values.parameters("iid", iid, "title", issueTitle, "createdAt", issueCreatedAt,
                                        "state", issueState, "stateReason", issueStateReason,
                                        "pid", pid, "prid", prid));

                        Result result1 = tx.run("MATCH (p:Person {pid: $pid}), (pr:Project {prid: $prid}) " +
                                        "MERGE (p)-[:OWNS]->(pr)" +
                                        "RETURN p,pr",
                                Values.parameters("pid", pid, "prid", prid));
                        if (!result1.hasNext()) {
                            System.err.println("Beziehung zwischen Person " + pid + " und Projekt " + prid + " konnte nicht erstellt werden.");
                        }

                        Result result2 = tx.run("MATCH (p:Person {pid: $pid}), (i:Issue {iid: $iid}) " +
                                        "MERGE (p)-[:CREATED]->(i)" +
                                        "RETURN p,i",
                                Values.parameters("pid", pid, "iid", iid));
                        if (!result2.hasNext()) {
                            System.err.println("Beziehung zwischen Person " + pid + " und Issue " + iid + " konnte nicht erstellt werden.");
                        }

                        Result result3 = tx.run("MATCH (i:Issue {iid: $iid}), (pr:Project {prid: $prid}) " +
                                        "MERGE (i)-[:BELONGS_TO]->(pr)" +
                                        "RETURN i,pr",
                                Values.parameters("iid", iid, "prid", prid));
                        if (!result3.hasNext()) {
                            System.err.println("Beziehung zwischen Issue " + iid + " und Projekt " + prid + " konnte nicht erstellt werden.");
                        }

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