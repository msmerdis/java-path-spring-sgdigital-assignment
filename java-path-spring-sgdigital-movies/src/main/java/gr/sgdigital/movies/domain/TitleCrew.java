package gr.sgdigital.movies.domain;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import gr.sgdigital.common.domain.EmbeddableEntity;
import gr.sgdigital.movies.transfer.TitleCrewDetailViewDTO;
import gr.sgdigital.movies.transfer.TitleCrewSimpleViewDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
	name = "ttitlecrew",
	indexes = {@Index(columnList = "titleId, crewId")}
)
public class TitleCrew extends EmbeddableEntity <TitleCrewKey, TitleCrew, TitleCrewSimpleViewDTO, TitleCrewDetailViewDTO> {

	@ManyToOne
	@MapsId("titleId")
	@JoinColumn(name = "titleId")
	Title title;

	@ManyToOne
	@MapsId("crewId")
	@JoinColumn(name = "crewId")
	Crew crew;

	@ManyToOne
	@JoinColumn(name = "crewRoleId")
	CrewRole crewRole;

	public TitleCrew() {
		super(TitleCrewSimpleViewDTO.class, TitleCrewDetailViewDTO.class);

		this.id = new TitleCrewKey ();
	}

	public TitleCrewSimpleViewDTO getTitleSummaryView () {
		TitleCrewSimpleViewDTO dto = new TitleCrewSimpleViewDTO ();

		dto.updateCrewFromEntity(crew);
		dto.updateCrewRoleFromEntity(crewRole);
		dto.setId(id);

		return dto;
	}

	public TitleCrewSimpleViewDTO getCrewSummaryView () {
		TitleCrewSimpleViewDTO dto = new TitleCrewSimpleViewDTO ();

		dto.updateCrewRoleFromEntity(crewRole);
		dto.updateTitleFromEntity(title);
		dto.setId(id);

		return dto;
	}
}



