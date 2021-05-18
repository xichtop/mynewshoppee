package f8.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class GioHang {
	@Id
	private Integer IdGH;
	
	@ManyToOne
	@JoinColumn(name="IdSP")
	private SanPham sanpham;

	@ManyToOne
	@JoinColumn(name="UserName")
	private Users userGH;
	
	private int SoLuong;
	private boolean TrangThaiMua;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date NgayMua;

	public int getSoLuong() {
		return SoLuong;
	}

	public void setSoLuong(int soLuong) {
		SoLuong = soLuong;
	}

	public SanPham getSanpham() {
		return sanpham;
	}

	public void setSanpham(SanPham sanpham) {
		this.sanpham = sanpham;
	}

	public Users getUserGH() {
		return userGH;
	}

	public void setUserGH(Users user) {
		this.userGH = user;
	}

	

	public Integer getIdGH() {
		return IdGH;
	}

	public void setIdGH(int idGH) {
		IdGH = idGH;
	}

	public boolean isTrangThaiMua() {
		return TrangThaiMua;
	}

	public void setTrangThaiMua(boolean trangThaiMua) {
		TrangThaiMua = trangThaiMua;
	}

	public Date getNgayMua() {
		return NgayMua;
	}

	public void setNgayMua(Date ngayMua) {
		NgayMua = ngayMua;
	}

	public GioHang() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GioHang(Integer idGH, SanPham sanpham, Users userGH, int soLuong, boolean trangThaiMua, Date ngayMua) {
		super();
		IdGH = idGH;
		this.sanpham = sanpham;
		this.userGH = userGH;
		SoLuong = soLuong;
		TrangThaiMua = trangThaiMua;
		NgayMua = ngayMua;
	}

}
