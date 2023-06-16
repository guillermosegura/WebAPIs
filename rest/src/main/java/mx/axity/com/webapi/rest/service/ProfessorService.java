package mx.axity.com.webapi.rest.service;

import java.util.List;
import mx.axity.com.webapi.rest.commons.dto.ProfessorDTO;

public interface ProfessorService {
  ProfessorDTO findById(Integer id);
  List<ProfessorDTO> findAll();
}
