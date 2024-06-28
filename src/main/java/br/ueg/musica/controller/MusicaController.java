package br.ueg.musica.controller;

import br.ueg.musica.dto.MusicaDto;
import br.ueg.musica.mapper.MusicaMapper;
import br.ueg.musica.model.MusicaModel;
import br.ueg.musica.service.MusicaService;
import br.ueg.musica.service.impl.MusicaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "")
public class MusicaController {

    @Autowired
    private MusicaService musicaService;

    @Autowired
    private MusicaMapper musicaMapper;

    @PostMapping("/incluir")
    public String incluir(@RequestBody @Valid MusicaDto musicaDto) {
        // Validação de campos obrigatórios
        if (musicaDto.getNomeMusica() == null || musicaDto.getNomeMusica().isEmpty()) {
            throw new IllegalArgumentException("O nome da música é obrigatório.");
        }
        if (musicaDto.getNomeBanda() == null || musicaDto.getNomeBanda().isEmpty()) {
            throw new IllegalArgumentException("O nome do artista é obrigatório.");
        }
        // Regra de negócio: verificar se a música já existe
        List<MusicaModel> musicasExistentes = musicaService.listar();
        for (MusicaModel musica : musicasExistentes) {
            if (musica.getNomeMusica().equalsIgnoreCase(musicaDto.getNomeMusica()) &&
                    musica.getNomeBanda().equalsIgnoreCase(musicaDto.getNomeBanda())) {
                throw new IllegalArgumentException("A música já existe no sistema.");
            }
        }
        MusicaModel musicaModel = musicaMapper.toModelo(musicaDto);
        musicaService.incluir(musicaModel);
        return musicaDto.toString();
    }




    @PutMapping(path = "/alterar/{id}")
    public MusicaDto alterar(@RequestBody() MusicaDto musicaDto, @PathVariable(name = "id") Long idMusica) {
        MusicaModel musicaModel = musicaMapper.toModelo(musicaDto);
        musicaModel.setId(idMusica);
        MusicaModel alterar = musicaService.alterar(musicaModel);
        return musicaMapper.toMusicaDto(alterar);
    }

    @DeleteMapping(path = "/excluir/{id}")
    public MusicaDto excluir(@PathVariable(name = "id") Long idMusica) {
        MusicaModel musicaModel = this.musicaService.excluir(idMusica);
        return musicaMapper.toMusicaDto(musicaModel);
    }

    @GetMapping(path="/lista")
    public List<MusicaDto> listAll() {
        List<MusicaModel> musicas = musicaService.listar();
        return musicaMapper.toListDto(musicas);
    }
    @GetMapping(path="/buscarPorNome")
    public List<MusicaDto> buscarPorNome(@RequestParam(name = "nome") String nome) {
        List<MusicaModel> musicas = musicaService.buscarPorNome(nome);
        return musicaMapper.toListDto(musicas);
    }
    @GetMapping(path="/filtrarPorArtista")
    public List<MusicaDto> filtrarPorArtista(@RequestParam(name = "nomeBanda") String nomeBanda) {
        List<MusicaModel> musicas = musicaService.filtrarPorArtista(nomeBanda);
        return musicaMapper.toListDto(musicas);
    }


}
