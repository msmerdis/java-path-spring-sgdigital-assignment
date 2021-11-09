package gr.sgdigital.movies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import gr.sgdigital.common.repository.AbstractRepository;
import gr.sgdigital.movies.domain.Crew;
import gr.sgdigital.movies.transfer.CrewCreateDTO;
import gr.sgdigital.movies.transfer.CrewDetailViewDTO;
import gr.sgdigital.movies.transfer.CrewSimpleViewDTO;
import gr.sgdigital.movies.transfer.CrewUpdateDTO;

public interface CrewRepository extends AbstractRepository<Long, Crew, CrewCreateDTO, CrewUpdateDTO, CrewSimpleViewDTO, CrewDetailViewDTO> {

	@Query(value = "SELECT c.* FROM tcrew c WHERE MATCH (c.firstName, c.lastName, c.middleName) AGAINST (:keyword IN NATURAL LANGUAGE MODE)", nativeQuery = true)
	List<Crew> freeTextSearch(String keyword);

}



