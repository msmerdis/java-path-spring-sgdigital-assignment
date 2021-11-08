package gr.sgdigital.movies.transfer;

import javax.validation.constraints.NotEmpty;

import gr.sgdigital.common.transfer.AbstractUpdateDTO;
import gr.sgdigital.movies.domain.Genre;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class GenreUpdateDTO extends AbstractUpdateDTO<Genre, Integer> {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Genre must have a name.")
	private String name;

	@Override
	public void updateEntity(Genre genre) {
		genre.setName(name);
	}
}



