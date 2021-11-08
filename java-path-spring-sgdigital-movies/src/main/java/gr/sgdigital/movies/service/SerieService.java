package gr.sgdigital.movies.service;

import gr.sgdigital.common.service.AbstractService;
import gr.sgdigital.common.transfer.status.NotFoundException;
import gr.sgdigital.movies.domain.Season;
import gr.sgdigital.movies.domain.Serie;
import gr.sgdigital.movies.transfer.SerieCreateDTO;
import gr.sgdigital.movies.transfer.SerieDetailViewDTO;
import gr.sgdigital.movies.transfer.SerieSimpleViewDTO;
import gr.sgdigital.movies.transfer.SerieUpdateDTO;

public interface SerieService extends AbstractService<Long, Serie, SerieCreateDTO, SerieUpdateDTO, SerieSimpleViewDTO, SerieDetailViewDTO> {

	void updateSeasonWithSerie(Season season, long seriesId) throws NotFoundException;
}



