package gr.sgdigital.movies.transfer;

import java.util.Set;
import java.util.stream.Collectors;

import gr.sgdigital.common.transfer.BaseResponseDTO;
import gr.sgdigital.movies.domain.Genre;
import gr.sgdigital.movies.domain.Movie;
import gr.sgdigital.movies.domain.Title;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class MovieSimpleViewDTO extends BaseResponseDTO<Movie> {
	private static final long serialVersionUID = 1L;

	private Long   titleId;
	private Long   movieId;
	private String movieName;
	private String movieDesc;
	private int    releasedYear;
	private Set<String> genres;

	@Override
	public void updateFromEntity(Movie movie) {
		Title title = movie.getTitle();

		titleId      = title.getId();
		movieId      = movie.getId();
		movieName    = title.getTitle();
		movieDesc    = title.getDescription();
		genres       = title.getGenres().stream().map(Genre::getName).collect(Collectors.toSet());
		releasedYear = movie.getReleasedYear();
	}
}



