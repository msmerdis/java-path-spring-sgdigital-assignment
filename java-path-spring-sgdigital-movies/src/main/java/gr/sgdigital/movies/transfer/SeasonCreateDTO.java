package gr.sgdigital.movies.transfer;

import gr.sgdigital.common.transfer.BaseCreateDTO;
import gr.sgdigital.movies.domain.Season;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SeasonCreateDTO implements BaseCreateDTO<Season> {
	private static final long serialVersionUID = 1L;

	@Override
	public void updateEntity(Season season) {
	}

}



