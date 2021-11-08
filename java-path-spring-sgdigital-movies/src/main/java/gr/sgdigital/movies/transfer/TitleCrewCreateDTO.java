package gr.sgdigital.movies.transfer;

import gr.sgdigital.common.transfer.AbstractCreateDTO;
import gr.sgdigital.movies.domain.TitleCrew;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TitleCrewCreateDTO implements AbstractCreateDTO<TitleCrew> {
	private static final long serialVersionUID = 1L;

	public Long titleId;
	public Long crewId;
	public String role;

	@Override
	public void updateEntity(TitleCrew title) {}
}



