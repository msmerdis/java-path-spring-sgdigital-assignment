package gr.sgdigital.movies.transfer;

import java.util.List;
import java.util.stream.Collectors;

import gr.sgdigital.movies.domain.Title;
import gr.sgdigital.movies.domain.TitleCrew;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class TitleDetailViewDTO extends TitleSimpleViewDTO {
	private static final long serialVersionUID = 1L;

	private List<TitleCrewSimpleViewDTO> crewList;

	@Override
	public void updateFromEntity(Title title) {
		super.updateFromEntity(title);

		crewList = title.getTitleCrew().stream().map(TitleCrew::getTitleSummaryView).collect(Collectors.toList());
	}
}



