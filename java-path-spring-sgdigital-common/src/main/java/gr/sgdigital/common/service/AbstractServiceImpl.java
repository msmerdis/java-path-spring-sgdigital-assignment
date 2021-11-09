package gr.sgdigital.common.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManagerFactory;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.TermContext;
import org.hibernate.search.query.dsl.TermMatchingContext;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import gr.sgdigital.common.base.BaseComponent;
import gr.sgdigital.common.domain.AbstractEntity;
import gr.sgdigital.common.repository.AbstractRepository;
import gr.sgdigital.common.transfer.AbstractCreateDTO;
import gr.sgdigital.common.transfer.AbstractResponseDTO;
import gr.sgdigital.common.transfer.AbstractUpdateDTO;
import gr.sgdigital.common.transfer.ApiStatus;
import gr.sgdigital.common.transfer.status.ConflictException;
import gr.sgdigital.common.transfer.status.NotFoundException;
import gr.sgdigital.common.transfer.status.NotImplementedException;

public abstract class AbstractServiceImpl<
	Key,
	Entity extends AbstractEntity<Key, Entity, SimpleDTO, DetailDTO>,
	CreateDTO extends AbstractCreateDTO<Entity>,
	UpdateDTO extends AbstractUpdateDTO<Entity, Key>,
	SimpleDTO extends AbstractResponseDTO<Entity>,
	DetailDTO extends AbstractResponseDTO<Entity>,
	Repository extends AbstractRepository<Key, Entity, CreateDTO, UpdateDTO, SimpleDTO, DetailDTO>
> extends BaseComponent implements AbstractService<Key, Entity, CreateDTO, UpdateDTO, SimpleDTO, DetailDTO> {

	final protected Repository repository;
	final private Class<Entity> entityClass;

	private FullTextEntityManager fullTextEntityManager;

	public AbstractServiceImpl (Repository repository, EntityManagerFactory entityManagerFactory, Class<Entity> entityClass) {
		this.repository  = repository;
		this.entityClass = entityClass;

		if (supportsFreeTestSearch()) {
			this.fullTextEntityManager = Search.getFullTextEntityManager(
				entityManagerFactory.createEntityManager()
			);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public DetailDTO create(CreateDTO dto) throws ApiStatus, Exception {
		Entity entity = entityClass.getDeclaredConstructor().newInstance();

		dto.updateEntity(entity);

		try {
			entity = repository.saveAndFlush(entity);
		} catch (Exception e) {
			throw new ConflictException("Entity already exists");
		}

		return (DetailDTO) entity.detailView();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void update(UpdateDTO dto) throws ApiStatus {
		Optional<Entity> optionalEntity = repository.findById(dto.getId());

		if (optionalEntity.isEmpty()) {
			throw new NotFoundException("Entity not found.");
		}

		Entity entity = optionalEntity.get();

		dto.updateEntity(entity);

		try {
			repository.saveAndFlush(entity);
		} catch (Exception e) {
			throw new ConflictException("Cannot update entity");
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void delete(Key id) throws ApiStatus {
		Optional<Entity> entity = repository.findById(id);

		if (entity.isEmpty()) {
			throw new NotFoundException("Entity not found.");
		}

		repository.deleteById(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public boolean exists(Key id) throws ApiStatus {
		return repository.existsById(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public DetailDTO find(Key id) throws ApiStatus, Exception {
		Optional<Entity> entity = repository.findById(id);

		if (entity.isEmpty()) {
			throw new NotFoundException("Entity not found.");
		}

		return entity.get().detailView();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public List<SimpleDTO> findAll() throws ApiStatus, Exception {
		List<SimpleDTO> results = new LinkedList<SimpleDTO>();

		for (Entity entity : repository.findAll()) {
			results.add(entity.simpleView());
		}

		return results;
	}

	@Override
	public boolean supportsFreeTestSearch() {
		return entityClass.isAnnotationPresent(Indexed.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DetailDTO> freeTextSearch(String keyword) throws ApiStatus, Exception {
		if (!supportsFreeTestSearch()) {
			throw new NotImplementedException("Free text search not supported for this entity");
		}

		var results = new LinkedList<DetailDTO>();

		var queryBuilder = fullTextEntityManager.getSearchFactory()
			.buildQueryBuilder()
			.forEntity(entityClass)
			.get();

		var query = addSearchFields(queryBuilder.keyword())
			.matching(keyword)
			.createQuery();

		var fullTextQuery = fullTextEntityManager.createFullTextQuery(query, entityClass);

		for (Entity entity : (List<Entity>)fullTextQuery.getResultList()) {
			results.add(entity.detailView());
		}

		return results;
	}

	protected abstract TermMatchingContext addSearchFields (TermContext context);
}



