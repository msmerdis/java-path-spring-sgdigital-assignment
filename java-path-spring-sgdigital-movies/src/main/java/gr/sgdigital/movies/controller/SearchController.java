package gr.sgdigital.movies.controller;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gr.sgdigital.common.base.BaseComponent;
import gr.sgdigital.common.service.AbstractServiceImpl;
import gr.sgdigital.common.transfer.ApiResponse;
import gr.sgdigital.common.transfer.SearchResult;

@RestController
@RequestMapping ("/api/search")
public class SearchController extends BaseComponent {

	private ListableBeanFactory listableBeanFactory;

	@SuppressWarnings("rawtypes")
	private List<AbstractServiceImpl> services = new LinkedList<AbstractServiceImpl> ();

	@Autowired
	public SearchController (ListableBeanFactory listableBeanFactory) {
		this.listableBeanFactory = listableBeanFactory;
	}

	@PostConstruct
	public void identifyServices () {
		// get all service beans
		var beans = listableBeanFactory.getBeansOfType(AbstractServiceImpl.class, false, false);

		for (String name : beans.keySet()) {
			var service = beans.get(name);
			logger.info("identified service implementation {}", service.getClass().getName());

			if (service.supportsFreeTextSearch()) {
				logger.info(" -> service supports free text search, keep for future search");
				services.add(service);
			}
		}
	}

	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<List<SearchResult>> search(@RequestHeader("X-Search-Terms") String keyword) throws Exception {

		List<SearchResult> results = new LinkedList<SearchResult> ();

		for (var service : services) {
			for (var object : service.freeTextSearch(keyword)) {
				results.add(new SearchResult(service.entityName(), object));
			}
		}

		return new ApiResponse<List<SearchResult>> (results);
	}
}



