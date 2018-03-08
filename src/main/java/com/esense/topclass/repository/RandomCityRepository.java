package com.esense.topclass.repository;

import org.springframework.data.repository.CrudRepository;

import com.esense.topclass.dbmodel.RandomCity;


public interface RandomCityRepository extends CrudRepository<RandomCity, Long> {
}
