package gr.sgdigital.movies.transfer;

import gr.sgdigital.common.transfer.BaseResponseDTO;
import gr.sgdigital.movies.domain.CrewRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class CrewRoleSimpleViewDTO extends BaseResponseDTO<CrewRole> {
	private static final long serialVersionUID = 1L;

	private int    crewRoleId;
	private String crewRoleName;

	@Override
	public void updateFromEntity(CrewRole crewRole) {
		crewRoleId   = crewRole.getId();
		crewRoleName = crewRole.getName();
	}
}



