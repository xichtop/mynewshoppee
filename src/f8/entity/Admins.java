package f8.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admins {
	@Id
	private String IdAdmin;
	private String Pass;
	private String Ho;
	private String Ten;
	
	public Admins(String idAdmin, String pass, String ho, String ten) {
		super();
		IdAdmin = idAdmin;
		Pass = pass;
		Ho = ho;
		Ten = ten;
	}

	public Admins() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getIdAdmin() {
		return IdAdmin;
	}

	public void setIdAdmin(String idAdmin) {
		IdAdmin = idAdmin;
	}

	public String getPass() {
		return Pass;
	}

	public void setPass(String pass) {
		Pass = pass;
	}

	public String getHo() {
		return Ho;
	}

	public void setHo(String ho) {
		Ho = ho;
	}

	public String getTen() {
		return Ten;
	}

	public void setTen(String ten) {
		Ten = ten;
	}
	
	
}
