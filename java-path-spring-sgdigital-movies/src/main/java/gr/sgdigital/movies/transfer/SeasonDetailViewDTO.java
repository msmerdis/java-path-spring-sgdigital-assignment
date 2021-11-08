package gr.sgdigital.movies.transfer;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import gr.sgdigital.movies.domain.Episode;
import gr.sgdigital.movies.domain.Genre;
import gr.sgdigital.movies.domain.Season;
import gr.sgdigital.movies.domain.Serie;
import gr.sgdigital.movies.domain.Title;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class SeasonDetailViewDTO extends SeasonSimpleViewDTO {
	private static final long serialVersionUID = 1L;

	private Long   serieId;
	private String serieName;
	private String serieDesc;

	private Set<String> serieGenre;

	@JsonProperty
	private List<EpisodeSimpleViewDTO> episodes;

	@Override
	public void updateFromEntity(Season season) {
		updateWithoutSerieInfo (season);

		Serie serie = season.getSerie();
		Title title = serie.getTitle();

		serieId    = serie.getId();
		serieName  = title.getTitle();
		serieDesc  = title.getDescription();
		serieGenre = title.getGenres().stream().map(Genre::getName).collect(Collectors.toSet());
	}

	public void updateWithoutSerieInfo (Season season) {
		super.updateFromEntity(season);

		episodes = season.getEpisodes().stream().map(Episode::simpleView).collect(Collectors.toList());
	}
}



