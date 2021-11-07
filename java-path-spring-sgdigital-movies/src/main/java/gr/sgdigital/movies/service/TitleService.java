package gr.sgdigital.movies.service;

import gr.sgdigital.common.service.BaseService;
import gr.sgdigital.movies.domain.Title;
import gr.sgdigital.movies.transfer.TitleCreateDTO;
import gr.sgdigital.movies.transfer.TitleDetailViewDTO;
import gr.sgdigital.movies.transfer.TitleSimpleViewDTO;
import gr.sgdigital.movies.transfer.TitleUpdateDTO;

public interface TitleService extends BaseService<Long, Title, TitleCreateDTO, TitleUpdateDTO, TitleSimpleViewDTO, TitleDetailViewDTO> {
}



