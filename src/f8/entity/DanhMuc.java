package f8.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class DanhMuc {
	@Id
	private String DanhMucId;
	private String Ten;
	
	@OneToMany(mappedBy="danhmuc", fetch=FetchType.EAGER)
	private Collection<SanPham> sanphams;
	
	
	
	public DanhMuc() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public DanhMuc(String danhMucId, String ten, Collection<SanPham> sanphams) {
		super();
		DanhMucId = danhMucId;
		Ten = ten;
		this.sanphams = sanphams;
	}


	public String getDanhMucId() {
		return DanhMucId;
	}

	public void setDanhMucId(String danhMucId) {
		DanhMucId = danhMucId;
	}

	public Collection<SanPham> getSanphams() {
		return sanphams;
	}
	
	public void setSanphams(Collection<SanPham> sanphams) {
		this.sanphams = sanphams;
	}
	public String getTen() {
		return Ten;
	}
	public void setTen(String ten) {
		Ten = ten;
	}
	
	
}
