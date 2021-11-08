package gr.sgdigital.movies.transfer;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import gr.sgdigital.common.transfer.AbstractCreateDTO;
import gr.sgdigital.movies.domain.Movie;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MovieCreateDTO implements AbstractCreateDTO<Movie> {
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
}



