package com.esense.topclass.service;

import java.util.List;

import com.esense.topclass.dbmodel.Content;
import com.esense.topclass.dbmodel.RandomCity;
import com.esense.topclass.dbmodel.User;


public interface GenericService {
    User findByUsername(String username);
    List<User> findAllUsers();
    List<RandomCity> findAllRandomCities();
	List<Content> findVersionUpdate(String versionId);
	List<Content> findAllVersionUpdate();
}
