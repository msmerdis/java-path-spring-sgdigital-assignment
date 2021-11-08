package gr.sgdigital.movies.transfer;

import java.util.List;
import java.util.stream.Collectors;

import gr.sgdigital.movies.domain.Season;
import gr.sgdigital.movies.domain.Serie;
import gr.sgdigital.movies.domain.TitleCrew;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class SerieDetailViewDTO extends SerieSimpleViewDTO {
	private static final long serialVersionUID = 1L;

	private List<SeasonDetailViewDTO> seasons;
	private List<TitleCrewSimpleViewDTO> crewList;

	@Override
	public void updateFromEntity(Serie serie) {
		super.updateFromEntity(serie);

		seasons  = serie.getSeasons().stream().map(Season::seriesView).collect(Collectors.toList());
		crewList = serie.getTitle().getTitleCrew().stream().map(TitleCrew::getTitleSummaryView).collect(Collectors.toList());
	}
}



