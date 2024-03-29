package gr.sgdigital.movies.service;

import java.util.Optional;

import javax.persistence.EntityManagerFactory;

import org.hibernate.search.query.dsl.TermContext;
import org.hibernate.search.query.dsl.TermMatchingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.sgdigital.common.service.AbstractServiceImpl;
import gr.sgdigital.common.transfer.status.NotFoundException;
import gr.sgdigital.movies.domain.Crew;
import gr.sgdigital.movies.domain.TitleCrew;
import gr.sgdigital.movies.repository.CrewRepository;
import gr.sgdigital.movies.transfer.CrewCreateDTO;
import gr.sgdigital.movies.transfer.CrewDetailViewDTO;
import gr.sgdigital.movies.transfer.CrewSimpleViewDTO;
import gr.sgdigital.movies.transfer.CrewUpdateDTO;

@Service
public class CrewServiceImpl extends AbstractServiceImpl<
	Long,
	Crew,
	CrewCreateDTO,
	CrewUpdateDTO,
	CrewSimpleViewDTO,
	CrewDetailViewDTO,
	CrewRepository
> implements CrewService {

	@Autowired
	public CrewServiceImpl(CrewRepository repository, EntityManagerFactory entityManagerFactory) {
		super(repository, entityManagerFactory, Crew.class);
	}

	@Override
	public void linkTitleCrew(TitleCrew titleCrew, Long crewId) throws NotFoundException {
		Optional<Crew> crew = repository.findById(crewId);

		if (crew.isEmpty()) {
			throw new NotFoundException("Crew not found to link");
		}

		titleCrew.setCrew(crew.get());
	}

	@Override
	protected TermMatchingContext addSearchFields (TermContext context) {
		return context.onFields ("firstName", "lastName", "middleName");
	}

}



