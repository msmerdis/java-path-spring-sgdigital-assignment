package gr.sgdigital.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.sgdigital.common.controller.AbstractController;
import gr.sgdigital.movies.domain.Episode;
import gr.sgdigital.movies.service.EpisodeService;
import gr.sgdigital.movies.transfer.EpisodeCreateDTO;
import gr.sgdigital.movies.transfer.EpisodeDetailViewDTO;
import gr.sgdigital.movies.transfer.EpisodeSimpleViewDTO;
import gr.sgdigital.movies.transfer.EpisodeUpdateDTO;

@RestController
@RequestMapping("/api/episode")
public class EpisodeController extends AbstractController<
	Long,
	Episode,
	EpisodeCreateDTO,
	EpisodeUpdateDTO,
	EpisodeSimpleViewDTO,
	EpisodeDetailViewDTO,
	EpisodeService
> {

	@Autowired
	public EpisodeController(EpisodeService service) {
		super(service);
	}

}



