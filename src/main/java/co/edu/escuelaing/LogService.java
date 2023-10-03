package co.edu.escuelaing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

public class LogService {

    private static final String MONGODB_HOST = "172.24.0.5"; // Cambia esto con la dirección IP o nombre de host de tu instancia de MongoDB
    private static final int MONGODB_PORT = 27017; // Cambia esto si el puerto de MongoDB es diferente
    private static final String DATABASE_NAME = "db";
    private static final String COLLECTION_NAME = "collection";



    public static void main(String[] args) {
        System.out.println("Log Service Server");
        port(getPort());

        /*ConnectionString connectionString = new ConnectionString("mongodb://" + MONGODB_HOST + ":" + MONGODB_PORT);
        System.out.println("EXITOSO");

        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
        System.out.println("exitoso2");*/




        get("/logservice", (req, pesp) -> {
            String val = req.queryParams("message");
            System.out.println("SERVICIOOOO" + val);
            //return logMessage(collection, val);
            return """
               {
               "m1":"mensaj1",
               "m2":"mensaj1",
               "m3":"mensaj1",
                }
                """;
        });



    }



    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4568;
    }




    private static String logMessage(MongoCollection<Document> collection, String val) {
        // Almacena el mensaje en MongoDB
        Document logEntry = new Document()
                .append("message", val)
                .append("timestamp", new Date());

        collection.insertOne(logEntry);

        // Recupera las últimas 10 entradas de registro ordenadas por timestamp descendente
        FindIterable<Document> recentLogEntries = collection.find()
                .sort(Sorts.descending("timestamp"))
                .limit(10);

        // Construye una respuesta JSON en el formato especificado
        JsonObject jsonResponse = new JsonObject();

        int index = 1;
        for (Document entry : recentLogEntries) {
            String messageKey = "m" + index;
            String messageValue = entry.getString("message");
            jsonResponse.addProperty(messageKey, messageValue);
            index++;
        }

        // Convierte el objeto JSON en una cadena
        String jsonResult = jsonResponse.toString();

        return jsonResult;
    }
}