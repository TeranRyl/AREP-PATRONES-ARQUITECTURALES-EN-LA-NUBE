package co.edu.escuelaing.logservice;

import java.io.IOException;

import static spark.Spark.*;

public class LogService {



    public static void main(String[] args) {
        System.out.println("Log Service Server");
        port(getPort());




        get("/logservice", (req, pesp) -> {
            String val = req.queryParams("value");
            return logMessage(val);
        });



    }



    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4568;
    }




    private static String logMessage(String val) {
        return """
               {
               "m1":"mensaj1",
               "m2":"mensaj1",
               "m3":"mensaj1",
                }
                """;
    }
}