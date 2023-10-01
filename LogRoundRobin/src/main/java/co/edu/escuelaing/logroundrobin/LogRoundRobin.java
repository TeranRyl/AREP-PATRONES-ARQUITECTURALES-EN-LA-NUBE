package co.edu.escuelaing.logroundrobin;

import java.io.IOException;

import static spark.Spark.*;

public class LogRoundRobin {



    public static void main(String[] args) {
        port(getPort());



        staticFiles.location("/public");



        get("/log", (req, pesp) -> {
            String val = req.queryParams("value");
            return logMessage(val);
        });



    }



    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }



    private static String logMessage(String val) throws IOException {
        return HttpRemoteCaller.remoteHttpCall("http://localhost:4568/logservice?message=" + val);
    }
}