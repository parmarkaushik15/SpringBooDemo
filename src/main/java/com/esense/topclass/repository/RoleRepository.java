package com.esense.topclass.repository;

import org.springframework.data.repository.CrudRepository;

import com.esense.topclass.dbmodel.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
