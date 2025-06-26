package com.literalura.catalogo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String idioma;

    @Column(name = "numero_downloads")
    private Integer numeroDownloads;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    // Construtor padrão JPA
    public Livro() {}

    // Construtor com todos os campos relevantes
    public Livro(String titulo, Autor autor, String idioma, Integer numeroDownloads) {
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.numeroDownloads = numeroDownloads;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public Integer getNumeroDownloads() {
        return numeroDownloads;
    }

    public Autor getAutor() {
        return autor;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setNumeroDownloads(Integer numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    // toString para exibição no menu
    @Override
    public String toString() {
        return "Livro: " + titulo +
                "\nAutor: " + autor +
                "\nIdioma: " + idioma +
                "\nDownloads: " + numeroDownloads + "\n";
    }
}