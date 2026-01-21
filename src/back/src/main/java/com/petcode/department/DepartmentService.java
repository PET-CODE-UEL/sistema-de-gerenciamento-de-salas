package com.petcode.department;

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

import com.petcode.center.Center;
import com.petcode.center.CenterRepository;
import com.petcode.controller.ResourceNotFoundException;

@Service
public class DepartmentService {
  private DepartmentRepository repository;
  private CenterRepository centerRepository;

  @Autowired
  public DepartmentService(DepartmentRepository repository, CenterRepository centerRepository) {
    this.repository = repository;
    this.centerRepository = centerRepository;
  }

  public DepartmentResponseDto createDepartment(DepartmentCreateDto departmentDto) throws Exception {
    if (repository.existsById(departmentDto.getCode())) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Department with this code already exists");
    }
    Center center = centerRepository.findByCode(departmentDto.getCenterCode())
        .orElseThrow(() -> new IllegalArgumentException(
            String.format("There is no Center with code %s", departmentDto.getCenterCode())));

    Department d = new Department(departmentDto.getCode(), departmentDto.getName(), center);

    return new DepartmentResponseDto(repository.save(d));
  }

  public DepartmentResponseDto findByCode(String code) throws Exception {
    Optional<Department> department = repository.findByCode(code);
    if (department.isEmpty()) {
      throw new Exception("Department code not found");
    } else {
      return new DepartmentResponseDto(department.get());
    }
  }

  public List<DepartmentResponseDto> findAll() throws ResourceNotFoundException {
    List<Department> departments = repository.findAll();

    List<DepartmentResponseDto> responseDtos = new ArrayList<DepartmentResponseDto>();

    for (Department d : departments) {
      responseDtos.add(new DepartmentResponseDto(d));
    }

    if (departments.isEmpty()) {
      throw new ResourceNotFoundException("There are no registered departments");
    } else {
      return responseDtos;
    }
  }

  public DepartmentResponseDto replaceDepartment(String code, Department department) throws Exception {
    if (repository.existsById(department.getCode())) {
      return new DepartmentResponseDto(repository.save(department));
    } else {
      throw new ResourceNotFoundException("Department code not found");
    }
  }

  public DepartmentResponseDto updateDepartment(String code, Map<String, Object> fields) throws Exception {
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

    return new DepartmentResponseDto(repository.save(department));
  }

  public void deleteByCode(String code) throws Exception {
    if (repository.existsById(code)) {
      repository.deleteById(code);
    } else {
      throw new ResourceNotFoundException("Department code not found");
    }
  }
}
