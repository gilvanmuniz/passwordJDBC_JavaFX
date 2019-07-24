package model.entities;

import java.io.Serializable;

public class Sites implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String userLogin;
	private String password;
	private String site;
	
	public Sites() {
		
	}

	public Sites(Integer id, String userLogin, String password, String site) {
		this.id = id;
		this.userLogin = userLogin;
		this.password = password;
		this.site = site;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sites other = (Sites) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sites [id=" + id + ", userLogin=" + userLogin + ", password=" + password + ", site=" + site + "]";
	}
		

}// class end
