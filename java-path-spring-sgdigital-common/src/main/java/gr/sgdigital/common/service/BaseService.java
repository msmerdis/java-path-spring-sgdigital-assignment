package gr.sgdigital.common.service;

import java.util.List;

public interface BaseService<E, I> {
	E create(E entity);

	List<E> createAll(List<E> entities);

	void update(E entity);

	void delete(E entity);

	void deleteById(I id);

	boolean exists(E entity);

	E find(I id);

	List<E> findAll();
}



