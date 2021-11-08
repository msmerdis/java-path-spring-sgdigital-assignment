package gr.sgdigital.movies.transfer;

import java.util.List;
import java.util.stream.Collectors;

import gr.sgdigital.movies.domain.Movie;
import gr.sgdigital.movies.domain.TitleCrew;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class MovieDetailViewDTO extends MovieSimpleViewDTO {
	private static final long serialVersionUID = 1L;

	private List<TitleCrewSimpleViewDTO> crewList;

	@Override
	public void updateFromEntity(Movie movie) {
		super.updateFromEntity(movie);

		crewList   = movie.getTitle().getTitleCrew().stream().map(TitleCrew::getTitleSummaryView).collect(Collectors.toList());
	}
}



