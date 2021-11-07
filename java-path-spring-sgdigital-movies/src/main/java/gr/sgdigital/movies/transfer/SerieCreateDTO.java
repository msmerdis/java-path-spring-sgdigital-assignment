package gr.sgdigital.movies.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;

import gr.sgdigital.common.transfer.BaseCreateDTO;
import gr.sgdigital.movies.domain.Serie;
import gr.sgdigital.movies.domain.Title;

public class SerieCreateDTO implements BaseCreateDTO<Serie> {
	private static final long serialVersionUID = 1L;

	@JsonProperty("title")
	TitleCreateDTO titleDTO;

	private boolean ongoing;

	public void updateEntity(Serie serie) {
		Title title = new Title ();

		titleDTO.updateEntity(title);

		serie.setOngoing(ongoing);
		serie.setTitle(title);
	}

}



