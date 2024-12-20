import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class GraphQL {
    private static final String TARGETURL = "130.61.19.194:8080";
    private static final int RUNS = 100;


    public void runRequest() {
        try {
            executeGetRequestIssueProjectsPeople();

/*
            System.out.println("---------------/api/resource---------------");
            for (int i = 0; i <= RUNS; i++){
                executeHeadRequest();
            }



            System.out.println("---------------/api/person/?id---------------");
            for (int i = 0; i <= RUNS; i++){
                executeGetRequestPersonById();
            }

            System.out.println("---------------/api/person---------------");
            for (int i = 0; i <= RUNS; i++){
                executeGetRequestPersons();
        }
         */
   /*
      System.out.println("---------------/api/persons/{pid}/projects/issues---------------");
            for (int i = 0; i <= RUNS; i++){
                executeGetRequestPersonsProjectsIssues();
            }

          System.out.println("---------------/api/persons/{pid}/projects/{prid}/issues---------------");
            for (int i = 0; i <= RUNS; i++){
                executePostRequestPersonsProjectsIssues();
            }
*/
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private void executeHeadRequest() {
        long startTime;
        long endTime;
        try{
            URL url = new URL(TARGETURL + "/api/resource");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("HEAD");
            con.setUseCaches(false);
            startTime = System.currentTimeMillis();
            int responseCode= con.getResponseCode();
            endTime = System.currentTimeMillis();
            System.out.println(" Response Time = " + (endTime - startTime + " ms | ResponseCode : " + responseCode));
            con.disconnect();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void executeGetRequestIssueProjectsPeople() {
        long startTime;
        long endTime;
        try{
        int i = 3;
        int j = 1;
        System.out.println("--------------JOINS: " + i + "--------------");
        for(int k = 1; k <= 50; k+= 1) {
            URL url = new URL(TARGETURL + "/apis/graphql");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");
            con.setUseCaches(false);
            con.setDoOutput(true);

            String jsonBody = """
                        {
                            "query":"query IssuesCount{issuesCount(counter: %d, joins: %d){iid title createdAt state stateReason}}"
                        }
                    """.formatted(j,i);

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            startTime = System.currentTimeMillis();
            int responseCode = con.getResponseCode();
            endTime = System.currentTimeMillis();
            System.out.println("Counter=" + j + "Joins=" + i + " Response Time = " + (endTime - startTime + " ms | ResponseCode : " + responseCode));
            con.disconnect();
            }
        }

            catch (Exception e){
            e.printStackTrace();
        }
    }

    private void executeGetRequestPersonById() {
        long startTime;
        long endTime;
        try{
            URL url = new URL(TARGETURL +"/apis/graphql");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");
            con.setUseCaches(false);
            con.setDoOutput(true);

            long id = (new Random().nextLong(5000 - 1) + 1);
            String jsonBody = """
            {
                "query": "query Person { person(id: \\\"%d\\\") { pid firstname lastname email } }"
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
            con.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void executeGetRequestPersons() {
        long startTime;
        long endTime;
        try{
            URL url = new URL(TARGETURL +"/apis/graphql");
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
            con.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void executeGetRequestPersonsProjectsIssues(){
        long startTime;
        long endTime;
        try{
            URL url = new URL(TARGETURL +"/apis/graphql");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");
            con.setUseCaches(false);
            con.setDoOutput(true);

            long id = (new Random().nextLong(5000 - 1) + 1);
            String jsonBody = """
            {
                "query": "query Person {person(id: \\\"%d\\\") {pid projects {prid title createdAt issues {iid title createdAt state stateReason } } firstname lastname email }}"
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
            con.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void executePostRequestPersonsProjectsIssues() {
        long startTime;
        long endTime;
        try{
            URL url = new URL(TARGETURL +"/apis/graphql");
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
    "query": "mutation { createIssue(input: { title: \\\"test\\\", createdAt: \\\"2023-02-21T00:00:00\\\", state: \\\"Open\\\", stateReason: \\\"Bug\\\", pid: \\\"%d\\\", prid:\\\" %d\\\" }) { iid title createdAt state stateReason }}"
}
""".formatted(pid, prid);

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            startTime = System.currentTimeMillis();
            int responseCode = con.getResponseCode();
            endTime = System.currentTimeMillis();
            System.out.println("Response Time = " + (endTime - startTime) + " ms | ResponseCode: " + responseCode);
            con.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
