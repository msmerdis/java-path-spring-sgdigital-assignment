package gr.sgdigital.movies.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import gr.sgdigital.common.domain.BaseEntity;
import gr.sgdigital.movies.transfer.CrewDetailViewDTO;
import gr.sgdigital.movies.transfer.CrewSimpleViewDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
	name = "tcrew",
	indexes = {
		@Index(columnList = "id"),
	},
	uniqueConstraints = {
		@UniqueConstraint (columnNames = {"firstName", "lastName", "birthDate"})
	}
)
@SequenceGenerator(name = "idGenerator", sequenceName = "CREW_SEQ", initialValue = 1, allocationSize = 1)
public class Crew extends BaseEntity <Long, Crew, CrewSimpleViewDTO, CrewDetailViewDTO> {

	@Column(length = 128, nullable = false)
	private String firstName;

	@Column(length = 128, nullable = false)
	private String lastName;

	@Column(length = 128, nullable = true)
	private String middleName;

	@Column(nullable = true)
	private Date birthDate;

	public Crew() {
		super(CrewSimpleViewDTO.class, CrewDetailViewDTO.class);
	}
}



