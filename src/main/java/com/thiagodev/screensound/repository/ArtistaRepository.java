package com.thiagodev.screensound.repository;

import com.thiagodev.screensound.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    @Query("SELECT a FROM Artista a WHERE a.nome = :artistaMusica")
    Artista findByNome(String artistaMusica);
}
