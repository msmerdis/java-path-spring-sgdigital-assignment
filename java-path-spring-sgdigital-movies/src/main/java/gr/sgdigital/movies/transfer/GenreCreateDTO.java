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
		GenreCreateDTO other = (GenreCreateDTO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GenreCreateDTO [name=" + name + "]";
	}

}



