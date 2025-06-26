# Catálogo Literalura

Projeto desenvolvido em Java com Spring Boot para consumir dados da [API Gutendex](https://gutendex.com/) e persistir livros e autores em um banco de dados PostgreSQL, com funcionalidades de consulta e estatísticas.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.5.3
- Spring Data JPA
- PostgreSQL
- Maven

## Funcionalidades

- Buscar livros por título via API Gutendex
- Persistir livros e autores no banco de dados
- Listar livros buscados na sessão atual
- Listar autores
- Consultar autores vivos em determinado ano
- Exibir estatísticas por idioma dos livros salvos

## Como Executar o Projeto

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/catalogo-literalura.git
cd catalogo-literalura
````

2. Configure seu banco PostgreSQL local com uma base chamada `catalogo`. Exemplo:

```bash
psql -U postgres
CREATE DATABASE catalogo;
```

3. Altere o arquivo `src/main/resources/application.properties` com suas credenciais:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/catalogo
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

4. Execute o projeto com Maven:

```bash
./mvnw spring-boot:run
```

## Exemplo de Uso

```
--- CATÁLOGO LITERALURA ---
1 - Buscar livro por título
2 - Listar todos os livros buscados
3 - Listar autores
4 - Listar autores vivos em um determinado ano
5 - Exibir quantidade de livros por idioma
0 - Sair
```

## Status do Projeto

✔️ Funcionalidades principais implementadas
🚧 Melhorias futuras planejadas:

* Tratamento de múltiplos autores por livro
* Testes automatizados com JUnit
* Interface gráfica (JavaFX ou Web)
* Integração com Docker

## Melhorias Futuras

O projeto ainda está em desenvolvimento e receberá aprimoramentos, como:

- [ ] Validação mais robusta dos dados retornados da API Gutendex
- [ ] Tratamento completo para livros sem autor ou com dados incompletos
- [ ] Filtro de autores por nacionalidade e século de nascimento
- [ ] Testes automatizados com JUnit e Spring Boot Test
- [ ] Paginação na listagem de livros e autores
- [ ] Internacionalização (i18n) para outros idiomas

## Autor

Desenvolvido por Matheus Vaz no programa ONE | Alura + Oracle.