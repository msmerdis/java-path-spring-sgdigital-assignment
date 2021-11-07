package gr.sgdigital.movies.repository;

import gr.sgdigital.common.repository.BaseRepository;
import gr.sgdigital.movies.domain.Serie;
import gr.sgdigital.movies.transfer.SerieCreateDTO;
import gr.sgdigital.movies.transfer.SerieDetailViewDTO;
import gr.sgdigital.movies.transfer.SerieSimpleViewDTO;
import gr.sgdigital.movies.transfer.SerieUpdateDTO;

public interface SerieRepository extends BaseRepository<Long, Serie, SerieCreateDTO, SerieUpdateDTO, SerieSimpleViewDTO, SerieDetailViewDTO> {
}



