package com.literalura.catalogo;

import com.literalura.catalogo.model.Autor;
import com.literalura.catalogo.model.Livro;
import com.literalura.catalogo.repository.AutorRepository;
import com.literalura.catalogo.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Menu {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    private final Scanner scanner = new Scanner(System.in);
    private final ConsultaGutendex consulta = new ConsultaGutendex();
    private final List<LivroResponse> livrosBuscados = new ArrayList<>();

    public void exibirMenu() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- CATÁLOGO LITERALURA ---");
            System.out.println("1 - Buscar livro por título");
            System.out.println("2 - Listar todos os livros buscados");
            System.out.println("3 - Listar autores");
            System.out.println("4 - Listar autores vivos em um determinado ano");
            System.out.println("5 - Exibir quantidade de livros por idioma");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
                continue;
            }

            switch (opcao) {
                case 1 -> buscarLivro();
                case 2 -> listarLivros();
                case 3 -> listarAutores();
                case 4 -> listarAutoresVivosPorAno();
                case 5 -> exibirLivrosPorIdioma();
                case 0 -> System.out.println("Encerrando aplicação...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void buscarLivro() {
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();

        try {
            LivroResponse livroResponse = consulta.buscarLivroPorTitulo(titulo);
            if (livroResponse != null) {
                livrosBuscados.add(livroResponse);
                System.out.println("Livro encontrado:\n" + livroResponse);

                AutorResponse autorResp = livroResponse.getAutorPrincipal();
                if (autorResp == null || autorResp.getNome() == null) {
                    System.out.println("Livro encontrado, mas sem autor definido. Não será salvo no banco.");
                    return;
                }

                Autor autor = autorRepository
                        .findByNomeIgnoreCase(autorResp.getNome())
                        .orElseGet(() -> autorRepository.save(
                                new Autor(autorResp.getNome(), autorResp.getAnoDeNascimento(), autorResp.getAnoDeFalecimento()))
                        );

                Livro livro = new Livro(
                        livroResponse.getTitulo(),
                        autor,
                        livroResponse.getIdiomaPrincipal(),
                        livroResponse.getNumeroDownloads()
                );
                livroRepository.save(livro);
            } else {
                System.out.printf("Nenhum livro encontrado com o título \"%s\".\n", titulo);
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());
        }
    }

    private void listarLivros() {
        if (livrosBuscados.isEmpty()) {
            System.out.println("Nenhum livro foi buscado ainda.");
            return;
        }

        System.out.println("\n--- Livros Buscados ---");
        livrosBuscados.forEach(System.out::println);
    }

    private void listarAutores() {
        if (livrosBuscados.isEmpty()) {
            System.out.println("Nenhum autor disponível. Busque livros primeiro.");
            return;
        }

        System.out.println("\n--- Autores dos Livros Buscados ---");
        livrosBuscados.stream()
                .map(LivroResponse::getAutorPrincipal)
                .filter(Objects::nonNull)
                .distinct()
                .forEach(System.out::println);
    }

    private void listarAutoresVivosPorAno() {
        System.out.print("Digite o ano para filtrar autores vivos: ");
        try {
            int ano = Integer.parseInt(scanner.nextLine());

            List<Autor> autoresVivos = new ArrayList<>();
            autoresVivos.addAll(autorRepository.findByAnoDeNascimentoLessThanEqualAndAnoDeFalecimentoIsNull(ano));
            autoresVivos.addAll(autorRepository.findByAnoDeNascimentoLessThanEqualAndAnoDeFalecimentoGreaterThan(ano, ano));

            if (autoresVivos.isEmpty()) {
                System.out.println("Nenhum autor encontrado vivo no ano " + ano);
            } else {
                System.out.println("\n--- Autores vivos em " + ano + " ---");
                autoresVivos.stream().distinct().forEach(System.out::println);
            }
        } catch (NumberFormatException e) {
            System.out.println("Ano inválido. Digite um número.");
        }
    }

    private void exibirLivrosPorIdioma() {
        System.out.println("\n--- Estatísticas por Idioma ---");
        List<String> idiomas = Arrays.asList("en", "pt", "fr", "es");

        idiomas.forEach(idioma -> {
            long total = livroRepository.countByIdiomaIgnoreCase(idioma);
            System.out.printf("Idioma \"%s\": %d livro(s) no banco.\n", idioma, total);
        });
    }
}