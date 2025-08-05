package comliteralura.conversor;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ConversorDatos {

    private final ObjectMapper mapper = new ObjectMapper();

    public <T> T convertirDatos(String json, Class<T> clase) {
        try {
            return mapper.readValue(json, clase);
        } catch (IOException e) {
            throw new RuntimeException("Error al convertir JSON: " + e.getMessage());
        }
    }
}
