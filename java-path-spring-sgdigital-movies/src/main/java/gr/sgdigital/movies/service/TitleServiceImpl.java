package gr.sgdigital.movies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.sgdigital.common.service.BaseServiceImpl;
import gr.sgdigital.movies.domain.Title;
import gr.sgdigital.movies.domain.TitleType;
import gr.sgdigital.movies.repository.TitleRepository;

@Service
public class TitleServiceImpl extends BaseServiceImpl<Title, Long, TitleRepository> implements TitleService {

	@Autowired
	public TitleServiceImpl(TitleRepository repository) {
		super(repository);
	}

	@Override
	public Title findByTitle(String title) {
		return repository.findByTitle(title);
	}

	@Override
	public Title loadOrCreate(String theTitle, TitleType type, String description) {
		Title title = repository.findByTitle(theTitle);

		if (title == null) {
			title = new Title ();
			title.setTitle(theTitle);
			title.setType(type);
			title.setDescription(description);
			title = repository.save(title);
		}

		return title;
	}
}



