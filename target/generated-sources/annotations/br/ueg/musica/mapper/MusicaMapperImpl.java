package br.ueg.musica.mapper;

import br.ueg.musica.dto.MusicaDto;
import br.ueg.musica.model.MusicaModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-26T23:57:23-0300",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240524-2033, environment: Java 17.0.11 (Eclipse Adoptium)"
)
@Component
public class MusicaMapperImpl implements MusicaMapper {

    @Override
    public MusicaModel toModelo(MusicaDto musicaDto) {
        if ( musicaDto == null ) {
            return null;
        }

        MusicaModel musicaModel = new MusicaModel();

        musicaModel.setDataLancamento( musicaDto.getDataLancamento() );
        musicaModel.setDuracao( musicaDto.getDuracao() );
        musicaModel.setNomeAlbum( musicaDto.getNomeAlbum() );
        musicaModel.setNomeBanda( musicaDto.getNomeBanda() );
        musicaModel.setNomeMusica( musicaDto.getNomeMusica() );

        return musicaModel;
    }

    @Override
    public MusicaDto toMusicaDto(MusicaModel musicaModel) {
        if ( musicaModel == null ) {
            return null;
        }

        MusicaDto musicaDto = new MusicaDto();

        musicaDto.setDataLancamento( musicaModel.getDataLancamento() );
        musicaDto.setDuracao( musicaModel.getDuracao() );
        musicaDto.setNomeAlbum( musicaModel.getNomeAlbum() );
        musicaDto.setNomeBanda( musicaModel.getNomeBanda() );
        musicaDto.setNomeMusica( musicaModel.getNomeMusica() );

        return musicaDto;
    }

    @Override
    public List<MusicaDto> toListDto(List<MusicaModel> musicaModelList) {
        if ( musicaModelList == null ) {
            return null;
        }

        List<MusicaDto> list = new ArrayList<MusicaDto>( musicaModelList.size() );
        for ( MusicaModel musicaModel : musicaModelList ) {
            list.add( toMusicaDto( musicaModel ) );
        }

        return list;
    }
}
