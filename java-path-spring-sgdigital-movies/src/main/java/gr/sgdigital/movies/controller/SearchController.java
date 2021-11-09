package gr.sgdigital.movies.controller;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gr.sgdigital.common.base.BaseComponent;
import gr.sgdigital.common.service.AbstractService;
import gr.sgdigital.common.transfer.ApiResponse;

@RestController
@RequestMapping ("/api/search")
public class SearchController extends BaseComponent {

	private ListableBeanFactory listableBeanFactory;

	@Autowired
	public SearchController (ListableBeanFactory listableBeanFactory) {
		this.listableBeanFactory = listableBeanFactory;
		logger.info("exectute on contructor");

		var beans = listableBeanFactory.getBeansOfType(AbstractService.class, false, false);

		beans.forEach((name, service) -> logger.info("got service : " + name));
	}

	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<String> search(@RequestHeader("X-Search-Terms") String keyword) throws Exception {
		var beans = listableBeanFactory.getBeansOfType(AbstractService.class, false, false);

		beans.forEach((name, service) -> logger.info("got service : " + name + " with searchable " + service.supportsFreeTestSearch()));

		return new ApiResponse<String> ("dummy");
	}
}



