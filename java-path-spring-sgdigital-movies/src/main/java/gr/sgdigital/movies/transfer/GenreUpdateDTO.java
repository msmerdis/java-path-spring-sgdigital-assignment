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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenreUpdateDTO other = (GenreUpdateDTO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GenreUpdateDTO [name=" + name + "]";
	}

}



