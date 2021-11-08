package gr.sgdigital.movies.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
class TitleCrewKey implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "titleId")
    Long titleId;

	@Column(name = "crewId")
    Long crewId;
}



