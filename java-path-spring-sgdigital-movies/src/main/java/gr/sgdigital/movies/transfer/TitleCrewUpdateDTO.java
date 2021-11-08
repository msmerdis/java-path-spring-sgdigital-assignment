package gr.sgdigital.movies.transfer;

import gr.sgdigital.common.transfer.AbstractUpdateDTO;
import gr.sgdigital.movies.domain.TitleCrew;
import gr.sgdigital.movies.domain.TitleCrewKey;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class TitleCrewUpdateDTO extends AbstractUpdateDTO<TitleCrew, TitleCrewKey> {
	private static final long serialVersionUID = 1L;

	public String role;

	@Override
	public void updateEntity(TitleCrew title) {}
}



