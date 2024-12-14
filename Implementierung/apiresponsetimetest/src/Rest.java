
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Rest {
    private static final String TARGETURL = "https://5b83-2a00-79c0-64c-5500-d53e-7b21-ccf9-6caf.ngrok-free.app/";
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
            URL url = new URL(TARGETURL + "api/resource");

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
            for(int i = 0; i < 4; i++){
                System.out.println("--------------JOINS: " + i + "--------------");
                for(int j = 1; i <= 5000; j+= 100){
                    URL url = new URL(TARGETURL + "api/issues?counter=" + j + "&joins=" + i);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("Accept", "application/json");
                    con.setUseCaches(false);
                    startTime = System.currentTimeMillis();
                    int responseCode= con.getResponseCode();
                    endTime = System.currentTimeMillis();
                    System.out.println(" Response Time = " + (endTime - startTime + " ms | ResponseCode : " + responseCode));
                    con.disconnect();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void executeGetRequestPersonById() {
            long startTime;
            long endTime;
            try{
                URL url = new URL(TARGETURL + "api/person/" + (new Random().nextLong(5000-1)+1));

                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Accept", "application/json");
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


    private void executeGetRequestPersons() {
        long startTime;
        long endTime;
        try{
            URL url = new URL(TARGETURL + "api/person");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
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

    private void executeGetRequestPersonsProjectsIssues() {
        long startTime;
        long endTime;
        try{
            URL url = new URL(TARGETURL + "api/persons/" + (new Random().nextLong(2500-1)+1) + "/projects/issues");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
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

    private void executePostRequestPersonsProjectsIssues() {
        long startTime;
        long endTime;
        try{
            URL url = new URL(TARGETURL + "api/persons/"+(new Random().nextLong(5000-1)+1)+"/projects/"+(new Random().nextLong(2500-1)+1)+"/issues");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");
            con.setUseCaches(false);
            con.setDoOutput(true);

            String jsonBody = """
                {
                    "title":"test",
                    "createdAt":"2023-02-21T00:00:00",
                    "state":"Open",
                    "stateReason":"Bug"
                }
                """;

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            startTime = System.currentTimeMillis();
            int responseCode= con.getResponseCode();
            endTime = System.currentTimeMillis();
            System.out.println(" Response Time = " + (endTime - startTime + " ms | ResponseCode : " + responseCode));
            con.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
