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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + genreId;
		result = prime * result + ((genreName == null) ? 0 : genreName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenreSimpleViewDTO other = (GenreSimpleViewDTO) obj;
		if (genreId != other.genreId)
			return false;
		if (genreName == null) {
			if (other.genreName != null)
				return false;
		} else if (!genreName.equals(other.genreName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GenreSimpleViewDTO [genreId=" + genreId + ", genreName=" + genreName + "]";
	}

}



