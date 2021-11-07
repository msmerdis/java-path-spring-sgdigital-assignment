package gr.sgdigital.movies.transfer;

import java.util.Set;

import gr.sgdigital.common.transfer.BaseResponseDTO;
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
@EqualsAndHashCode(callSuper = false)
public class EpisodeSimpleViewDTO extends BaseResponseDTO<Episode> {
	private static final long serialVersionUID = 1L;

	private int    order;
	private Long   episodeId;
	private String episodeName;
	private String episodeDesc;
	private int    duration;

	private Long   seasonId;
	private String seasonName;
	private String seasonDesc;
	private int    released;

	private Long   serieId;
	private String serieName;
	private String serieDesc;

	private Set<Genre> serieGenre;

	@Override
	public void updateFromEntity(Episode episode) {
		order       = episode.getOrder();
		episodeId   = episode.getId();
		episodeName = episode.getName();
		episodeDesc = episode.getDesc();
		duration    = episode.getDuration();

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
		serieGenre = title.getGenres();
	}
}



