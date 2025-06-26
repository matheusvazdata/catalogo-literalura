package com.literalura.catalogo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LivroResponse {

    @JsonAlias("title")
    private String titulo;

    @JsonAlias("authors")
    private List<AutorResponse> autores;

    @JsonAlias("languages")
    private List<String> idiomas;

    @JsonAlias("download_count")
    private Integer numeroDownloads;

    public String getTitulo() {
        return titulo;
    }

    public AutorResponse getAutorPrincipal() {
        return autores != null && !autores.isEmpty() ? autores.get(0) : null;
    }

    public String getIdiomaPrincipal() {
        return idiomas != null && !idiomas.isEmpty() ? idiomas.get(0) : "desconhecido";
    }

    public Integer getNumeroDownloads() {
        return numeroDownloads;
    }

    @Override
    public String toString() {
        return "Livro: " + titulo + "\n" +
                "Autor: " + getAutorPrincipal() + "\n" +
                "Idioma: " + getIdiomaPrincipal() + "\n" +
                "Downloads: " + numeroDownloads + "\n";
    }
}