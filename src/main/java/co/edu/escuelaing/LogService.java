package co.edu.escuelaing;

import java.util.Date;
import static spark.Spark.*;
import com.google.gson.JsonObject;
import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

public class LogService {

    private static final String MONGODB_HOST = "db";
    private static final int MONGODB_PORT = 27017;
    private static final String DATABASE_NAME = "db";
    private static final String COLLECTION_NAME = "collection";



    public static void main(String[] args) {
        System.out.println("Log Service Server");
        port(getPort());

        ConnectionString connectionString = new ConnectionString("mongodb://" + MONGODB_HOST + ":" + MONGODB_PORT);

        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);




        get("/logservice", (req, pesp) -> {
            String val = req.queryParams("message");
            return logMessage(collection, val);
        });



    }



    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4568;
    }




    private static String logMessage(MongoCollection<Document> collection, String val) {
        Document logEntry = new Document()
                .append("message", val)
                .append("date", new Date());

        collection.insertOne(logEntry);
        FindIterable<Document> recentLogEntries = collection.find()
                .sort(Sorts.descending("date"))
                .limit(10);

        JsonObject response = new JsonObject();
        int index = 1;

        for (Document entry : recentLogEntries) {
            String messageKey = "Mensaje" + index;
            String dateKey = "Fecha" + index;
            String messageValue = entry.getString("message");
            String dateValue = entry.getDate("date").toString();

            response.addProperty(messageKey, messageValue);
            response.addProperty(dateKey, dateValue);

            index++;
        }


        return response.toString();
    }
}