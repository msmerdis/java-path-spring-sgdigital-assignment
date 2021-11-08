package gr.sgdigital.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.sgdigital.common.controller.AbstractController;
import gr.sgdigital.movies.domain.TitleCrew;
import gr.sgdigital.movies.domain.TitleCrewKey;
import gr.sgdigital.movies.service.TitleCrewService;
import gr.sgdigital.movies.transfer.TitleCrewCreateDTO;
import gr.sgdigital.movies.transfer.TitleCrewDetailViewDTO;
import gr.sgdigital.movies.transfer.TitleCrewSimpleViewDTO;
import gr.sgdigital.movies.transfer.TitleCrewUpdateDTO;

@RestController
@RequestMapping("/api/titlecrew")
public class TitleCrewController extends AbstractController<
	TitleCrewKey,
	TitleCrew,
	TitleCrewCreateDTO,
	TitleCrewUpdateDTO,
	TitleCrewSimpleViewDTO,
	TitleCrewDetailViewDTO,
	TitleCrewService
> {

	@Autowired
	public TitleCrewController(TitleCrewService service) {
		super(service);
	}

}



