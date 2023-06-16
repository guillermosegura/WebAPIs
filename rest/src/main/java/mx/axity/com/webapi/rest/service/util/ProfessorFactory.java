package mx.axity.com.webapi.rest.service.util;

import mx.axity.com.webapi.rest.commons.dto.ProfessorDTO;
import mx.axity.com.webapi.rest.model.ProfessorDO;

public final class ProfessorFactory {
  public static ProfessorDTO transform(ProfessorDO professorDO) {
      ProfessorDTO professorDTO = new ProfessorDTO();
      professorDTO.setId(professorDO.getId());
      professorDTO.setName(professorDO.getName());
      professorDTO.setLastname(professorDO.getLastname());
      professorDTO.setEmail(professorDO.getEmail());
      return professorDTO;
  }

  public static ProfessorDO transform(ProfessorDTO professorDTO) {
      ProfessorDO professorDO = new ProfessorDO();
      professorDO.setId(professorDTO.getId());
      professorDO.setName(professorDTO.getName());
      professorDO.setLastname(professorDTO.getLastname());
      professorDO.setEmail(professorDTO.getEmail());
      return professorDO;
  }
}
