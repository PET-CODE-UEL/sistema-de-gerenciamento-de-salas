package com.petcode.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.petcode.entity.Course;

public interface CourseRepository extends ListCrudRepository<Course, String> {

  Optional<Course> findByCode(String code);

  List<Course> findByName(String name);

  Object deleteByCode(String code);

}
