package com.literalura.catalogo.repository;

import com.literalura.catalogo.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    long countByIdiomaIgnoreCase(String idioma);
}