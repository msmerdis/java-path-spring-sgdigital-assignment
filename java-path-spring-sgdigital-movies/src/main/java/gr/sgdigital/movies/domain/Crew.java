package gr.sgdigital.movies.domain;

import java.util.Date;
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
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

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
		@Index(columnList = "id", unique = true),
	}
)
@SequenceGenerator(name = "idGenerator", sequenceName = "CREW_SEQ", initialValue = 1, allocationSize = 1)
@Indexed
public class Crew extends BaseEntity <Long, Crew, CrewSimpleViewDTO, CrewDetailViewDTO> {

	@Field
	@Column(length = 128, nullable = false)
	private String firstName;

	@Field
	@Column(length = 128, nullable = false)
	private String lastName;

	@Field
	@Column(length = 128, nullable = true)
	private String middleName;

	@Column(nullable = true)
	private Date birthDate;

	@Fetch(FetchMode.SELECT)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "crew")
	private List<TitleCrew> titleCrew = new LinkedList<TitleCrew>();

	public Crew() {
		super(CrewSimpleViewDTO.class, CrewDetailViewDTO.class);
	}
}



