package gr.sgdigital.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import gr.sgdigital.common.domain.BaseEntity;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity<I>, I> extends JpaRepository<E, I> {
}



