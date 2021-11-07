package gr.sgdigital.movies.transfer;

import gr.sgdigital.common.transfer.BaseResponseDTO;
import gr.sgdigital.movies.domain.Genre;

public class GenreSimpleViewDTO extends BaseResponseDTO<Genre> {
	private static final long serialVersionUID = 1L;

	private int    genreId;
	private String genreName;

	@Override
	public void updateFromEntity(Genre genre) {
		genreId   = genre.getId();
		genreName = genre.getName();
	}

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

}



