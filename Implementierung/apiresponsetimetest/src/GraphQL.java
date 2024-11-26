import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class GraphQL {
    private static final String TARGETURL = "http://localhost:8080/apis/graphql";
    private static final int RUNS = 100;


    public void runRequest() {
        try {
            System.out.println("---------------/api/person/?id---------------");
            for (int i = 0; i <= RUNS; i++){
                executeGetRequestPersonById();
            }
            System.out.println("---------------/api/person---------------");
            for (int i = 0; i <= RUNS; i++){
                executeGetRequestPersons();
            }

            System.out.println("---------------/api/persons/{pid}/projects/issues---------------");
            for (int i = 0; i <= RUNS; i++){
                executeGetRequestPersonsProjectsIssues();
            }

            System.out.println("---------------/api/persons/{pid}/projects/{prid}/issues---------------");
            for (int i = 0; i <= RUNS; i++){
                executePostRequestPersonsProjectsIssues();
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private void executeGetRequestPersonById() {
        long startTime;
        long endTime;
        try{
            URL url = new URL(TARGETURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");
            con.setUseCaches(false);
            con.setDoOutput(true);

            long id = (new Random().nextLong(5000 - 1) + 1);
            String jsonBody = """
            {
                "query": "query Person { person(id: %d) { pid firstname lastname email } }"
            }
        """.formatted(id);

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            startTime = System.currentTimeMillis();
            int responseCode = con.getResponseCode();
            endTime = System.currentTimeMillis();
            System.out.println("Response Time = " + (endTime - startTime) + " ms | ResponseCode: " + responseCode);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void executeGetRequestPersons() {
        long startTime;
        long endTime;
        try{
            URL url = new URL(TARGETURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");
            con.setUseCaches(false);
            con.setDoOutput(true);

            String jsonBody = """
            {
                "query": "query Persons{persons{pid firstname lastname email } }"
            }
        """;

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            startTime = System.currentTimeMillis();
            int responseCode = con.getResponseCode();
            endTime = System.currentTimeMillis();
            System.out.println("Response Time = " + (endTime - startTime) + " ms | ResponseCode: " + responseCode);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void executeGetRequestPersonsProjectsIssues(){
        long startTime;
        long endTime;
        try{
            URL url = new URL(TARGETURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");
            con.setUseCaches(false);
            con.setDoOutput(true);

            long id = (new Random().nextLong(5000 - 1) + 1);
            String jsonBody = """
            {
                "query": "query Person {person(id: %d) {pid projects {prid title createdAt issues {iid title createdAt state stateReason } } firstname lastname email }}"
            }
        """.formatted(id);

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            startTime = System.currentTimeMillis();
            int responseCode = con.getResponseCode();
            endTime = System.currentTimeMillis();
            System.out.println("Response Time = " + (endTime - startTime) + " ms | ResponseCode: " + responseCode);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void executePostRequestPersonsProjectsIssues() {
        long startTime;
        long endTime;
        try{
            URL url = new URL(TARGETURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");
            con.setUseCaches(false);
            con.setDoOutput(true);

            long pid = (new Random().nextLong(5000 - 1) + 1);
            long prid = (new Random().nextLong(2500 - 1) + 1);
            String jsonBody = """
            {
                "mutation": mutation CreateIssue {createIssue( input: { title: "test", createdAt: "2023-02-21T00:00:00", state: "Open", stateReason: "Bug" pid: %d prid: %e}) { iid title createdAt state stateReason }}            }
        """.formatted(pid,prid);

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            startTime = System.currentTimeMillis();
            int responseCode = con.getResponseCode();
            endTime = System.currentTimeMillis();
            System.out.println("Response Time = " + (endTime - startTime) + " ms | ResponseCode: " + responseCode);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
