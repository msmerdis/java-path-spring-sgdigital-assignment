package gr.sgdigital.movies.transfer;

import javax.validation.constraints.NotEmpty;

import gr.sgdigital.common.transfer.BaseUpdateDTO;
import gr.sgdigital.movies.domain.Genre;

public class GenreUpdateDTO extends BaseUpdateDTO<Genre, Integer> {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Genre must have a name.")
	private String name;

	@Override
	public void updateEntity(Genre entity) {
		entity.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}



