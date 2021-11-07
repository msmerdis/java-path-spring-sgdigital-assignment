package gr.sgdigital.movies.transfer;

import gr.sgdigital.common.transfer.BaseResponseDTO;
import gr.sgdigital.movies.domain.Genre;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class GenreSimpleViewDTO extends BaseResponseDTO<Genre> {
	private static final long serialVersionUID = 1L;

	private int    genreId;
	private String genreName;

	@Override
	public void updateFromEntity(Genre genre) {
		genreId   = genre.getId();
		genreName = genre.getName();
	}
}



