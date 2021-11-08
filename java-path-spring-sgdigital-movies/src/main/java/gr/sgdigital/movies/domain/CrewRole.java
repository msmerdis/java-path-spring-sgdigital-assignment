package gr.sgdigital.movies.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import gr.sgdigital.common.domain.BaseEntity;
import gr.sgdigital.movies.transfer.CrewRoleDetailViewDTO;
import gr.sgdigital.movies.transfer.CrewRoleSimpleViewDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
	name = "tcrewrole",
	indexes = {@Index(columnList = "id"), @Index(columnList = "name")},
	uniqueConstraints = {@UniqueConstraint(columnNames = "name")}
)
@SequenceGenerator(name = "idGenerator", sequenceName = "GENRE_SEQ", initialValue = 1, allocationSize = 1)
public class CrewRole extends BaseEntity <Integer, CrewRole, CrewRoleSimpleViewDTO, CrewRoleDetailViewDTO> {

	@Column(length = 32, nullable = false)
	private String name;

	public CrewRole() {
		super(CrewRoleSimpleViewDTO.class, CrewRoleDetailViewDTO.class);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}



