package com.thiagodev.screensound.repository;

import com.thiagodev.screensound.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {

    @Query("SELECT m FROM Musica m JOIN m.artista a WHERE a.nome = :artista")
    List<Musica> findByArtista(String artista);
}
