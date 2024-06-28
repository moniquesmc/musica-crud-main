package br.ueg.musica.service.impl;

import br.ueg.musica.model.MusicaModel;
import br.ueg.musica.repository.MusicaRepository;
import br.ueg.musica.service.MusicaService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MusicaServiceImpl implements MusicaService {

    @Autowired
    private MusicaRepository musicaRepository;

    public void incluir(MusicaModel musicaModel) {
        this.validarCamposObrigatorios(musicaModel);
        musicaRepository.save(musicaModel);
    }

    private void validarCamposObrigatorios(MusicaModel musica) {
        if (Objects.isNull(musica)) {
            throw new IllegalArgumentException("Campo precisa ser preenchida");
        }
        List<String> campoVazio = new ArrayList<>();
        if (Strings.isEmpty(musica.getNomeMusica())) {
            campoVazio.add("Nome musica");
        }
        if (Strings.isEmpty(musica.getNomeBanda())) {
            campoVazio.add("Nome banda");
        }
        if (Strings.isEmpty(musica.getNomeAlbum())) {
            campoVazio.add("Nome album");
        }
        if (musica.getDuracao() == null) {
            campoVazio.add("Duração");
        }
        if (musica.getDataLancamento() == null) {
            campoVazio.add("Data lançamento");
        }
        if (!campoVazio.isEmpty()) {
            throw new IllegalArgumentException(
                    "Campos obrigatórios não preenchidos (" +
                            String.join(",", campoVazio) + ")"
            );
        }

    }

    @Override
    public MusicaModel alterar(MusicaModel musicaModel) {
        return musicaRepository.save(musicaModel);
    }

    @Override
    public MusicaModel excluir(Long idMusica) {
        MusicaModel excluirMusica = this.pesquisarMusicaOuGeraErro(idMusica);
        this.musicaRepository.delete(excluirMusica);
        return excluirMusica;
    }

    @Override
    public List<MusicaModel> listar() {
        return musicaRepository.findAll();
    }

    private MusicaModel pesquisarMusicaOuGeraErro(Long idMusica) {
        MusicaModel musicaModel = musicaRepository.findById(idMusica).orElseThrow(() -> new IllegalArgumentException("Música não encontrada"));
        return musicaModel;
    }

    @Override
    public List<MusicaModel> buscarPorNome(String nome) {
        return musicaRepository.findByNomeMusicaContainingIgnoreCase(nome);
    }

    @Override
    public List<MusicaModel> filtrarPorArtista(String nomeBanda) {
        return musicaRepository.findByNomeBandaContainingIgnoreCase(nomeBanda);
    }
}
