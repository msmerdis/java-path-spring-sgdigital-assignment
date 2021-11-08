package gr.sgdigital.movies.transfer;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import gr.sgdigital.movies.domain.Season;
import gr.sgdigital.movies.domain.Serie;
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

	@JsonProperty
	private List<SeasonDetailViewDTO> seasons;

	@Override
	public void updateFromEntity(Serie serie) {
		super.updateFromEntity(serie);

		seasons = serie.getSeasons().stream().map(Season::detailView).collect(Collectors.toList());
	}
}



