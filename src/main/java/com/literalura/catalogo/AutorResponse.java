package com.literalura.catalogo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AutorResponse {

    @JsonAlias("name")
    private String nome;

    @JsonAlias("birth_year")
    private Integer anoDeNascimento;

    @JsonAlias("death_year")
    private Integer anoDeFalecimento;

    public String getNome() {
        return nome;
    }

    public Integer getAnoDeNascimento() {
        return anoDeNascimento;
    }

    public Integer getAnoDeFalecimento() {
        return anoDeFalecimento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(nome);
        if (anoDeNascimento != null) {
            sb.append(" (nascido em ").append(anoDeNascimento);
            if (anoDeFalecimento != null) {
                sb.append(", falecido em ").append(anoDeFalecimento).append(")");
            } else {
                sb.append(")");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AutorResponse outro)) return false;
        return nome != null && nome.equalsIgnoreCase(outro.nome);
    }

    @Override
    public int hashCode() {
        return nome != null ? nome.toLowerCase().hashCode() : 0;
    }
}