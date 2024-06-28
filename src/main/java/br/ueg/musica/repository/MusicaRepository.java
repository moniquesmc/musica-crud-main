package br.ueg.musica.repository;

import br.ueg.musica.model.MusicaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicaRepository extends JpaRepository<MusicaModel, Long> {
    List<MusicaModel> findByNomeMusicaContainingIgnoreCase(String nome);
    List<MusicaModel> findByNomeBandaContainingIgnoreCase(String nomeBanda);
}


