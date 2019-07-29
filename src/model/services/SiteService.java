package model.services;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SitesDao;
import model.entities.Sites;

public class SiteService {
	
	private SitesDao dao = DaoFactory.createSitesDao();
	
	public List<Sites> findAll(){
		
		return dao.findAll();
//		List<Sites> list = new ArrayList<>();
//		list.add(new Sites(1,"camisetaecia@live.com","gr007879","gitHub" ));
//		list.add(new Sites(2,"gilservicosweb@gmail.com","gr007879rg","gmailGil" ));
//		list.add(new Sites(3,"gilservicosweb@gmail.com","gr007879rg","oracle" ));
//		return list;
	}
	
	public Sites findById(Integer id) {
		return dao.findById(id);
	}
	
	public Sites findByName(String name) {
		return dao.findByName(name);
	}
	
	public void saveOrUpdate(Sites obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	
	public void remove(Sites obj) {
		dao.deleteById(obj.getId());
	}
	
}//class end
