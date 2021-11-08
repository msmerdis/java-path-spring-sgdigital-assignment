package gr.sgdigital.movies.transfer;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import gr.sgdigital.common.transfer.AbstractCreateDTO;
import gr.sgdigital.movies.domain.Serie;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SerieCreateDTO implements AbstractCreateDTO<Serie> {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Serie must have a name.")
	private String serieName;

	@NotEmpty(message = "Serie must have a description.")
	private String serieDesc;

	private Set<String> genres = new HashSet<String>();

	@JsonProperty
	private Boolean ongoing;

	public void updateEntity(Serie serie) {
		serie.setOngoing(ongoing);
	}
}



