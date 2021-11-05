package gr.sgdigital.movies.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import gr.sgdigital.common.domain.BaseEntity;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "tseason", indexes = {@Index(columnList = "id")})
@SequenceGenerator(name = "idGenerator", sequenceName = "SEASON_SEQ", initialValue = 300000, allocationSize = 1)
public class Season extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@JoinColumn(name="serieId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Serie serie;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Episode.class, mappedBy = "season")
	private Set<Episode> episodes = new HashSet<Episode>();

	@Column(length = 512, nullable = true)
	private String desc;

	@Column
	private int releasedYear;

	public Season () {
		super ();
	}

	public Season (Serie serie) {
		super ();
		this.serie = serie;
	}

	public Serie getSerie() {
		return serie;
	}

	public Set<Episode> getEpisodes() {
		return episodes;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getReleasedYear() {
		return releasedYear;
	}

	public void setReleasedYear(int releasedYear) {
		this.releasedYear = releasedYear;
	}
}
