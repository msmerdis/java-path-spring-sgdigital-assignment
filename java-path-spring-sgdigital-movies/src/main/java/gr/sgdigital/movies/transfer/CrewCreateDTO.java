package gr.sgdigital.movies.transfer;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import gr.sgdigital.common.transfer.AbstractCreateDTO;
import gr.sgdigital.movies.domain.Crew;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CrewCreateDTO implements AbstractCreateDTO<Crew> {
	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

	private String middleName;
	private Date birthDate;

	@Override
	public void updateEntity(Crew crew) {
		crew.setFirstName(firstName);
		crew.setMiddleName(middleName);
		crew.setLastName(lastName);
		crew.setBirthDate(birthDate);
	}
}



