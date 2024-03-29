package gr.sgdigital.movies.transfer;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import gr.sgdigital.movies.domain.Genre;
import gr.sgdigital.movies.domain.Season;
import gr.sgdigital.movies.domain.Serie;
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
@JsonInclude(Include.NON_NULL)
public class SeasonDetailViewDTO extends SeasonSimpleViewDTO {
	private static final long serialVersionUID = 1L;

	private Long   serieId;
	private String serieName;
	private String serieDesc;

	private Set<String> serieGenre;
	private Boolean ongoing;

	private List<TitleCrewSimpleViewDTO> crewList;

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
		ongoing    = serie.getOngoing();
		crewList   = title.getTitleCrew().stream().map(TitleCrew::getTitleSummaryView).collect(Collectors.toList());
	}

	public void updateWithoutSerieInfo (Season season) {
		super.updateFromEntity(season);

		episodes = season.getEpisodes().stream().map(t -> {
			try {
				return t.simpleView();
			} catch (Exception e) {
				return null;
			}
		}).collect(Collectors.toList());
	}
}



