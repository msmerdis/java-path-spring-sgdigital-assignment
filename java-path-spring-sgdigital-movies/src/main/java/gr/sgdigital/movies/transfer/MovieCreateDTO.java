package gr.sgdigital.movies.transfer;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;

import gr.sgdigital.common.transfer.BaseCreateDTO;
import gr.sgdigital.movies.domain.Movie;
import gr.sgdigital.movies.domain.Title;

public class MovieCreateDTO implements BaseCreateDTO<Movie> {
	private static final long serialVersionUID = 1L;

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genres == null) ? 0 : genres.hashCode());
		result = prime * result + ((movieDesc == null) ? 0 : movieDesc.hashCode());
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
		MovieCreateDTO other = (MovieCreateDTO) obj;
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
		return "MovieCreateDTO [movieName=" + movieName + ", movieDesc=" + movieDesc + ", genres=" + genres
				+ ", releasedYear=" + releasedYear + "]";
	}

}



