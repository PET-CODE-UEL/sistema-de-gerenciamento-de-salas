package com.petcode.course;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.server.ResponseStatusException;

import com.petcode.department.Department;
import com.petcode.department.DepartmentRepository;

@Service
public class CourseService {
  private CourseRepository repository;
  private DepartmentRepository departmentRepository;

  @Autowired
  public CourseService(CourseRepository repository, DepartmentRepository departmentRepository) {
    this.repository = repository;
    this.departmentRepository = departmentRepository;
  }

  public CourseResponseDto createCourse(CourseCreateDto courseCreateDto) throws Exception {
    if (repository.existsById(courseCreateDto.getCode())) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Course with this code already exists");
    }

    Department department = departmentRepository.findByCode(courseCreateDto.getDepartmentCode())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT,
            String.format("There is no Department with code %s", courseCreateDto.getDepartmentCode())));

    Course c = new Course(courseCreateDto.getCode(), courseCreateDto.getName(), department);

    return new CourseResponseDto(repository.save(c));
  }

  public CourseResponseDto findByCode(String code) throws Exception {
    Optional<Course> course = repository.findByCode(code);
    if (course.isEmpty()) {
      throw new Exception("Course code not found");
    } else {
      return new CourseResponseDto(course.get());
    }
  }

  public List<CourseResponseDto> findAll() throws Exception {
    List<Course> courses = repository.findAll();

    List<CourseResponseDto> responseDtos = new ArrayList<CourseResponseDto>();

    for (Course c : courses) {
      responseDtos.add(new CourseResponseDto(c));
    }

    if (responseDtos.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NO_CONTENT, "There are no registered courses");
    } else {
      return responseDtos;
    }
  }

  public CourseResponseDto replaceCourse(String code, Course course) throws Exception {
    if (repository.existsById(course.getCode())) {
      return new CourseResponseDto(repository.save(course));
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course code not found");
    }
  }

  public CourseResponseDto updateCourse(String code, Map<String, Object> fields) throws Exception {
    Optional<Course> opt = repository.findByCode(code);
    if (opt.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course code not found");
    }
    Course department = opt.get();

    fields.forEach((key, val) -> {
      Field field = ReflectionUtils.findField(Course.class, key);
      if (field == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            String.format("Tried to update non existent field %s", key));
      }
      field.setAccessible(true);
      ReflectionUtils.setField(field, department, val);
    });

    return new CourseResponseDto(repository.save(department));
  }

  public void deleteByCode(String code) throws Exception {
    if (repository.existsById(code)) {
      repository.deleteById(code);
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course code not found");
    }
  }
}
