package model.dao;

import db.DB;
import model.dao.impl.SitesDaoJDBC;


public class DaoFactory {

	
	
	public static SitesDao createSitesDao() {
		return new SitesDaoJDBC(DB.getConnection());
	}
}
