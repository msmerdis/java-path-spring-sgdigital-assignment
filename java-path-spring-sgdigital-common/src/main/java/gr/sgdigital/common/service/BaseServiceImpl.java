package gr.sgdigital.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import gr.sgdigital.common.base.BaseComponent;
import gr.sgdigital.common.domain.BaseEntity;
import gr.sgdigital.common.repository.BaseRepository;

public abstract class BaseServiceImpl<E extends BaseEntity<I>, I> extends BaseComponent implements BaseService<E, I> {

	final protected BaseRepository<E, I> repository;

	public BaseServiceImpl (BaseRepository<E, I> repository) {
		this.repository = repository;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public E create(final E entity) {
		logger.trace("Creating {}.", entity);
		return repository.save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public List<E> createAll(final List<E> entities) {
		final List<E> updatedEntities = new ArrayList<>();
		for (final E entity : entities) {
			updatedEntities.add(create(entity));
		}
		return updatedEntities;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void update(final E entity) {
		logger.trace("Updating {}.", entity);
		repository.save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void delete(final E entity) {
		logger.trace("Deleting {}.", entity);
		repository.delete(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void deleteById(final I id) {
		final E entityFound = repository.getById(id);
		logger.trace("Deleting {}.", entityFound);
		repository.deleteById(id);
	}

	@Override
	public boolean exists(final E entity) {
		logger.trace("Checking whether {} exists.", entity);
		return repository.existsById(entity.getId());
	}

	@Override
	public E find(I id) {
		return repository.findById(id).orElseThrow(NoSuchElementException::new);
	}

	@Override
	public List<E> findAll() {
		logger.trace("Retrieving all entities.");
		return repository.findAll();
	}
}



