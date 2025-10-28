package com.petcode.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.petcode.controller.ResourceNotFoundException;
import com.petcode.entity.Center;
import com.petcode.repository.CenterRepository;

@Service
public class CenterService {

  private CenterRepository repository;

  public CenterService(CenterRepository repository) {
    this.repository = repository;
  }

  public Center createCenter(Center newCenter) {
    return repository.save(newCenter);
  }

  public Center findByCode(String code) throws Exception {
    Optional<Center> center = repository.findById(code);
    if (center.isEmpty()) {
      throw new Exception("Center code not found.");
    } else {
      return center.get();
    }
  }

  public List<Center> findAll() throws ResourceNotFoundException {
    List<Center> centers = repository.findAll();
    if (centers.isEmpty()) {
      throw new ResourceNotFoundException("There are no registered centers");
    } else {
      return centers;
    }
  }

  public Center replaceCenter(String code, Center center) throws Exception {
    if (repository.existsById(center.getCode())) {
      return repository.save(center);
    } else {
      throw new ResourceNotFoundException("Center code not found.");
    }
  }

  public Center updateCenter(String code, Map<String, Object> fields) throws Exception {
    Optional<Center> opt = repository.findById(code);
    if (opt.isEmpty()) {
      throw new ResourceNotFoundException("Center code not found.");
    }
    Center center = opt.get();

    fields.forEach((key, val) -> {
      Field field = ReflectionUtils.findField(Center.class, key);
      if (field == null) {
        throw new IllegalArgumentException(String.format("Tried to update non existent field %s.", key));
      }
      field.setAccessible(true);
      ReflectionUtils.setField(field, center, val);
    });

    return repository.save(center);
  }

  public void deleteByCode(String code) throws Exception {
    if (repository.existsById(code)) {
      repository.deleteById(code);
    } else {
      throw new ResourceNotFoundException("Center code not found.");
    }
  }
}
