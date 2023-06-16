package mx.axity.com.webapi.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.axity.com.webapi.rest.commons.dto.CourseDTO;
import mx.axity.com.webapi.rest.service.CourseService;

@RestController
@RequestMapping(path = "/api/courses", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController {

  @Autowired
  private CourseService courseService;
  
  @GetMapping("/{id}")
  public EntityModel<CourseDTO> getCourse(@PathVariable Integer id) {
    // Lógica para obtener el estudiante por ID desde tu servicio o repositorio
    CourseDTO course = this.courseService.findById(id);

    // Crear enlace a sí mismo
    Link selfLink = WebMvcLinkBuilder.linkTo(CourseController.class).slash(id).withSelfRel();

    // Agregar enlace al estudiante
    EntityModel<CourseDTO> courseModel = EntityModel.of(course, selfLink);

    return courseModel;
  }
}
