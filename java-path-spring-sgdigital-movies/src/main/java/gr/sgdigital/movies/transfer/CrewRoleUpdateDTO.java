package gr.sgdigital.movies.transfer;

import javax.validation.constraints.NotEmpty;

import gr.sgdigital.common.transfer.BaseUpdateDTO;
import gr.sgdigital.movies.domain.CrewRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class CrewRoleUpdateDTO extends BaseUpdateDTO<CrewRole, Integer> {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Crew Role must have a name.")
	private String name;

	@Override
	public void updateEntity(CrewRole entity) {
		entity.setName(name);
	}
}



