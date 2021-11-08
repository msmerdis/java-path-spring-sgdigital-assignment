package gr.sgdigital.movies.repository;

import gr.sgdigital.common.repository.AbstractRepository;
import gr.sgdigital.movies.domain.Crew;
import gr.sgdigital.movies.transfer.CrewCreateDTO;
import gr.sgdigital.movies.transfer.CrewDetailViewDTO;
import gr.sgdigital.movies.transfer.CrewSimpleViewDTO;
import gr.sgdigital.movies.transfer.CrewUpdateDTO;

public interface CrewRepository extends AbstractRepository<Long, Crew, CrewCreateDTO, CrewUpdateDTO, CrewSimpleViewDTO, CrewDetailViewDTO> {
}



