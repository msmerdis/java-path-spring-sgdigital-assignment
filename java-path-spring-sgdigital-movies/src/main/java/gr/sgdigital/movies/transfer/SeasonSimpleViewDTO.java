package gr.sgdigital.movies.transfer;

import gr.sgdigital.common.transfer.BaseResponseDTO;
import gr.sgdigital.movies.domain.Season;

public class SeasonSimpleViewDTO extends BaseResponseDTO<Season> {
	private static final long serialVersionUID = 1L;

	@Override
	public void updateFromEntity(Season season) {
	}

}



