package gr.sgdigital.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.sgdigital.common.controller.AbstractController;
import gr.sgdigital.movies.domain.Season;
import gr.sgdigital.movies.service.SeasonService;
import gr.sgdigital.movies.transfer.SeasonCreateDTO;
import gr.sgdigital.movies.transfer.SeasonDetailViewDTO;
import gr.sgdigital.movies.transfer.SeasonSimpleViewDTO;
import gr.sgdigital.movies.transfer.SeasonUpdateDTO;

@RestController
@RequestMapping("/api/season")
public class SeasonController extends AbstractController<
	Long,
	Season,
	SeasonCreateDTO,
	SeasonUpdateDTO,
	SeasonSimpleViewDTO,
	SeasonDetailViewDTO,
	SeasonService
> {

	@Autowired
	public SeasonController(SeasonService service) {
		super(service);
	}

}



