package gr.sgdigital.movies.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import gr.sgdigital.common.service.AbstractServiceImpl;
import gr.sgdigital.common.transfer.ApiStatus;
import gr.sgdigital.common.transfer.status.ConflictException;
import gr.sgdigital.common.transfer.status.NotFoundException;
import gr.sgdigital.movies.domain.TitleCrew;
import gr.sgdigital.movies.domain.TitleCrewKey;
import gr.sgdigital.movies.repository.TitleCrewRepository;
import gr.sgdigital.movies.transfer.TitleCrewCreateDTO;
import gr.sgdigital.movies.transfer.TitleCrewDetailViewDTO;
import gr.sgdigital.movies.transfer.TitleCrewSimpleViewDTO;
import gr.sgdigital.movies.transfer.TitleCrewUpdateDTO;

@Service
public class TitleCrewServiceImpl extends AbstractServiceImpl<
	TitleCrewKey,
	TitleCrew,
	TitleCrewCreateDTO,
	TitleCrewUpdateDTO,
	TitleCrewSimpleViewDTO,
	TitleCrewDetailViewDTO,
	TitleCrewRepository
> implements TitleCrewService {

	private CrewService crewService;
	private CrewRoleService crewRoleService;
	private TitleService titleService;

	@Autowired
	public TitleCrewServiceImpl(
		TitleCrewRepository repository,
		CrewService crewService,
		CrewRoleService crewRoleService,
		TitleService titleService
	) {
		super(repository, TitleCrew.class);

		this.crewService     = crewService;
		this.crewRoleService = crewRoleService;
		this.titleService    = titleService;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public TitleCrewDetailViewDTO create(TitleCrewCreateDTO dto) throws ApiStatus, Exception {
		TitleCrew titleCrew = new TitleCrew ();

		dto.updateEntity(titleCrew);

		// we need to map title with the actual persistent entity
		// for the save operation to be successful
		titleService.linkTitleCrew(titleCrew, dto.getTitleId());
		crewService.linkTitleCrew(titleCrew, dto.getCrewId());
		crewRoleService.linkTitleCrew(titleCrew, dto.getRole());

		return repository.saveAndFlush(titleCrew).detailView();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void update(TitleCrewUpdateDTO dto) throws ApiStatus {
		Optional<TitleCrew> optionalTitleCrew = repository.findById(dto.getId());

		if (optionalTitleCrew.isEmpty()) {
			throw new NotFoundException("Title crew not found.");
		}

		TitleCrew titleCrew = optionalTitleCrew.get();

		crewRoleService.linkTitleCrew(titleCrew, dto.getRole());

		try {
			repository.saveAndFlush(titleCrew);
		} catch (Exception e) {
			throw new ConflictException("Cannot link title and crew");
		}

	}
}



