package gr.sgdigital.movies.transfer;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import gr.sgdigital.common.transfer.BaseUpdateDTO;
import gr.sgdigital.movies.domain.Movie;

public class MovieUpdateDTO extends BaseUpdateDTO<Movie, Long> {
	private static final long serialVersionUID = 1L;

	private Long   movieId;

	@NotEmpty(message = "Movie must have a name.")
	private String movieName;

	@NotEmpty(message = "Movie must have a description.")
	private String movieDesc;

	private Set<String> genres = new HashSet<String>();

	@Positive(message = "Movie's released year must be a positive number.")
	private int releasedYear;

	public void updateEntity(Movie movie) {
		movie.setReleasedYear(releasedYear);
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

	public Set<String> getGenres() {
		return genres;
	}

	public void setGenres(Set<String> genres) {
		this.genres = genres;
	}

	public int getReleasedYear() {
		return releasedYear;
	}

	public void setReleasedYear(int releasedYear) {
		this.releasedYear = releasedYear;
	}

	@Override
	public String toString() {
		return "MovieUpdateDTO [movieId=" + movieId + ", movieName=" + movieName + ", movieDesc=" + movieDesc
				+ ", genres=" + genres + ", releasedYear=" + releasedYear + "]";
	}

}



