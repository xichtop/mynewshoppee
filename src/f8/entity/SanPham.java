package f8.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class SanPham {
	@Id
	private String IdSP;
	private String Ten;
	private String Link;
	private Float Gia;
	private String ThuongHieu;
	private String XuatXu;
	private Boolean YeuThich;
	private int DanhGia;
	private int GiamGia;
	
	@ManyToOne
	@JoinColumn(name="DanhMucId")
	private DanhMuc danhmuc;
	
	@OneToMany(mappedBy="sanpham", fetch=FetchType.EAGER)
	private Collection<GioHang> giohangs;
	
	public SanPham() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SanPham(String id, String ten, String link, Float gia, String thuongHieu, String xuatXu, Boolean yeuThich,
			int danhGia, int giamGia, DanhMuc danhmuc) {
		super();
		IdSP = id;
		Ten = ten;
		Link = link;
		Gia = gia;
		ThuongHieu = thuongHieu;
		XuatXu = xuatXu;
		YeuThich = yeuThich;
		DanhGia = danhGia;
		GiamGia = giamGia;
		this.danhmuc = danhmuc;
	}

	public String getId() {
		return IdSP;
	}

	public void setId(String id) {
		IdSP = id;
	}

	public String getTen() {
		return Ten;
	}

	public void setTen(String ten) {
		Ten = ten;
	}

	public String getLink() {
		return Link;
	}

	public void setLink(String link) {
		Link = link;
	}

	public Float getGia() {
		return Gia;
	}

	public void setGia(Float gia) {
		Gia = gia;
	}

	public String getThuongHieu() {
		return ThuongHieu;
	}

	public void setThuongHieu(String thuongHieu) {
		ThuongHieu = thuongHieu;
	}

	public String getXuatXu() {
		return XuatXu;
	}

	public void setXuatXu(String xuatXu) {
		XuatXu = xuatXu;
	}

	public Boolean getYeuThich() {
		return YeuThich;
	}

	public void setYeuThich(Boolean yeuThich) {
		YeuThich = yeuThich;
	}

	public int getDanhGia() {
		return DanhGia;
	}

	public void setDanhGia(int danhGia) {
		DanhGia = danhGia;
	}

	public int getGiamGia() {
		return GiamGia;
	}

	public void setGiamGia(int giamGia) {
		GiamGia = giamGia;
	}

	public DanhMuc getDanhmuc() {
		return danhmuc;
	}

	public void setDanhmuc(DanhMuc danhmuc) {
		this.danhmuc = danhmuc;
	}
	
	
}
