package gr.sgdigital.movies.transfer;

import gr.sgdigital.common.transfer.BaseResponseDTO;
import gr.sgdigital.movies.domain.Movie;

public class MovieSimpleViewDTO extends BaseResponseDTO<Movie> {
	private static final long serialVersionUID = 1L;

	@Override
	public void updateFromEntity(Movie movie) {
	}

}



