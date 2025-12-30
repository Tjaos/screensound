package com.thiagodev.screensound.principal;

import com.thiagodev.screensound.model.*;
import com.thiagodev.screensound.repository.ArtistaRepository;
import com.thiagodev.screensound.repository.MusicaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private final ArtistaRepository artistaRepository;
    private final MusicaRepository musicaRepository;

    public Principal(ArtistaRepository artistaRepository, MusicaRepository musicaRepository) {
        this.artistaRepository = artistaRepository;
        this.musicaRepository = musicaRepository;
    }

    public void exibeMenu(){
        var opcao = -1;
        while(opcao != 0){
            var menu = """
                    Escolha uma opção:
                    1 - Cadastrar artista
                    2 - Cadastrar música
                    3 - Pesquisar músicas por artistas
                    
                    
                    0 - sair
                    """;
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao){
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusica();
                    break;
                case 3:
                    pesquisarMusicaPorArtista();
                    break;


                case 0:
                    System.out.println("saindo...");
                    break;
                default:
                    System.out.println("Opção iválida");
            }

        }
    }


    private void cadastrarArtista() {
        System.out.println("Digite o nome do artista: ");
        String nome = leitura.nextLine();
        System.out.println("Digite o tipo do artista (SOLO, DUPLA ou BANDA)");
        String tipo = leitura.nextLine();

        TipoArtista tipoArtista;
        try {
            tipoArtista = TipoArtista.valueOf(tipo.trim().toUpperCase());
        }catch (IllegalArgumentException e){
            System.out.println("Tipo inválido! use SOLO, DUPLA ou BANDA");
            return;
        }
        DadosArtista dados = new DadosArtista(null, nome, tipoArtista);
        Artista artista = new Artista(dados);
        artistaRepository.save(artista);

        System.out.println("Artista cadastrado com sucesso!");

    }

    private void cadastrarMusica() {
        System.out.println("Digite o nome da música: ");
        String nome = leitura.nextLine();

        System.out.println("Digite o gênero da música: ");
        String genero = leitura.nextLine();

        System.out.println("Digite o nome do artista que canta a música: ");
        String artistaMusica = leitura.nextLine();
        Artista artista = artistaRepository.findByNome(artistaMusica);
        if(artista == null){
            System.out.println("Artista não encontrado!");
            return;
        }

        Genero generoMusica;

        try {
            generoMusica = Genero.valueOf(genero.trim().toUpperCase());
        }catch (IllegalArgumentException e){
            System.out.println("Gênero ausente no catálogo");
            System.out.println(""" 
                    Tente:
                        ROCK,
                        SERTANEJO,
                        MPB,
                        FUNK,
                        TRAP,
                        INTERNACIONAL,
                        FORRO,
                        GOSPEL,
                        PAGODE,
                        SAMBA,
                        AXE.
                    """);
            return;
        }
        DadosMusica dados = new DadosMusica(null, nome, generoMusica, artista);
        Musica musica = new Musica(dados);
        musicaRepository.save(musica);



    }

    private void pesquisarMusicaPorArtista() {
        System.out.println("Digite o nome do artista: ");
        var artista = leitura.nextLine();
        List<Musica> musicasEncontradas = musicaRepository.findByArtista(artista);

        musicasEncontradas.forEach(m ->
                System.out.println(
                        "Música: " + m.getNome() +
                                " | Gênero: " + m.getGenero() +
                                " | Artista: " + m.getArtista().getNome()
                )
        );

    }

}
