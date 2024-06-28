package br.ueg.musica.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_MUSICA")
public class MusicaModel {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "nome_banda")
    private String nomeBanda;

    @Column(name = "nome_musica")
    private String nomeMusica;

    @Column(name = "nome_album")
    private String nomeAlbum;

    @Column(name = "data_lancamento")
    private LocalDate dataLancamento;

    @Column(name = "duracao")
    private Long duracao;
}

