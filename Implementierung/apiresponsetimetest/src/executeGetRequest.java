import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class executeGetRequest {
    private static final String TARGETURL = "http://localhost:8080/";
    private static final int RUNS = 100;

    public executeGetRequest() {
        //runGetRequestPerson();
        executeGetRequestPersonClosedIssueProjectCreatedBefore();
    }

    private void runGetRequestPerson() {
        try {
            for (int i = 0; i <= RUNS; i++){
                executeGetRequestPerson();
                Thread.sleep(500);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }


    private void executeGetRequestPerson() {
            long startTime;
            long endTime;
            try{
                URL url = new URL(TARGETURL + "person?id=" + (new Random().nextLong(100-1)+1));
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Accept", "application/json");
                con.setUseCaches(false);

                startTime = System.currentTimeMillis();
                InputStream is = con.getInputStream();
                endTime = System.currentTimeMillis();

                BufferedReader rd = new BufferedReader(new InputStreamReader(is));

                String line;
                StringBuilder response = new StringBuilder();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                }
                rd.close();
                System.out.println(response.toString() + " Response Time = " + (endTime - startTime + " ms"));

            }catch (Exception e){
                e.printStackTrace();
            }
    }


    private void executeGetRequestPersonClosedIssueProjectCreatedBefore() {
        long startTime;
        long endTime;
        try{
            URL url = new URL(TARGETURL + "personClosedIssueProjectCreatedBefore?date=2024-05-01");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            con.setUseCaches(false);

            startTime = System.currentTimeMillis();
            InputStream is = con.getInputStream();
            endTime = System.currentTimeMillis();

            BufferedReader rd = new BufferedReader(new InputStreamReader(is));

           /* String line;
            StringBuilder response = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }
            rd.close();
            System.out.println(response.toString() + " Response Time = " + (endTime - startTime + " ms"));

            */
            System.out.println(" Response Time = " + (endTime - startTime + " ms"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
