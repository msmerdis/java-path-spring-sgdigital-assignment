package gr.sgdigital.common.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

public class AbstractServiceImpl<
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

	public AbstractServiceImpl (Repository repository, Class<Entity> entityClass) {
		this.repository  = repository;
		this.entityClass = entityClass;
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
}



