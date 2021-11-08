package gr.sgdigital.movies.transfer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import gr.sgdigital.common.transfer.EmbeddableResponseDTO;
import gr.sgdigital.movies.domain.Crew;
import gr.sgdigital.movies.domain.CrewRole;
import gr.sgdigital.movies.domain.Title;
import gr.sgdigital.movies.domain.TitleCrew;
import gr.sgdigital.movies.domain.TitleCrewKey;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class TitleCrewSimpleViewDTO  extends EmbeddableResponseDTO<TitleCrew> {
	private static final long serialVersionUID = 1L;

	private TitleCrewKey id;

	@JsonProperty("crew")
	private CrewSimpleViewDTO crewDto;

	private Long titleId;
	private String titleName;
	private String titleType;

	private Integer crewRoleId;
	private String crewRoleName;

	@Override
	public void updateFromEntity(TitleCrew titleCrew) {
		id = titleCrew.getId();

		updateCrewFromEntity (titleCrew.getCrew());
		updateTitleFromEntity (titleCrew.getTitle());
		updateCrewRoleFromEntity (titleCrew.getCrewRole());
	}

	public void updateCrewFromEntity (Crew crew) {
		try {
			crewDto = crew.simpleView();
		} catch (Exception e) {}
	}

	public void updateTitleFromEntity (Title title) {
		titleId    = title.getId();
		titleName  = title.getTitle();
		titleType  = title.getType().getName();
	}

	public void updateCrewRoleFromEntity (CrewRole crewRole) {
		crewRoleId   = crewRole.getId();
		crewRoleName = crewRole.getName();
	}
}



