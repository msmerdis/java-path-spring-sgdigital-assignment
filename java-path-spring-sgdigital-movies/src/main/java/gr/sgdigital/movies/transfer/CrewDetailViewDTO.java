package gr.sgdigital.movies.transfer;

import java.util.List;
import java.util.stream.Collectors;

import gr.sgdigital.movies.domain.Crew;
import gr.sgdigital.movies.domain.TitleCrew;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class CrewDetailViewDTO extends CrewSimpleViewDTO {
	private static final long serialVersionUID = 1L;

	private List<TitleCrewSimpleViewDTO> titles;

	@Override
	public void updateFromEntity(Crew crew) {
		super.updateFromEntity(crew);

		titles = crew.getTitleCrew().stream().map(TitleCrew::getCrewSummaryView).collect(Collectors.toList());
	}
}



