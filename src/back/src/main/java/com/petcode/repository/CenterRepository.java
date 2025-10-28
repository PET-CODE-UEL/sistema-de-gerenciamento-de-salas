package com.petcode.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.petcode.entity.Center;

public interface CenterRepository extends ListCrudRepository<Center, String> {

  Center findByCode(String code);

  List<Center> findByName(String name);

  Object deleteByCode(String code);

}
