package mx.axity.com.webapi.rest.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axity.com.webapi.rest.commons.dto.ProfessorDTO;
import mx.axity.com.webapi.rest.commons.exception.BusinessException;
import mx.axity.com.webapi.rest.model.ProfessorDO;
import mx.axity.com.webapi.rest.persistence.ProfessorRepository;
import mx.axity.com.webapi.rest.service.ProfessorService;
import mx.axity.com.webapi.rest.service.util.ProfessorFactory;

@Service
public class ProfessorServiceImpl implements ProfessorService {
  @Autowired
  private ProfessorRepository professorRepository;

  @Override
  public ProfessorDTO findById(Integer id) {
    ProfessorDO professorDO = professorRepository.findById(id)
        .orElseThrow(() -> new BusinessException("Professor not found with id: " + id, 200));
    return ProfessorFactory.transform(professorDO);
  }

  @Override
  public List<ProfessorDTO> findAll() {
    List<ProfessorDO> professorDOList = professorRepository.findAll();

    List<ProfessorDTO> professors =
        professorDOList.stream().map(ProfessorFactory::transform).collect(Collectors.toList());
    return professors;
  }
}
