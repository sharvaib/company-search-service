package com.spring.exercise.companysearchservice.repository;


import com.spring.exercise.companysearchservice.models.jpa.Officer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OfficerRepository extends CrudRepository<Officer, Long> {


}

