package gr.sgdigital.movies.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AssociationOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "tserie", indexes = {@Index(columnList = "id")})
@SequenceGenerator(name = "idGenerator", sequenceName = "SERIE_SEQ", initialValue = 200000, allocationSize = 1)
@AssociationOverride(
	name = "genres",
	joinTable = @JoinTable(
		name = "tseriegenres",
		joinColumns = @JoinColumn(name="serieId"),
		inverseJoinColumns = @JoinColumn(name="genreId")
	)
)
public class Serie extends Title {
	private static final long serialVersionUID = 1L;

	@Column
	private boolean ongoing;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Season.class, mappedBy = "serie")
	private Set<Season> seasons = new HashSet<Season>();

	public boolean isOngoing() {
		return ongoing;
	}

	public void setOngoing(boolean ongoing) {
		this.ongoing = ongoing;
	}

	public Set<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(Set<Season> seasons) {
		this.seasons = seasons;
	}
}



