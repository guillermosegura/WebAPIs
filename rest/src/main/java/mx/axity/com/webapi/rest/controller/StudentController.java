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
import mx.axity.com.webapi.rest.commons.dto.CourseDTO;
import mx.axity.com.webapi.rest.commons.dto.StudentDTO;
import mx.axity.com.webapi.rest.service.StudentService;

@RestController
@RequestMapping(path = "/api/students", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

  @Autowired
  private StudentService studentService;

  @GetMapping("")
  public List<EntityModel<StudentDTO>> getAllStudents() {
    // Lógica para obtener todos los estudiantes desde tu servicio o repositorio
    List<StudentDTO> students = studentService.findAll();

//    // Crear enlace a sí mismo
//    Link selfLink = WebMvcLinkBuilder.linkTo(StudentController.class).withSelfRel();

    // Crear una lista de EntityModel<Student>
    List<EntityModel<StudentDTO>> studentModels = new ArrayList<>();

    // Agregar enlaces a cada estudiante
    students.forEach(student -> {
      EntityModel<StudentDTO> studentModel = EntityModel.of(student);
      studentModel.add(WebMvcLinkBuilder
          .linkTo(WebMvcLinkBuilder.methodOn(StudentController.class).getStudent(student.getId())).withSelfRel());

      Link coursesLink = WebMvcLinkBuilder
          .linkTo(WebMvcLinkBuilder.methodOn(StudentController.class).getStudentCourses(student.getId()))
          .withRel("courses");
      studentModel.add(coursesLink);

      Link advisorLink = WebMvcLinkBuilder
          .linkTo(WebMvcLinkBuilder.methodOn(ProfessorController.class).getProfessor(student.getAdvisorId()))
          .withRel("advisor");
      studentModel.add(advisorLink);

      studentModels.add(studentModel);
    });

    return studentModels;
  }

  @GetMapping("/{id}")
  public EntityModel<StudentDTO> getStudent(@PathVariable Integer id) {
    // Lógica para obtener el estudiante por ID desde tu servicio o repositorio
    StudentDTO student = this.studentService.findById(id);

    // Crear enlace a sí mismo
    Link selfLink = WebMvcLinkBuilder.linkTo(StudentController.class).slash(id).withSelfRel();

    // Agregar enlace al estudiante
    EntityModel<StudentDTO> studentModel = EntityModel.of(student, selfLink);

    Link advisorLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(ProfessorController.class).getProfessor(student.getAdvisorId()))
        .withRel("advisor");
    studentModel.add(advisorLink);

    Link coursesLink =
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(StudentController.class).getStudentCourses(student.getId()))
            .withRel("courses");
    studentModel.add(coursesLink);

    return studentModel;
  }

  @GetMapping("/{id}/courses")
  public List<EntityModel<CourseDTO>> getStudentCourses(@PathVariable Integer id) {
    // Lógica para obtener los cursos del estudiante desde tu servicio o repositorio
    List<CourseDTO> courses = this.studentService.findCoursesByStudentId(id);
    // Crear enlace a sí mismo
    Link selfLink = WebMvcLinkBuilder.linkTo(StudentController.class).slash(id).slash("courses").withSelfRel();

    // Agregar enlace a la lista de cursos del estudiante
    List<EntityModel<CourseDTO>> coursesModel = new ArrayList<>();

    courses.forEach(course -> {
      EntityModel<CourseDTO> courseModel = EntityModel.of(course);
      courseModel.add(WebMvcLinkBuilder
          .linkTo(WebMvcLinkBuilder.methodOn(CourseController.class).getCourse(course.getId())).withSelfRel());

      Link profesorLink = WebMvcLinkBuilder
          .linkTo(WebMvcLinkBuilder.methodOn(ProfessorController.class).getProfessor(course.getInstructorId()))
          .withRel("instructor");
      courseModel.add(profesorLink);

      coursesModel.add(courseModel);
    });

    return coursesModel;
  }

}
