package f8.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ThongBao {
	@Id
	private String IdThongBao;
	private String TieuDe;
	private String NoiDung;
	private String LinkAnh;
	
	public String getIdThongBao() {
		return IdThongBao;
	}
	public void setIdThongBao(String idThongBao) {
		IdThongBao = idThongBao;
	}
	public String getTieuDe() {
		return TieuDe;
	}
	public void setTieuDe(String tieuDe) {
		TieuDe = tieuDe;
	}
	public String getNoiDung() {
		return NoiDung;
	}
	public void setNoiDung(String noiDung) {
		NoiDung = noiDung;
	}
	public String getLinkAnh() {
		return LinkAnh;
	}
	public void setLinkAnh(String linkAnh) {
		LinkAnh = linkAnh;
	}
	public ThongBao(String idThongBao, String tieuDe, String noiDung, String linkAnh) {
		super();
		IdThongBao = idThongBao;
		TieuDe = tieuDe;
		NoiDung = noiDung;
		LinkAnh = linkAnh;
	}
	public ThongBao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
