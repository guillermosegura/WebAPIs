package mx.axity.com.webapi.rest.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.axity.com.webapi.rest.commons.dto.ProfessorDTO;
import mx.axity.com.webapi.rest.service.ProfessorService;

@RestController
@RequestMapping(path = "/api/professors", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfessorController {

  @Autowired
  private ProfessorService professorService;

  @GetMapping
  public List<EntityModel<ProfessorDTO>> getProfessors() {
    // Lógica para obtener todos los estudiantes desde tu servicio o repositorio
    List<ProfessorDTO> professors = professorService.findAll();

    // Crear enlace a sí mismo
    Link selfLink = WebMvcLinkBuilder.linkTo(ProfessorController.class).withSelfRel();

    // Crear una lista de EntityModel<ProfessorDTO>
    List<EntityModel<ProfessorDTO>> professorModels = new ArrayList<>();

    // Agregar enlaces a cada estudiante
    professors.forEach(professor -> {
      EntityModel<ProfessorDTO> professorModel = EntityModel.of(professor);
      professorModel.add(WebMvcLinkBuilder
          .linkTo(WebMvcLinkBuilder.methodOn(ProfessorController.class).getProfessor(professor.getId())).withSelfRel());


      professorModels.add(professorModel);
    });

    return professorModels;
  }

  @GetMapping("/{id}")
  public EntityModel<ProfessorDTO> getProfessor(@PathVariable Integer id) {
    // Lógica para obtener el estudiante por ID desde tu servicio o repositorio
    ProfessorDTO Professor = this.professorService.findById(id);

    // Crear enlace a sí mismo
    Link selfLink = WebMvcLinkBuilder.linkTo(ProfessorController.class).slash(id).withSelfRel();

    // Agregar enlace al estudiante
    EntityModel<ProfessorDTO> ProfessorModel = EntityModel.of(Professor, selfLink);

    return ProfessorModel;
  }
}
