package com.literalura.catalogo.repository;

import com.literalura.catalogo.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNomeIgnoreCase(String nome);

    // Autores com ano de falecimento nulo e nascidos antes ou durante o ano
    List<Autor> findByAnoDeNascimentoLessThanEqualAndAnoDeFalecimentoIsNull(int ano);

    // Autores com ano de falecimento depois do ano informado e nascidos antes ou durante o ano
    List<Autor> findByAnoDeNascimentoLessThanEqualAndAnoDeFalecimentoGreaterThan(int anoNascimento, int anoFalecimento);
}