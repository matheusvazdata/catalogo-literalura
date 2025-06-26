package com.literalura.catalogo.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "ano_nascimento")
    private Integer anoDeNascimento;

    @Column(name = "ano_falecimento")
    private Integer anoDeFalecimento;

    // Construtor padrão exigido pelo JPA
    public Autor() {}

    // Construtor com campos
    public Autor(String nome, Integer anoDeNascimento, Integer anoDeFalecimento) {
        this.nome = nome;
        this.anoDeNascimento = anoDeNascimento;
        this.anoDeFalecimento = anoDeFalecimento;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getAnoDeNascimento() {
        return anoDeNascimento;
    }

    public Integer getAnoDeFalecimento() {
        return anoDeFalecimento;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAnoDeNascimento(Integer anoDeNascimento) {
        this.anoDeNascimento = anoDeNascimento;
    }

    public void setAnoDeFalecimento(Integer anoDeFalecimento) {
        this.anoDeFalecimento = anoDeFalecimento;
    }

    // equals e hashCode para evitar duplicação em consultas com stream.distinct()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Autor)) return false;
        Autor autor = (Autor) o;
        return Objects.equals(nome.toLowerCase(), autor.nome.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome.toLowerCase());
    }

    // toString para exibição amigável
    @Override
    public String toString() {
        return nome +
                (anoDeNascimento != null ? " (nascido em " + anoDeNascimento : "") +
                (anoDeFalecimento != null ? ", falecido em " + anoDeFalecimento + ")" : (anoDeNascimento != null ? ")" : ""));
    }
}