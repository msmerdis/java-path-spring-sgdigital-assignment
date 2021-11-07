package gr.sgdigital.movies.transfer;

import gr.sgdigital.common.transfer.BaseResponseDTO;
import gr.sgdigital.movies.domain.Season;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class SeasonSimpleViewDTO extends BaseResponseDTO<Season> {
	private static final long serialVersionUID = 1L;

	@Override
	public void updateFromEntity(Season season) {
	}

}



