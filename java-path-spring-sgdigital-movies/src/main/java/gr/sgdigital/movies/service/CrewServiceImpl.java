package gr.sgdigital.movies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.sgdigital.common.service.BaseServiceImpl;
import gr.sgdigital.movies.domain.Crew;
import gr.sgdigital.movies.repository.CrewRepository;
import gr.sgdigital.movies.transfer.CrewCreateDTO;
import gr.sgdigital.movies.transfer.CrewDetailViewDTO;
import gr.sgdigital.movies.transfer.CrewSimpleViewDTO;
import gr.sgdigital.movies.transfer.CrewUpdateDTO;

@Service
public class CrewServiceImpl extends BaseServiceImpl<
	Long,
	Crew,
	CrewCreateDTO,
	CrewUpdateDTO,
	CrewSimpleViewDTO,
	CrewDetailViewDTO,
	CrewRepository
> implements CrewService {

	@Autowired
	public CrewServiceImpl(CrewRepository repository) {
		super(repository, Crew.class);
	}
}



