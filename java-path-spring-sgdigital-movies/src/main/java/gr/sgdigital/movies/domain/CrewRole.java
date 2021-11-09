package gr.sgdigital.movies.domain;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
	indexes = {
		@Index(columnList =  "id" , unique = true),
		@Index(columnList = "name", unique = true)
	}
)
@SequenceGenerator(name = "idGenerator", sequenceName = "GENRE_SEQ", initialValue = 1, allocationSize = 1)
public class CrewRole extends BaseEntity <Integer, CrewRole, CrewRoleSimpleViewDTO, CrewRoleDetailViewDTO> {

	@Column(length = 32, nullable = false)
	private String name;

	@Fetch(FetchMode.SELECT)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "crewRole")
	private List<TitleCrew> titleCrew = new LinkedList<TitleCrew>();

	public CrewRole() {
		super(CrewRoleSimpleViewDTO.class, CrewRoleDetailViewDTO.class);
	}
}



