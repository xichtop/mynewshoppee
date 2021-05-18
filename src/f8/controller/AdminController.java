package f8.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import f8.bean.Mailer;
import f8.entity.DanhMuc;
import f8.entity.GioHang;
import f8.entity.SanPham;
import f8.entity.Users;

@Transactional
@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	SessionFactory factory1;
	Session session1;
	String hql1;
	Query query1;
	Transaction t1;
	static Users UserAdmin;
	@RequestMapping("/index")
	public String index(ModelMap model){
		model.addAttribute("User", UserAdmin);
		return "admin/index";
	}
	@RequestMapping("/listsanpham")
	public String listsanpham(ModelMap model){
		model.addAttribute("User", UserAdmin);
		session1 = factory1.getCurrentSession();
		hql1 = "FROM SanPham";
		query1 = session1.createQuery(hql1);
		List<SanPham> sanphams = query1.list();
		model.addAttribute("sanphams", sanphams);
		return "admin/dssanpham";
	}
	
	@RequestMapping(value = "themsanpham", method = RequestMethod.GET)
	public String themsanpham(ModelMap model){
		model.addAttribute("User", UserAdmin);
		SanPham s = new SanPham();
		model.addAttribute("sanpham", s);
		return "admin/themsanpham";
	}
	
	@RequestMapping(value = "themsanpham", method = RequestMethod.POST, params = "btnTroLai")
	public String trolaithem(){
		return "redirect:/admin/listsanpham.htm";
	}
	
	@RequestMapping(value = "themsanpham", method = RequestMethod.POST, params = "btnXacNhan")
	public String xacnhanthem(ModelMap model, @ModelAttribute("sanpham") SanPham s, BindingResult errors){
		if (s.getId().trim().length() == 0) {
			errors.rejectValue("IdSP", "sanpham", "Vui lòng nhập mã sản phẩm !");
		} else {
			session1 = factory1.openSession();
			hql1 = "FROM SanPham";
			query1 = session1.createQuery(hql1);
			List<SanPham> sanphams = query1.list();
			for (SanPham s1 : sanphams) {
				if (s1.getId().compareTo(s.getId()) == 0) {
					errors.rejectValue("IdSP", "sanpham", "Mã sản phẩm bị trùng");
					break;
				}
			}
		}
		
		if (s.getTen().trim().length() == 0) {
			errors.rejectValue("Ten", "sanpham", "Vui lòng nhập tên sản phẩm !");
		}
		
		if (s.getGia() == null) {
			errors.rejectValue("Gia", "sanpham", "Vui lòng nhập giá sản phẩm !");
		}
		
		if (s.getThuongHieu().trim().length() == 0) {
			errors.rejectValue("ThuongHieu", "sanpham", "Vui lòng nhập thương hiệu sản phẩm !");
		}
		
		if (s.getXuatXu().trim().length() == 0) {
			errors.rejectValue("XuatXu", "sanpham", "Vui lòng nhập xuất xứ sản phẩm !");
		}
		
		if (s.getLink().trim().length() == 0) {
			errors.rejectValue("Link", "sanpham", "Vui lòng nhập link ảnh của sản phẩm !");
		}
		
		if (s.getDanhGia() <= 0 || s.getDanhGia() > 5) {
			errors.rejectValue("DanhGia", "sanpham", "Đánh giá từ 1 đến 5 !");
		}
		
		if (s.getGiamGia() < 0) {
			errors.rejectValue("GiamGia", "sanpham", "Giảm giá sai !");
		}
		
		if (errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng sửa các lỗi sau đây !");
			return "admin/themsanpham";
		} else {
			session1 = factory1.openSession();
			t1 = session1.beginTransaction();
			try {
				session1.save(s);
				t1.commit();
				model.addAttribute("mess", "Thêm sản phẩm thành công !");
			} catch (Exception e) {
				t1.rollback();
				model.addAttribute("mess", "Thêm sản phẩm thất bại !");
			} finally {
				session1.close();
			}
			return "redirect:/admin/listsanpham.htm";
		}
	}
	
	@RequestMapping(value = "sanpham/sua/{Id}", method = RequestMethod.GET)
	public String suasanpham(ModelMap model, @PathVariable("Id") String IdSP){
		model.addAttribute("User", UserAdmin);
		session1 = factory1.getCurrentSession();
		hql1 = "FROM SanPham where IdSP= :id";
		query1 = session1.createQuery(hql1);
		query1.setParameter("id", IdSP);
		List<SanPham> sanphams = query1.list();
		model.addAttribute("sanpham", sanphams.get(0));
		return "admin/suasanpham";
	}
	
	@RequestMapping(value = "suasanpham", method = RequestMethod.POST, params = "btnTroLai")
	public String trolaisua(){
		return "redirect:/admin/listsanpham.htm";
	}
	
	@RequestMapping(value = "suasanpham", method = RequestMethod.POST, params = "btnXacNhan")
	public String xacnhansua(ModelMap model, @ModelAttribute("sanpham") SanPham s, BindingResult errors){
		
		if (s.getTen().trim().length() == 0) {
			errors.rejectValue("Ten", "sanpham", "Vui lòng nhập tên sản phẩm !");
		}
		
		if (s.getGia() == null) {
			errors.rejectValue("Gia", "sanpham", "Vui lòng nhập giá sản phẩm !");
		}
		
		if (s.getThuongHieu().trim().length() == 0) {
			errors.rejectValue("ThuongHieu", "sanpham", "Vui lòng nhập thương hiệu sản phẩm !");
		}
		
		if (s.getXuatXu().trim().length() == 0) {
			errors.rejectValue("XuatXu", "sanpham", "Vui lòng nhập xuất xứ sản phẩm !");
		}
		
		if (s.getLink().trim().length() == 0) {
			errors.rejectValue("Link", "sanpham", "Vui lòng nhập link ảnh của sản phẩm !");
		}
		
		if (s.getDanhGia() <= 0 || s.getDanhGia() > 5) {
			errors.rejectValue("DanhGia", "sanpham", "Đánh giá từ 1 đến 5 !");
		}
		
		if (s.getGiamGia() <= 0) {
			errors.rejectValue("GiamGia", "sanpham", "Giảm giá sai !");
		}
		
		if (errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng sửa các lỗi sau đây !");
			return "admin/themsanpham";
		} else {
			session1 = factory1.openSession();
			t1 = session1.beginTransaction();
			try {
				session1.update(s);
				t1.commit();
				model.addAttribute("mess", "Sửa sản phẩm thành công !");
			} catch (Exception e) {
				t1.rollback();
				model.addAttribute("mess", "Sửa sản phẩm thất bại !" + e);
			} finally {
				session1.close();
			}
			return "redirect:/admin/listsanpham.htm";
		}
	}
	
	@RequestMapping(value = "/sanpham/xoa/{Id}")
	public String xoasanpham(@PathVariable("Id") String IdSP, ModelMap model){
		session1 = factory1.getCurrentSession();
		hql1 = "FROM GioHang";
		query1 = session1.createQuery(hql1);
		List<GioHang> giohangs = query1.list();
		boolean check = false;
		for(GioHang g : giohangs){
			if(g.getSanpham().getId().trim().equals(IdSP) == true){
				check = true;
				break;
			}
		}
		if(check){
			model.addAttribute("mess", "Sản phẩm đang trong giỏ hàng");
			return "redirect:/admin/listsanpham.htm";
		}
		else{
			session1 = factory1.openSession();
			hql1 = "FROM SanPham where IdSP= :id";
			query1 = session1.createQuery(hql1);
			query1.setParameter("id", IdSP);
			List<SanPham> sanphams = query1.list();
			t1 = session1.beginTransaction();
			try {
				session1.delete(sanphams.get(0));
				t1.commit();
				model.addAttribute("mess", "Xóa sản phẩm thành công!");
			} catch (Exception e) {
				t1.rollback();
				model.addAttribute("mess", "Xóa sản phẩm thất bại!");
			} finally {
				session1.close();
			}	
			return "redirect:/admin/listsanpham.htm";
		}
	}
	
	@RequestMapping("/listuser")
	public String listuser(ModelMap model){
		model.addAttribute("User", UserAdmin);
		session1 = factory1.openSession();
		hql1 = "FROM Users WHERE Admin = 'false'";
		query1 = session1.createQuery(hql1);
		List<DanhMuc> users = query1.list();
		model.addAttribute("users", users);
		return "admin/dsuser";
	}
	
	@RequestMapping("/listadmin")
	public String listadmin(ModelMap model){
		model.addAttribute("User", UserAdmin);
		session1 = factory1.openSession();
		hql1 = "FROM Users WHERE Admin = 'true'";
		query1 = session1.createQuery(hql1);
		List<DanhMuc> users = query1.list();
		model.addAttribute("users", users);
		return "admin/dsadmin";
	}
	
	@RequestMapping(value = "/user/xoa/{Id}")
	public String xoauser(@PathVariable("Id") String UserName, ModelMap model){
		
			session1 = factory1.openSession();
			hql1 = "FROM Users where UserName = :id";
			query1 = session1.createQuery(hql1);
			query1.setParameter("id", UserName);
			List<Users> users = query1.list();
			t1 = session1.beginTransaction();
			try {
				session1.delete(users.get(0));
				t1.commit();
				model.addAttribute("mess", "Xóa user thành công !");
			} catch (Exception e) {
				t1.rollback();
				model.addAttribute("mess", "Xóa user thất bại !" + e);
			} finally {
				session1.close();
			}	
			return "redirect:/admin/listuser.htm";
		}
	
	
	@RequestMapping(value = "dangki", method = RequestMethod.GET)
	public String dangki(ModelMap model) {
		Users u = new Users();
		model.addAttribute("userdangki", u);
		model.addAttribute("errorImg", "");
		model.addAttribute("errorPassAgian", "");
		return "admin/taotaikhoan";
	}
	
	@Autowired
	ServletContext context;
	Mailer mailer1;
	@RequestMapping(value = "dangki", method = RequestMethod.POST, params = "btnDangKi")
	public String dangki(ModelMap model, @ModelAttribute("userdangki") Users u, @RequestParam("PassAgain") String passAgain,
			@RequestParam("Img") MultipartFile img, BindingResult errors) {
		String errorpass = "", errorimg = "", photoPath = "";
		if (u.getUserName().trim().length() == 0) {
			errors.rejectValue("UserName", "userdangki", "Vui lòng nhập tên đăng nhập !");
		} else {
			session1 = factory1.openSession();
			hql1 = "FROM Users";
			query1 = session1.createQuery(hql1);
			List<Users> users = query1.list();
			for (Users u1 : users) {
				if (u1.getUserName().trim().equals(u.getUserName()) == true) {
					errors.rejectValue("UserName", "userdangki", "Tên đăng nhập đã tồn tại !");
					break;
				}
			}
		}

		if (u.getPass().trim().length() == 0) {
			errors.rejectValue("Pass", "userdangki", "Vui lòng nhập mật khẩu !");
		} else if (u.getPass().trim().length() < 6) {
			errors.rejectValue("Pass", "userdangki", "Mật khẩu phải lớn hơn 6 kí tự !");
		}

		if (passAgain.trim().length() == 0) {
			errorpass = "Vui lòng nhập lại mật khẩu !";
			model.addAttribute("errorPassAgian", errorpass);
		} else if (passAgain.equals(u.getPass().trim()) == false) {
			errorpass = "Mật khẩu không trùng !";
			model.addAttribute("errorPassAgian", errorpass);
		}
		if (u.getHo().trim().length() == 0) {
			errors.rejectValue("Ho", "userdangki", "Vui lòng nhập họ !");
		}
		if (u.getTen().trim().length() == 0) {
			errors.rejectValue("Ten", "userdangki", "Vui lòng nhập tên !");
		}
		if (u.getGioiTinh() == null) {
			errors.rejectValue("GioiTinh", "userdangki", "Vui lòng chọn giới tính !");
		}
		if (u.getNgaySinh() == null) {
			errors.rejectValue("NgaySinh", "userdangki", "Vui lòng nhập ngày sinh !");
		}

		if (u.getEmail().trim().length() == 0) {
			errors.rejectValue("Email", "userdangki", "Vui lòng nhập Email !");
		}

		if (u.getSDT().trim().length() == 0) {
			errors.rejectValue("SDT", "userdangki", "Vui lòng nhập số điện thoại !");
		}
		if (u.getDiaChi().trim().length() == 0) {
			errors.rejectValue("DiaChi", "userdangki", "Vui lòng nhập địa chỉ !");
		}

		if (img.isEmpty()) {
			errorimg = "Vui lòng chọn file !";
			model.addAttribute("errorImg", errorimg);
		} else {
			try {
				photoPath = context.getRealPath("/assets/img/" + img.getOriginalFilename());
				img.transferTo(new File(photoPath));
				photoPath = "assets/img/" + img.getOriginalFilename();
			} catch (Exception e) {
				errorimg = "Lỗi lưu file !";
				model.addAttribute("errorImg", errorimg);
			}
		}

		if (errors.hasErrors() || errorimg.equals("") == false || errorpass.equals("") == false) {
			model.addAttribute("message", "Vui lòng sửa các lỗi sau đây !");
			return "admin/dangki";
		} else {
			
			boolean check = false;
			String subject = "Tạo tài khoản thành công";
			String EmailWeb = "xichtop99@gmail.com";
			String body = "Tài khoản Admin: " + u.getDiaChi() + " " + u.getTen() 
						+ "được tạo thành công! \n"
						+ "Trân trọng!";
			try {
				// Gửi mail
				mailer1.send(EmailWeb, u.getEmail().trim(), subject, body);
				model.addAttribute("mess", "Tạo tài khoản admin thành công !");
				check = true;
			} catch (Exception ex) {
				model.addAttribute("mess", "Tạo tài khoản admin thất bại !" + ex);
				check = false;
			}
			
			if(check){
				session1 = factory1.openSession();
				t1 = session1.beginTransaction();
				u.setLinkAnh(photoPath);
				u.setAdmin(true);
				try {
					session1.save(u);
					t1.commit();
					model.addAttribute("mess", "Đăng kí thành công !");
				} catch (Exception e) {
					t1.rollback();
					model.addAttribute("mess", "Đăng kí thất bại !");
				} finally {
					session1.close();
				}
				return "redirect:/admin/index.htm";
			}
			else{
				return "redirect:/admin/index.htm";
			}
			
			
		}
	}
	
	@RequestMapping(value = "dangki", method = RequestMethod.POST, params = "btnTroLai")
	public String trolaidangki() {
		return "redirect:/admin/index.htm";
	}
	
	@RequestMapping("dangxuat")
	public String dangxuat(){
		HomeController.UserDangNhap.setUserName("0");
		return "redirect:/home/index.htm";
	}
	
	@ModelAttribute("genders")
	public Map<Boolean, String> getGender() {
		Map<Boolean, String> genders = new HashMap<>();
		genders.put(false, "Nữ");
		genders.put(true, "Nam");
		return genders;
	}
	
	@ModelAttribute("danhmucs")
	public List<DanhMuc> getDanhMuc(){
		session1 = factory1.openSession();
		hql1 = "FROM DanhMuc";
		query1 = session1.createQuery(hql1);
		List<DanhMuc> danhmucs = query1.list();
		return danhmucs;
	}
}
