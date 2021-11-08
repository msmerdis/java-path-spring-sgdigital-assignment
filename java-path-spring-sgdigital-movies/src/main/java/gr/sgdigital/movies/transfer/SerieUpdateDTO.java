package gr.sgdigital.movies.transfer;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import gr.sgdigital.common.transfer.BaseUpdateDTO;
import gr.sgdigital.movies.domain.Serie;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class SerieUpdateDTO extends BaseUpdateDTO<Serie, Long> {
	private static final long serialVersionUID = 1L;

	private Long   serieId;

	@NotEmpty(message = "Serie must have a name.")
	private String serieName;

	@NotEmpty(message = "Serie must have a description.")
	private String serieDesc;

	private Set<String> genres = new HashSet<String>();

	@JsonProperty
	private Boolean ongoing;

	@Override
	public void updateEntity (Serie serie) {
		serie.setOngoing(ongoing);
	}
}



