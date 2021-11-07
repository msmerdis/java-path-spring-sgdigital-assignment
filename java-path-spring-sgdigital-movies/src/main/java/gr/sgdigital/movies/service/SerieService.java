package gr.sgdigital.movies.service;

import gr.sgdigital.common.service.BaseService;
import gr.sgdigital.movies.domain.Serie;
import gr.sgdigital.movies.transfer.SerieCreateDTO;
import gr.sgdigital.movies.transfer.SerieDetailViewDTO;
import gr.sgdigital.movies.transfer.SerieSimpleViewDTO;
import gr.sgdigital.movies.transfer.SerieUpdateDTO;

public interface SerieService extends BaseService<Long, Serie, SerieCreateDTO, SerieUpdateDTO, SerieSimpleViewDTO, SerieDetailViewDTO> {
}



