package gr.sgdigital.movies.transfer;

import java.util.Set;
import java.util.stream.Collectors;

import gr.sgdigital.common.transfer.BaseResponseDTO;
import gr.sgdigital.movies.domain.Genre;
import gr.sgdigital.movies.domain.Movie;
import gr.sgdigital.movies.domain.Title;

public class MovieSimpleViewDTO extends BaseResponseDTO<Movie> {
	private static final long serialVersionUID = 1L;

	private Long   movieId;
	private String movieName;
	private String movieDesc;
	private int    releasedYear;
	private Set<String> genres;

	@Override
	public void updateFromEntity(Movie movie) {
		Title title = movie.getTitle();

		movieId      = movie.getId();
		movieName    = title.getTitle();
		movieDesc    = title.getDescription();
		genres       = title.getGenres().stream().map(Genre::getName).collect(Collectors.toSet());
		releasedYear = movie.getReleasedYear();
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieDesc() {
		return movieDesc;
	}

	public void setMovieDesc(String movieDesc) {
		this.movieDesc = movieDesc;
	}

	public int getReleasedYear() {
		return releasedYear;
	}

	public void setReleasedYear(int releasedYear) {
		this.releasedYear = releasedYear;
	}

	public Set<String> getGenres() {
		return genres;
	}

	public void setGenres(Set<String> genres) {
		this.genres = genres;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genres == null) ? 0 : genres.hashCode());
		result = prime * result + ((movieDesc == null) ? 0 : movieDesc.hashCode());
		result = prime * result + ((movieId == null) ? 0 : movieId.hashCode());
		result = prime * result + ((movieName == null) ? 0 : movieName.hashCode());
		result = prime * result + releasedYear;
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
		MovieSimpleViewDTO other = (MovieSimpleViewDTO) obj;
		if (genres == null) {
			if (other.genres != null)
				return false;
		} else if (!genres.equals(other.genres))
			return false;
		if (movieDesc == null) {
			if (other.movieDesc != null)
				return false;
		} else if (!movieDesc.equals(other.movieDesc))
			return false;
		if (movieId == null) {
			if (other.movieId != null)
				return false;
		} else if (!movieId.equals(other.movieId))
			return false;
		if (movieName == null) {
			if (other.movieName != null)
				return false;
		} else if (!movieName.equals(other.movieName))
			return false;
		if (releasedYear != other.releasedYear)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MovieSimpleViewDTO [movieId=" + movieId + ", movieName=" + movieName + ", movieDesc=" + movieDesc
				+ ", releasedYear=" + releasedYear + ", genres=" + genres + "]";
	}
}



