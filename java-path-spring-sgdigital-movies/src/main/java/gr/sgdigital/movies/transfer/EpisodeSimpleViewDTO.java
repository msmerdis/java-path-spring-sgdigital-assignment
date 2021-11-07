package gr.sgdigital.movies.transfer;

import java.util.Set;

import gr.sgdigital.common.transfer.BaseResponseDTO;
import gr.sgdigital.movies.domain.Episode;
import gr.sgdigital.movies.domain.Genre;
import gr.sgdigital.movies.domain.Season;
import gr.sgdigital.movies.domain.Serie;
import gr.sgdigital.movies.domain.Title;

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

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public Long getEpisodeId() {
		return episodeId;
	}

	public void setEpisodeId(Long episodeId) {
		this.episodeId = episodeId;
	}

	public String getEpisodeName() {
		return episodeName;
	}

	public void setEpisodeName(String episodeName) {
		this.episodeName = episodeName;
	}

	public String getEpisodeDesc() {
		return episodeDesc;
	}

	public void setEpisodeDesc(String episodeDesc) {
		this.episodeDesc = episodeDesc;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Long getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(Long seasonId) {
		this.seasonId = seasonId;
	}

	public String getSeasonName() {
		return seasonName;
	}

	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}

	public String getSeasonDesc() {
		return seasonDesc;
	}

	public void setSeasonDesc(String seasonDesc) {
		this.seasonDesc = seasonDesc;
	}

	public int getReleased() {
		return released;
	}

	public void setReleased(int released) {
		this.released = released;
	}

	public Long getSerieId() {
		return serieId;
	}

	public void setSerieId(Long serieId) {
		this.serieId = serieId;
	}

	public String getSerieName() {
		return serieName;
	}

	public void setSerieName(String serieName) {
		this.serieName = serieName;
	}

	public String getSerieDesc() {
		return serieDesc;
	}

	public void setSerieDesc(String serieDesc) {
		this.serieDesc = serieDesc;
	}

	public Set<Genre> getSerieGenre() {
		return serieGenre;
	}

	public void setSerieGenre(Set<Genre> serieGenre) {
		this.serieGenre = serieGenre;
	}

}



