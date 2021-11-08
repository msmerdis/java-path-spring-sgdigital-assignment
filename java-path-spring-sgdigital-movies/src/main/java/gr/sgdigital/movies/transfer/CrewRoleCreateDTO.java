package gr.sgdigital.movies.transfer;

import javax.validation.constraints.NotEmpty;

import gr.sgdigital.common.transfer.BaseCreateDTO;
import gr.sgdigital.movies.domain.CrewRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CrewRoleCreateDTO implements BaseCreateDTO<CrewRole> {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "CrewRole must have a name.")
	private String name;

	@Override
	public void updateEntity(CrewRole genre) {
		genre.setName(name);
	}
}



