package model.dao;

import java.util.List;

import model.entities.Sites;

public interface SitesDao {

	void insert(Sites obj);
	void update(Sites obj);
	void deleteById(Integer id);
	Sites findById(Integer id);
	Sites findByName(String name);
	List<Sites> findAll();
}
