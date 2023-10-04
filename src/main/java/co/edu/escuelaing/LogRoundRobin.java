package co.edu.escuelaing;

import java.io.IOException;

import static spark.Spark.*;

/**
 * La clase `LogRoundRobin` es el servidor principal que utiliza el framework Spark para
 * manejar solicitudes HTTP entrantes y dirigirlas al servicio de registro remoto.
 * @author juan.teran
 */
public class LogRoundRobin {


    /**
     * Método principal que inicia el servidor LogRoundRobin.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        port(getPort());



        staticFiles.location("/public");



        get("/log", (req, pesp) -> {
            String val = req.queryParams("message");
            return logMessage(val);
        });



    }



    /**
     * Obtiene el puerto en el que el servidor debe escuchar. Si no se especifica, se utiliza el puerto 4567.
     *
     * @return El puerto en el que escuchará el servidor.
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }


    /**
     * Registra un mensaje llamando al servicio remoto a través de HttpRemoteCaller.
     *
     * @param val El mensaje que se va a registrar.
     * @return La respuesta del servicio remoto después de registrar el mensaje.
     * @throws IOException Si se produce un error al realizar la llamada HTTP.
     */
    private static String logMessage(String val) throws IOException {
        return HttpRemoteCaller.remoteLogCall(val);
    }
}