package gr.sgdigital.movies.service;

import java.util.Optional;

import javax.persistence.EntityManagerFactory;

import org.hibernate.search.query.dsl.TermContext;
import org.hibernate.search.query.dsl.TermMatchingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.sgdigital.common.service.AbstractServiceImpl;
import gr.sgdigital.common.transfer.status.NotFoundException;
import gr.sgdigital.movies.domain.CrewRole;
import gr.sgdigital.movies.domain.TitleCrew;
import gr.sgdigital.movies.repository.CrewRoleRepository;
import gr.sgdigital.movies.transfer.CrewRoleCreateDTO;
import gr.sgdigital.movies.transfer.CrewRoleDetailViewDTO;
import gr.sgdigital.movies.transfer.CrewRoleSimpleViewDTO;
import gr.sgdigital.movies.transfer.CrewRoleUpdateDTO;

@Service
public class CrewRoleServiceImpl extends AbstractServiceImpl<
	Integer,
	CrewRole,
	CrewRoleCreateDTO,
	CrewRoleUpdateDTO,
	CrewRoleSimpleViewDTO,
	CrewRoleDetailViewDTO,
	CrewRoleRepository
> implements CrewRoleService {

	@Autowired
	public CrewRoleServiceImpl(CrewRoleRepository repository, EntityManagerFactory entityManagerFactory) {
		super(repository, entityManagerFactory, CrewRole.class);
	}

	@Override
	public void linkTitleCrew(TitleCrew titleCrew, String role) throws NotFoundException {
		Optional<CrewRole> crewRole = repository.findByName(role);

		if (crewRole.isEmpty()) {
			throw new NotFoundException("Crew role not found to link");
		}

		titleCrew.setCrewRole(crewRole.get());
	}

	@Override
	protected TermMatchingContext addSearchFields(TermContext context) {
		// crew role is not indexed
		return null;
	}

}



