import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        try{
            requestUrl();
        }catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void requestUrl() throws Exception{
        HttpURLConnection conn = null;
        JSONObject responseJson = null;
        //↓ classes라는 INDEX가 있는지 확인, pretty는 예쁘게 보이도록
        //String url = "http://127.0.0.1:9200/classes";
        String HOST_URL = "http://127.0.0.1:9200/classes";
        URL url = new URL(HOST_URL);

        conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.setRequestMethod("DELETE");
        //conn.setDoOutput(true);

        JSONObject commands = new JSONObject();

        int responseCode = conn.getResponseCode();
        System.out.println("responseCode = " + responseCode);
        if (responseCode == 400 || responseCode == 401 || responseCode == 500 ) {
            System.out.println(responseCode + " Error!");
        } else {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }


            //responseJson = new JSONObject(sb.toString());

            System.out.println(sb.toString());
        }
    }
}
