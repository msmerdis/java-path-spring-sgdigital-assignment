package gr.sgdigital.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.sgdigital.common.controller.BaseController;
import gr.sgdigital.movies.domain.Serie;
import gr.sgdigital.movies.service.SerieService;

@RestController
@RequestMapping("/api/serie")
public class SerieController extends BaseController<Serie, Long> {

	@Autowired
	public SerieController(SerieService service) {
		super(service);
	}

}



