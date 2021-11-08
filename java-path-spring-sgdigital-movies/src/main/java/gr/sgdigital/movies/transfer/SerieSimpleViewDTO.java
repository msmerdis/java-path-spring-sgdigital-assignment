package gr.sgdigital.movies.transfer;

import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import gr.sgdigital.common.transfer.BaseResponseDTO;
import gr.sgdigital.movies.domain.Genre;
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
public class SerieSimpleViewDTO extends BaseResponseDTO<Serie> {
	private static final long serialVersionUID = 1L;

	private Long    titleId;
	private Long    serieId;
	private String  serieName;
	private String  serieDesc;

	@JsonProperty
	private Boolean ongoing;
	private Set<String> genres;

	@Override
	public void updateFromEntity(Serie serie) {
		Title title = serie.getTitle();

		titleId   = title.getId();
		serieId   = serie.getId();
		serieName = title.getTitle();
		serieDesc = title.getDescription();
		genres    = title.getGenres().stream().map(Genre::getName).collect(Collectors.toSet());
		ongoing   = serie.getOngoing();
	}
}



