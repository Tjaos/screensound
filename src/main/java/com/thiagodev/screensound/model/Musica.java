package com.thiagodev.screensound.model;

import jakarta.persistence.*;

@Entity
@Table(name = "musicas")
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    private Genero genero;

    @ManyToOne()
    private Artista artista;

    public Musica() {
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Musica(Long id, String nome, Genero genero, Artista artista) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.artista = artista;
    }

    public Musica(DadosMusica dados) {
        this.id = dados.id();
        this.nome = dados.nome();
        this.genero = dados.genero();
        this.artista = dados.artista();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return "Musica: " +
                "id = " + id +
                ", nome = " + nome +
                "gênero = " + genero +
                ", artista = " + artista.getNome();
    }
}
