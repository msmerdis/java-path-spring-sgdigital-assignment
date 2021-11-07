package gr.sgdigital.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.sgdigital.common.controller.BaseController;
import gr.sgdigital.movies.domain.Serie;
import gr.sgdigital.movies.service.SerieService;
import gr.sgdigital.movies.transfer.SerieCreateDTO;
import gr.sgdigital.movies.transfer.SerieDetailViewDTO;
import gr.sgdigital.movies.transfer.SerieSimpleViewDTO;
import gr.sgdigital.movies.transfer.SerieUpdateDTO;

@RestController
@RequestMapping("/api/serie")
public class SerieController extends BaseController<
	Long,
	Serie,
	SerieCreateDTO,
	SerieUpdateDTO,
	SerieSimpleViewDTO,
	SerieDetailViewDTO,
	SerieService
> {

	@Autowired
	public SerieController(SerieService service) {
		super(service);
	}

}



