package co.edu.escuelaing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRemoteCaller {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String[] LOG_SERVICES = new String[]{"http://web1:6000/logservice",
            "http://web2:6000/logservice",
            "http://web3:6000/logservice"};//

    private static int currentServer = 0;

    public static String remoteLogCall(String message) throws IOException {
        //rotateRoundRobinServer();//
        return remoteHttpCall(LOG_SERVICES[currentServer], message);
    }
    public static String remoteHttpCall(String url, String message) throws IOException {

        URL obj = new URL(url+"?message="+message);
        System.out.println("OBJETO" + obj);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        //con.connect();

       /* String urlParameters = "message=" + message;
        con.setDoOutput(true);
        con.getOutputStream().write(urlParameters.getBytes("UTF-8"));*/

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        StringBuffer response = new StringBuffer();
        System.out.println("HOLAA" + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
            return "404 error";
        }
        System.out.println("GET DONE");
        rotateRoundRobinServer();//
        System.out.println("RESPUESTA" + response.toString());
        return response.toString();
    }

    public static void rotateRoundRobinServer(){
        currentServer = (currentServer + 1) % 3;

    }
}
