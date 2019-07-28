package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.SitesDao;
import model.entities.Sites;

public class SitesDaoJDBC implements SitesDao {

	private Connection conn;
	
	public SitesDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Sites findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM sitesandpassword WHERE Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Sites obj = new Sites();
				obj.setId(rs.getInt("id"));
				obj.setUserLogin(rs.getString("userLogin"));
				obj.setPassword(rs.getString("password"));
				obj.setSite(rs.getString("site"));
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Sites> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM sitesandpassword ORDER BY site");
			rs = st.executeQuery();

			List<Sites> list = new ArrayList<>();

			while (rs.next()) {
				Sites obj = new Sites();
				obj.setId(rs.getInt("id"));
				obj.setUserLogin(rs.getString("userLogin"));
				obj.setPassword(rs.getString("password"));
				obj.setSite(rs.getString("site"));
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void insert(Sites obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO sitesandpassword " +
				"(userLogin, password, site) " +
				"VALUES " +
				"(?,?,?)", 
				Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getUserLogin());
			st.setString(2, obj.getPassword());
			st.setString(3, obj.getSite());

			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Sites obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE sitesandpassword " +
				"SET userLogin = ? ,password = ?, site = ? " +
				"WHERE id = ?");

			st.setString(1, obj.getUserLogin());
			st.setString(2, obj.getPassword());
			st.setString(3, obj.getSite());
			st.setInt(4, obj.getId());

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM sitesandpassword WHERE Id = ?");

			st.setInt(1, id);

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}
}
