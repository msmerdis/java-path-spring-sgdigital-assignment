package gr.sgdigital.movies.transfer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import gr.sgdigital.common.transfer.AbstractCreateDTO;
import gr.sgdigital.movies.domain.Season;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SeasonCreateDTO implements AbstractCreateDTO<Season> {
	private static final long serialVersionUID = 1L;

	private long seriesId;

	@Positive (message = "Season order must be a positive number")
	private int seasonOrder;

	@NotEmpty (message = "Season must define a season name")
	private String seasonName;

	@NotNull ()
	private String seasonDesc;

	private int releasedYear;

	@Override
	public void updateEntity(Season season) {
		season.setOrder(seasonOrder);
		season.setName(seasonName);
		season.setDesc(seasonDesc);
		season.setReleasedYear(releasedYear);
	}

}



