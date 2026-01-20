package com.petcode.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.petcode.controller.ResourceNotFoundException;
import com.petcode.dto.DepartmentCreateDTO;
import com.petcode.entity.Center;
import com.petcode.entity.Department;
import com.petcode.repository.CenterRepository;
import com.petcode.repository.DepartmentRepository;

@Service
public class DepartmentService {
  private DepartmentRepository repository;
  private CenterRepository centerRepository;

  @Autowired
  public DepartmentService(DepartmentRepository repository, CenterRepository centerRepository) {
    this.repository = repository;
    this.centerRepository = centerRepository;
  }

  public Department createDepartment(DepartmentCreateDTO departmentDTO) throws Exception {
    Center center = centerRepository.findByCode(departmentDTO.getCenterCode())
        .orElseThrow(() -> new IllegalArgumentException(
            String.format("There is no Center with code %s", departmentDTO.getCenterCode())));

    Department d = new Department(departmentDTO.getCode(), departmentDTO.getName(), center);

    return repository.save(d);
  }

  public Department findByCode(String code) throws Exception {
    Optional<Department> department = repository.findByCode(code);
    if (department.isEmpty()) {
      throw new Exception("Department code not found");
    } else {
      return department.get();
    }
  }

  public List<Department> findAll() throws ResourceNotFoundException {
    List<Department> departments = repository.findAll();
    if (departments.isEmpty()) {
      throw new ResourceNotFoundException("There are no registered departments");
    } else {
      return departments;
    }
  }

  public Department replaceDepartment(String code, Department department) throws Exception {
    if (repository.existsById(department.getCode())) {
      return repository.save(department);
    } else {
      throw new ResourceNotFoundException("Department code not found");
    }
  }

  public Department updateDepartment(String code, Map<String, Object> fields) throws Exception {
    Optional<Department> opt = repository.findByCode(code);
    if (opt.isEmpty()) {
      throw new ResourceNotFoundException("Department code not found");
    }
    Department department = opt.get();

    fields.forEach((key, val) -> {
      Field field = ReflectionUtils.findField(Department.class, key);
      if (field == null) {
        throw new IllegalArgumentException(String.format("Tried to update non existent field %s", key));
      }
      field.setAccessible(true);
      ReflectionUtils.setField(field, department, val);
    });

    return repository.save(department);
  }

  public void deleteByCode(String code) throws Exception {
    if (repository.existsById(code)) {
      repository.deleteById(code);
    } else {
      throw new ResourceNotFoundException("Department code not found");
    }
  }
}
