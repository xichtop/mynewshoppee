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
			errors.rejectValue("IdSP", "sanpham", "Vui l??ng nh???p m?? s???n ph???m !");
		} else {
			session1 = factory1.openSession();
			hql1 = "FROM SanPham";
			query1 = session1.createQuery(hql1);
			List<SanPham> sanphams = query1.list();
			for (SanPham s1 : sanphams) {
				if (s1.getId().compareTo(s.getId()) == 0) {
					errors.rejectValue("IdSP", "sanpham", "M?? s???n ph???m b??? tr??ng");
					break;
				}
			}
		}
		
		if (s.getTen().trim().length() == 0) {
			errors.rejectValue("Ten", "sanpham", "Vui l??ng nh???p t??n s???n ph???m !");
		}
		
		if (s.getGia() == null) {
			errors.rejectValue("Gia", "sanpham", "Vui l??ng nh???p gi?? s???n ph???m !");
		}
		
		if (s.getThuongHieu().trim().length() == 0) {
			errors.rejectValue("ThuongHieu", "sanpham", "Vui l??ng nh???p th????ng hi???u s???n ph???m !");
		}
		
		if (s.getXuatXu().trim().length() == 0) {
			errors.rejectValue("XuatXu", "sanpham", "Vui l??ng nh???p xu???t x??? s???n ph???m !");
		}
		
		if (s.getLink().trim().length() == 0) {
			errors.rejectValue("Link", "sanpham", "Vui l??ng nh???p link ???nh c???a s???n ph???m !");
		}
		
		if (s.getDanhGia() <= 0 || s.getDanhGia() > 5) {
			errors.rejectValue("DanhGia", "sanpham", "????nh gi?? t??? 1 ?????n 5 !");
		}
		
		if (s.getGiamGia() < 0) {
			errors.rejectValue("GiamGia", "sanpham", "Gi???m gi?? sai !");
		}
		
		if (errors.hasErrors()) {
			model.addAttribute("message", "Vui l??ng s???a c??c l???i sau ????y !");
			return "admin/themsanpham";
		} else {
			session1 = factory1.openSession();
			t1 = session1.beginTransaction();
			try {
				session1.save(s);
				t1.commit();
				model.addAttribute("mess", "Th??m s???n ph???m th??nh c??ng !");
			} catch (Exception e) {
				t1.rollback();
				model.addAttribute("mess", "Th??m s???n ph???m th???t b???i !");
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
			errors.rejectValue("Ten", "sanpham", "Vui l??ng nh???p t??n s???n ph???m !");
		}
		
		if (s.getGia() == null) {
			errors.rejectValue("Gia", "sanpham", "Vui l??ng nh???p gi?? s???n ph???m !");
		}
		
		if (s.getThuongHieu().trim().length() == 0) {
			errors.rejectValue("ThuongHieu", "sanpham", "Vui l??ng nh???p th????ng hi???u s???n ph???m !");
		}
		
		if (s.getXuatXu().trim().length() == 0) {
			errors.rejectValue("XuatXu", "sanpham", "Vui l??ng nh???p xu???t x??? s???n ph???m !");
		}
		
		if (s.getLink().trim().length() == 0) {
			errors.rejectValue("Link", "sanpham", "Vui l??ng nh???p link ???nh c???a s???n ph???m !");
		}
		
		if (s.getDanhGia() <= 0 || s.getDanhGia() > 5) {
			errors.rejectValue("DanhGia", "sanpham", "????nh gi?? t??? 1 ?????n 5 !");
		}
		
		if (s.getGiamGia() <= 0) {
			errors.rejectValue("GiamGia", "sanpham", "Gi???m gi?? sai !");
		}
		
		if (errors.hasErrors()) {
			model.addAttribute("message", "Vui l??ng s???a c??c l???i sau ????y !");
			return "admin/themsanpham";
		} else {
			session1 = factory1.openSession();
			t1 = session1.beginTransaction();
			try {
				session1.update(s);
				t1.commit();
				model.addAttribute("mess", "S???a s???n ph???m th??nh c??ng !");
			} catch (Exception e) {
				t1.rollback();
				model.addAttribute("mess", "S???a s???n ph???m th???t b???i !" + e);
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
			model.addAttribute("mess", "S???n ph???m ??ang trong gi??? h??ng");
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
				model.addAttribute("mess", "X??a s???n ph???m th??nh c??ng!");
			} catch (Exception e) {
				t1.rollback();
				model.addAttribute("mess", "X??a s???n ph???m th???t b???i!");
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
				model.addAttribute("mess", "X??a user th??nh c??ng !");
			} catch (Exception e) {
				t1.rollback();
				model.addAttribute("mess", "X??a user th???t b???i !" + e);
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
			errors.rejectValue("UserName", "userdangki", "Vui l??ng nh???p t??n ????ng nh???p !");
		} else {
			session1 = factory1.openSession();
			hql1 = "FROM Users";
			query1 = session1.createQuery(hql1);
			List<Users> users = query1.list();
			for (Users u1 : users) {
				if (u1.getUserName().trim().equals(u.getUserName()) == true) {
					errors.rejectValue("UserName", "userdangki", "T??n ????ng nh???p ???? t???n t???i !");
					break;
				}
			}
		}

		if (u.getPass().trim().length() == 0) {
			errors.rejectValue("Pass", "userdangki", "Vui l??ng nh???p m???t kh???u !");
		} else if (u.getPass().trim().length() < 6) {
			errors.rejectValue("Pass", "userdangki", "M???t kh???u ph???i l???n h??n 6 k?? t??? !");
		}

		if (passAgain.trim().length() == 0) {
			errorpass = "Vui l??ng nh???p l???i m???t kh???u !";
			model.addAttribute("errorPassAgian", errorpass);
		} else if (passAgain.equals(u.getPass().trim()) == false) {
			errorpass = "M???t kh???u kh??ng tr??ng !";
			model.addAttribute("errorPassAgian", errorpass);
		}
		if (u.getHo().trim().length() == 0) {
			errors.rejectValue("Ho", "userdangki", "Vui l??ng nh???p h??? !");
		}
		if (u.getTen().trim().length() == 0) {
			errors.rejectValue("Ten", "userdangki", "Vui l??ng nh???p t??n !");
		}
		if (u.getGioiTinh() == null) {
			errors.rejectValue("GioiTinh", "userdangki", "Vui l??ng ch???n gi???i t??nh !");
		}
		if (u.getNgaySinh() == null) {
			errors.rejectValue("NgaySinh", "userdangki", "Vui l??ng nh???p ng??y sinh !");
		}

		if (u.getEmail().trim().length() == 0) {
			errors.rejectValue("Email", "userdangki", "Vui l??ng nh???p Email !");
		}

		if (u.getSDT().trim().length() == 0) {
			errors.rejectValue("SDT", "userdangki", "Vui l??ng nh???p s??? ??i???n tho???i !");
		}
		if (u.getDiaChi().trim().length() == 0) {
			errors.rejectValue("DiaChi", "userdangki", "Vui l??ng nh???p ?????a ch??? !");
		}

		if (img.isEmpty()) {
			errorimg = "Vui l??ng ch???n file !";
			model.addAttribute("errorImg", errorimg);
		} else {
			try {
				photoPath = context.getRealPath("/assets/img/" + img.getOriginalFilename());
				img.transferTo(new File(photoPath));
				photoPath = "assets/img/" + img.getOriginalFilename();
			} catch (Exception e) {
				errorimg = "L???i l??u file !";
				model.addAttribute("errorImg", errorimg);
			}
		}

		if (errors.hasErrors() || errorimg.equals("") == false || errorpass.equals("") == false) {
			model.addAttribute("message", "Vui l??ng s???a c??c l???i sau ????y !");
			return "admin/dangki";
		} else {
			
			boolean check = false;
			String subject = "T???o t??i kho???n th??nh c??ng";
			String EmailWeb = "xichtop99@gmail.com";
			String body = "T??i kho???n Admin: " + u.getDiaChi() + " " + u.getTen() 
						+ "???????c t???o th??nh c??ng! \n"
						+ "Tr??n tr???ng!";
			try {
				// G???i mail
				mailer1.send(EmailWeb, u.getEmail().trim(), subject, body);
				model.addAttribute("mess", "T???o t??i kho???n admin th??nh c??ng !");
				check = true;
			} catch (Exception ex) {
				model.addAttribute("mess", "T???o t??i kho???n admin th???t b???i !" + ex);
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
					model.addAttribute("mess", "????ng k?? th??nh c??ng !");
				} catch (Exception e) {
					t1.rollback();
					model.addAttribute("mess", "????ng k?? th???t b???i !");
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
		genders.put(false, "N???");
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
