package f8.bean;

import java.util.Date;

import f8.entity.SanPham;

public class SanPham_SoLuong {
	private SanPham s;
	private int soluong;
	private Date ngayMua;
	public SanPham getS() {
		return s;
	}
	public void setS(SanPham s) {
		this.s = s;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	
	public Date getNgayMua() {
		return ngayMua;
	}
	public void setNgayMua(Date ngayMua) {
		this.ngayMua = ngayMua;
	}
	public SanPham_SoLuong() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SanPham_SoLuong(SanPham s, int soluong, Date ngayMua) {
		super();
		this.s = s;
		this.soluong = soluong;
		this.ngayMua = ngayMua;
	}
}
