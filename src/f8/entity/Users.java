package f8.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Users {
	@Id
	private String UserName;
	private String Pass;
	private String Ho;
	private String Ten;
	private String Email;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date NgaySinh;
	private String DiaChi;
	private Boolean GioiTinh; 
	private String LinkAnh;
	private String SDT;
	private Boolean Admin;
	
	@OneToMany(mappedBy="userGH", fetch=FetchType.EAGER)
	private Collection<GioHang> giohangs;

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
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

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Date getNgaySinh() {
		return NgaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		NgaySinh = ngaySinh;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public Boolean getGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(Boolean gioiTinh) {
		GioiTinh = gioiTinh;
	}
	
	public String getLinkAnh() {
		return LinkAnh;
	}

	public void setLinkAnh(String linkAnh) {
		LinkAnh = linkAnh;
	}

	public Collection<GioHang> getGiohangs() {
		return giohangs;
	}

	public void setGiohangs(Collection<GioHang> giohangs) {
		this.giohangs = giohangs;
	}


	public String getSDT() {
		return SDT;
	}
	
	

	public Boolean getAdmin() {
		return Admin;
	}

	public void setAdmin(Boolean admin) {
		Admin = admin;
	}
	
	public Users(String userName, String pass, String ho, String ten, String email, Date ngaySinh, String diaChi,
			Boolean gioiTinh, String linkAnh, String sDT, Boolean admin, Collection<GioHang> giohangs) {
		super();
		UserName = userName;
		Pass = pass;
		Ho = ho;
		Ten = ten;
		Email = email;
		NgaySinh = ngaySinh;
		DiaChi = diaChi;
		GioiTinh = gioiTinh;
		LinkAnh = linkAnh;
		SDT = sDT;
		Admin = admin;
		this.giohangs = giohangs;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
