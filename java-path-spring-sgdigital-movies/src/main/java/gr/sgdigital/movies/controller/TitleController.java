package gr.sgdigital.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.sgdigital.common.controller.AbstractController;
import gr.sgdigital.movies.domain.Title;
import gr.sgdigital.movies.service.TitleService;
import gr.sgdigital.movies.transfer.TitleCreateDTO;
import gr.sgdigital.movies.transfer.TitleDetailViewDTO;
import gr.sgdigital.movies.transfer.TitleSimpleViewDTO;
import gr.sgdigital.movies.transfer.TitleUpdateDTO;

@RestController
@RequestMapping("/api/title")
public class TitleController extends AbstractController<
	Long,
	Title,
	TitleCreateDTO,
	TitleUpdateDTO,
	TitleSimpleViewDTO,
	TitleDetailViewDTO,
	TitleService
> {

	@Autowired
	public TitleController(TitleService service) {
		super(service);
	}

}



