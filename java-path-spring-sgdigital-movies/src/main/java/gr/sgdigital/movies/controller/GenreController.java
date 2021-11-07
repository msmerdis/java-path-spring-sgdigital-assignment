package gr.sgdigital.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.sgdigital.common.controller.BaseController;
import gr.sgdigital.movies.domain.Genre;
import gr.sgdigital.movies.service.GenreService;
import gr.sgdigital.movies.transfer.GenreCreateDTO;
import gr.sgdigital.movies.transfer.GenreDetailViewDTO;
import gr.sgdigital.movies.transfer.GenreSimpleViewDTO;
import gr.sgdigital.movies.transfer.GenreUpdateDTO;

@RestController
@RequestMapping("/api/genre")
public class GenreController extends BaseController<
	Integer,
	Genre,
	GenreCreateDTO,
	GenreUpdateDTO,
	GenreSimpleViewDTO,
	GenreDetailViewDTO,
	GenreService
> {

	@Autowired
	public GenreController(GenreService service) {
		super(service);
	}

}



