package gr.sgdigital.movies.domain;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import gr.sgdigital.common.domain.BaseEntity;
import gr.sgdigital.movies.transfer.TitleDetailViewDTO;
import gr.sgdigital.movies.transfer.TitleSimpleViewDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ttitle", indexes = {@Index(columnList = "id")})
@SequenceGenerator(name = "idGenerator", sequenceName = "TITLE_SEQ", initialValue = 100000, allocationSize = 1)
@Indexed
public class Title extends BaseEntity <Long, Title, TitleSimpleViewDTO, TitleDetailViewDTO> {

	@Field
	@NotNull(message = "Movie must have a title.")
	@Column(length = 128, nullable = false)
	private String title;

	@Field
	@Column(length = 1024, nullable = true)
	private String description;

	@Fetch(FetchMode.SELECT)
	@JoinTable(
		name = "ttitlegenres",
		joinColumns = @JoinColumn(name="titleId"),
		inverseJoinColumns = @JoinColumn(name="genreId")
	)
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Genre> genres = new HashSet<Genre> ();

	@Fetch(FetchMode.SELECT)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "title")
	private List<TitleCrew> titleCrew = new LinkedList<TitleCrew>();

	@Enumerated(EnumType.ORDINAL)
	private TitleType type;

	public Title () {
		super (TitleSimpleViewDTO.class, TitleDetailViewDTO.class);
	}
}



