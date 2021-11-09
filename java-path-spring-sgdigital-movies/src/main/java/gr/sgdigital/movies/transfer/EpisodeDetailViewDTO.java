package gr.sgdigital.movies.transfer;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import gr.sgdigital.movies.domain.Episode;
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
public class EpisodeDetailViewDTO extends EpisodeSimpleViewDTO {
	private static final long serialVersionUID = 1L;

	private Long   seasonId;
	private String seasonName;
	private String seasonDesc;
	private int    released;

	private Long   serieId;
	private String serieName;
	private String serieDesc;

	private Set<String> serieGenre;
	private Boolean ongoing;

	private List<TitleCrewSimpleViewDTO> crewList;

	@Override
	public void updateFromEntity(Episode episode) {
		super.updateFromEntity(episode);

		Season season = episode.getSeason();

		seasonId   = season.getId();
		seasonName = season.getName();
		seasonDesc = season.getDesc();
		released   = season.getReleasedYear();

		Serie serie = season.getSerie();
		Title title = serie.getTitle();

		serieId    = serie.getId();
		serieName  = title.getTitle();
		serieDesc  = title.getDescription();
		serieGenre = title.getGenres().stream().map(Genre::getName).collect(Collectors.toSet());
		ongoing    = serie.getOngoing();
		crewList   = title.getTitleCrew().stream().map(TitleCrew::getTitleSummaryView).collect(Collectors.toList());
	}
}



