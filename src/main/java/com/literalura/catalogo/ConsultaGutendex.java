package com.literalura.catalogo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaGutendex {

    private final ObjectMapper mapper = new ObjectMapper();

    public LivroResponse buscarLivroPorTitulo(String titulo) {
        try {
            String url = "https://gutendex.com/books/?search=" + titulo.replace(" ", "+");
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonNode root = mapper.readTree(response.body());
            JsonNode results = root.get("results");

            if (results != null && results.isArray() && results.size() > 0) {
                JsonNode primeiroLivro = results.get(0);
                return mapper.readValue(primeiroLivro.toString(), LivroResponse.class);
            } else {
                System.out.println("Nenhum livro encontrado para o título informado.");
                return null;
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao consultar a API Gutendex", e);
        }
    }
}