package com.petcode.department;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

public interface DepartmentRepository extends ListCrudRepository<Department, String> {

  Optional<Department> findByCode(String code);

  List<Department> findByName(String name);

  Object deleteByCode(String code);

  List<Department> findByCenterCode(String centerCode);

}
