package gr.sgdigital.app.service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.sgdigital.common.base.BaseComponent;

@Service
public class LuceneIndexService extends BaseComponent {

	private FullTextEntityManager fullTextEntityManager;

	@Autowired
	public LuceneIndexService(EntityManagerFactory entityManagerFactory){
		fullTextEntityManager = Search.getFullTextEntityManager(entityManagerFactory.createEntityManager());
	}

	@PostConstruct
	public void triggerIndexing() {
		logger.info("start index creation");
		try {
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		logger.info("index creation completed");
	}
}



