package gr.sgdigital.movies.transfer;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import gr.sgdigital.common.transfer.BaseResponseDTO;
import gr.sgdigital.movies.base.Formats;
import gr.sgdigital.movies.domain.Crew;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class CrewSimpleViewDTO extends BaseResponseDTO<Crew> {
	private static final long serialVersionUID = 1L;

	private long   crewId;
	private String firstName;
	private String lastName;
	private String middleName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Formats.DATE_FORMAT)
	private Date birthDate;

	@Override
	public void updateFromEntity(Crew crew) {
		crewId     = crew.getId();
		firstName  = crew.getFirstName();
		lastName   = crew.getLastName();
		middleName = crew.getMiddleName();
		birthDate  = crew.getBirthDate();
	}
}



