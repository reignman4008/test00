package mx.axa.gestiondocumental.monitoreo.controller;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestClient {
    public static void main(String[] args) {
        // URL del servicio web
        String url = "https://api.restful-api.dev/objects";

        // Crear el objeto a enviar
        Data data = new Data("example", 123);
        RequestObject requestObject = new RequestObject("Sample Object", data);

        // Convertir el objeto a JSON usando Gson
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(requestObject);

        // Crear un cliente HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Construir la solicitud HttpRequest con el método POST
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json") // Configurar cabeceras
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        try {
            // Enviar la solicitud y obtener la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Imprimir el código de estado y el cuerpo de la respuesta
            System.out.println("Código de estado: " + response.statusCode());
            System.out.println("Respuesta: " + response.body());
        } catch (Exception e) {
            // Manejar errores
            System.err.println("Error al realizar la solicitud: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Clase para representar la estructura del objeto
    static class RequestObject {
        private String name;
        private Data data;

        public RequestObject(String name, Data data) {
            this.name = name;
            this.data = data;
        }

        // Getters y setters si son necesarios
    }

    static class Data {
        private String type;
        private int value;

        public Data(String type, int value) {
            this.type = type;
            this.value = value;
        }

        // Getters y setters si son necesarios
    }
}
