package gr.sgdigital.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.sgdigital.common.controller.BaseController;
import gr.sgdigital.movies.domain.Crew;
import gr.sgdigital.movies.service.CrewService;
import gr.sgdigital.movies.transfer.CrewCreateDTO;
import gr.sgdigital.movies.transfer.CrewDetailViewDTO;
import gr.sgdigital.movies.transfer.CrewSimpleViewDTO;
import gr.sgdigital.movies.transfer.CrewUpdateDTO;

@RestController
@RequestMapping("/api/crew")
public class CrewController extends BaseController<
	Long,
	Crew,
	CrewCreateDTO,
	CrewUpdateDTO,
	CrewSimpleViewDTO,
	CrewDetailViewDTO,
	CrewService
> {

	@Autowired
	public CrewController(CrewService service) {
		super(service);
	}

}



