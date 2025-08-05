package comliteralura.cliente;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {

    private static final String BASE_URL = "https://gutendex.com/books/?search=";

    private final HttpClient client = HttpClient.newHttpClient();

    public String obtenerDatosLibro(String titulo) {
        String tituloFormateado = titulo.replace(" ", "+");
        String url = BASE_URL + tituloFormateado;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al hacer la solicitud HTTP: " + e.getMessage());
        }
    }
}
