package gr.sgdigital.movies.transfer;

import javax.validation.constraints.NotEmpty;

import gr.sgdigital.common.transfer.BaseCreateDTO;
import gr.sgdigital.movies.domain.Genre;

public class GenreCreateDTO implements BaseCreateDTO<Genre> {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Genre must have a name.")
	private String name;

	@Override
	public void updateEntity(Genre genre) {
		genre.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}



