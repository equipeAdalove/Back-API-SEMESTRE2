package com.adalove.api.model.services;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.*;

public class OllamaServiceTest {

    // URL base do Ollama (ajuste a URL conforme a configuração local do Ollama)
    private static final String OLLAMA_URL = "http://localhost:11434/"; // Ex: http://localhost:5000

    @Test
    public void testConnection() throws IOException {
        // Tenta estabelecer uma conexão com a URL do Ollama
        HttpURLConnection connection = null;
        try {
            URL url = new URL(OLLAMA_URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET"); // ou POST dependendo da configuração do Ollama

            // Conecta e obtém o código de resposta HTTP
            int responseCode = connection.getResponseCode();

            // Verifica se a conexão foi bem-sucedida (código de resposta 200)
            assertEquals(200, responseCode, "Conexão com o Ollama falhou!");
        } catch (IOException e) {
            // Se ocorrer uma exceção, a conexão falhou
            fail("Erro ao tentar conectar ao Ollama: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
