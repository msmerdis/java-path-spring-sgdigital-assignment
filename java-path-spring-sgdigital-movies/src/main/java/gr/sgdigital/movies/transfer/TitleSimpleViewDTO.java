package gr.sgdigital.movies.transfer;

import java.util.Set;
import java.util.stream.Collectors;

import gr.sgdigital.common.transfer.BaseResponseDTO;
import gr.sgdigital.movies.domain.Genre;
import gr.sgdigital.movies.domain.Title;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class TitleSimpleViewDTO extends BaseResponseDTO<Title> {
	private static final long serialVersionUID = 1L;

	private Long   titleId;
	private String titleName;
	private String titleDesc;
	private Set<String> genres;

	@Override
	public void updateFromEntity(Title title) {
		titleId   = title.getId();
		titleName = title.getTitle();
		titleDesc = title.getDescription();
		genres    = title.getGenres().stream().map(Genre::getName).collect(Collectors.toSet());
	}
}



