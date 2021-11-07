package gr.sgdigital.movies.transfer;

import java.util.Set;

import gr.sgdigital.common.transfer.BaseResponseDTO;
import gr.sgdigital.movies.domain.Episode;
import gr.sgdigital.movies.domain.Genre;
import gr.sgdigital.movies.domain.Season;
import gr.sgdigital.movies.domain.Serie;
import gr.sgdigital.movies.domain.Title;

public class SerieSimpleViewDTO extends BaseResponseDTO<Serie> {
	private static final long serialVersionUID = 1L;

	@Override
	public void updateFromEntity(Serie serie) {
	}

}



