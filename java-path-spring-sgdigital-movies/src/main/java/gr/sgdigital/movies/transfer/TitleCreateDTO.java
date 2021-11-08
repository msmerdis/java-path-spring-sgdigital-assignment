package gr.sgdigital.movies.transfer;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import gr.sgdigital.common.transfer.AbstractCreateDTO;
import gr.sgdigital.movies.domain.Title;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TitleCreateDTO implements AbstractCreateDTO<Title> {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Title must have a name.")
	private String titleName;

	@NotEmpty(message = "Title must have a description.")
	private String titleDesc;

	private Set<String> genres = new HashSet<String>();

	@Override
	public void updateEntity(Title title) {
		title.setTitle(titleName);
		title.setDescription(titleDesc);
	}
}



