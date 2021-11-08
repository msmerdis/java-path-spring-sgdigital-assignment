package gr.sgdigital.movies.transfer;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import gr.sgdigital.common.transfer.BaseUpdateDTO;
import gr.sgdigital.movies.domain.Crew;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class CrewUpdateDTO extends BaseUpdateDTO<Crew, Long> {
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



