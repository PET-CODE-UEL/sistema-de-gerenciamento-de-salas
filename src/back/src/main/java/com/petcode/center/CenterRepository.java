package com.petcode.center;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

public interface CenterRepository extends ListCrudRepository<Center, String> {

  Optional<Center> findByCode(String code);

  List<Center> findByName(String name);

  Object deleteByCode(String code);

}
