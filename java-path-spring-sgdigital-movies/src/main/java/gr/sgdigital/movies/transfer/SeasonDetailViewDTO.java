package gr.sgdigital.movies.transfer;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import gr.sgdigital.movies.domain.Episode;
import gr.sgdigital.movies.domain.Season;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class SeasonDetailViewDTO extends SeasonSimpleViewDTO {
	private static final long serialVersionUID = 1L;

	@JsonProperty
	private List<EpisodeDetailViewDTO> episodes;

	@Override
	public void updateFromEntity(Season season) {
		super.updateFromEntity(season);

		episodes = season.getEpisodes().stream().map(Episode::detailView).collect(Collectors.toList());
	}
}



