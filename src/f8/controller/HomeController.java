package f8.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import f8.bean.Mailer;
import f8.bean.SanPham_SoLuong;
import f8.entity.DanhMuc;
import f8.entity.GioHang;
import f8.entity.SanPham;
import f8.entity.ThongBao;
import f8.entity.Users;

@Transactional
@Controller
@RequestMapping("home")
public class HomeController {
	@Autowired
	// Mailer mailer;
	SessionFactory factory;
	Session session;
	String hql;
	Query query;
	Transaction t;
	static Users UserDangNhap = new Users("0", "", "", "", "", null, "", false, "", null, false,  null);

	@RequestMapping("/index")
	public String index(ModelMap model) {
		session = factory.getCurrentSession();
		hql = "FROM SanPham WHERE DanhMucId = 'DM1'";
		query = session.createQuery(hql);
		List<SanPham> sanphams = query.list();
		hql = "FROM SanPham";
		query = session.createQuery(hql);
		List<SanPham> sanphamalls = query.list();
		model.addAttribute("sps", sanphams);
		hql = "FROM DanhMuc";
		query = session.createQuery(hql);
		List<DanhMuc> danhmucs = query.list();
		model.addAttribute("dms", danhmucs);
		int dmInt = 0;
		model.addAttribute("dmInt", dmInt);

		int listInt = 1;
		model.addAttribute("listInt", listInt);
		int listTong = 0;
		if (sanphams.size() % 10 == 0) {
			listTong = sanphams.size() / 10;
		} else {
			listTong = sanphams.size() / 10 + 1;
		}

		model.addAttribute("listTong", listTong);

		int sapxep = 0;
		model.addAttribute("sxInt", sapxep);
		model.addAttribute("User", UserDangNhap);
		if (UserDangNhap.getUserName().equals("0") == false) {
			hql = "FROM GioHang GH WHERE GH.userGH.UserName = '" + UserDangNhap.getUserName()
					+ "' AND GH.TrangThaiMua = 'false'";
			query = session.createQuery(hql);
			List<GioHang> giohangs = query.list();
			int slgh = giohangs.size();
			model.addAttribute("slgh", slgh);
			int slct = 0;
			int i = 0;
			if (slgh != 0) {
				float tongtien = 0;
				Map<Integer, SanPham_SoLuong> ctghs = new HashMap<>();
				for (GioHang g : giohangs) {
					for (SanPham s : sanphamalls) {
						if (g.getSanpham().getId().equals(s.getId()) == true) {
							SanPham_SoLuong spsl = new SanPham_SoLuong(s, g.getSoLuong(), g.getNgayMua());
							ctghs.put(i, spsl);
							tongtien += (s.getGia() * (100 - s.getGiamGia()) / 100) * g.getSoLuong();
							break;
						}
					}
					slct += g.getSoLuong();
					i++;
				}
				model.addAttribute("slct", slct);
				model.addAttribute("ctghs", ctghs);
				model.addAttribute("tongtien", tongtien);
			}
		}
		return "home/index";
	}

	@RequestMapping(value = "search", params = "btnSearch")
	public String search(ModelMap model, @RequestParam("timkiem") String timkiem) {
		session = factory.getCurrentSession();
		hql = "FROM SanPham WHERE Ten LIKE '%" + timkiem + "%'";
		query = session.createQuery(hql);
		List<SanPham> sanphams = query.list();
		if (sanphams.size() != 0) {
			System.out.print(sanphams.get(0).getTen());
		}

		hql = "FROM SanPham";
		query = session.createQuery(hql);
		List<SanPham> sanphamalls = query.list();
		model.addAttribute("sps", sanphams);
		hql = "FROM DanhMuc";
		query = session.createQuery(hql);
		List<DanhMuc> danhmucs = query.list();
		model.addAttribute("dms", danhmucs);
		int dmInt = 0;
		model.addAttribute("dmInt", dmInt);

		int listInt = 1;
		model.addAttribute("listInt", listInt);
		int listTong = 0;
		if (sanphams.size() % 10 == 0) {
			listTong = sanphams.size() / 10;
		} else {
			listTong = sanphams.size() / 10 + 1;
		}

		model.addAttribute("listTong", listTong);

		int sapxep = 0;
		model.addAttribute("sxInt", sapxep);
		model.addAttribute("User", UserDangNhap);
		if (UserDangNhap.getUserName().equals("0") == false) {
			hql = "FROM GioHang GH WHERE GH.userGH.UserName = '" + UserDangNhap.getUserName()
					+ "' AND GH.TrangThaiMua = 'false'";
			query = session.createQuery(hql);
			List<GioHang> giohangs = query.list();
			int slgh = giohangs.size();
			model.addAttribute("slgh", slgh);
			int slct = 0;
			int i = 0;
			if (slgh != 0) {
				float tongtien = 0;
				Map<Integer, SanPham_SoLuong> ctghs = new HashMap<>();
				for (GioHang g : giohangs) {
					for (SanPham s : sanphamalls) {
						if (g.getSanpham().getId().equals(s.getId()) == true) {
							SanPham_SoLuong spsl = new SanPham_SoLuong(s, g.getSoLuong(), g.getNgayMua());
							ctghs.put(i, spsl);
							tongtien += (s.getGia() * (100 - s.getGiamGia()) / 100) * g.getSoLuong();
							break;
						}
					}
					slct += g.getSoLuong();
					i++;
				}
				model.addAttribute("slct", slct);
				model.addAttribute("ctghs", ctghs);
				model.addAttribute("tongtien", tongtien);
			}
		}
		return "home/index";
	}

	@RequestMapping(value = "/sapxep/{danhmuc}/{sapxep}/{list}")
	public String changeDanhMuc(@PathVariable("danhmuc") int dmInt, @PathVariable("sapxep") int sxInt,
			@PathVariable("list") int listInt, ModelMap model) {
		session = factory.getCurrentSession();

		hql = "FROM DanhMuc";
		query = session.createQuery(hql);
		List<DanhMuc> danhmucs = query.list();
		model.addAttribute("dms", danhmucs);
		model.addAttribute("dmInt", dmInt);
		String danhMucId = danhmucs.get(dmInt).getDanhMucId();
		hql = "FROM SanPham WHERE DanhMucId = '" + danhMucId + "'";
		query = session.createQuery(hql);
		List<SanPham> sanphams = query.list();

		hql = "FROM SanPham";
		query = session.createQuery(hql);
		List<SanPham> sanphamalls = query.list();

		if (sxInt == 1) {
			Collections.sort(sanphams, new Comparator<SanPham>() {
				@Override
				public int compare(SanPham s1, SanPham s2) {
					if ((s1.getGia() * (100 - s1.getGiamGia()) / 100) == (s2.getGia() * (100 - s2.getGiamGia()) / 100))
						return 0;
					else if ((s1.getGia() * (100 - s1.getGiamGia()) / 100) > (s2.getGia() * (100 - s2.getGiamGia())
							/ 100))
						return 1;
					else
						return -1;
				}
			});
		} else {
			Collections.sort(sanphams, new Comparator<SanPham>() {
				@Override
				public int compare(SanPham s1, SanPham s2) {
					if ((s1.getGia() * (100 - s1.getGiamGia()) / 100) == (s2.getGia() * (100 - s2.getGiamGia()) / 100))
						return 0;
					else if ((s1.getGia() * (100 - s1.getGiamGia()) / 100) < (s2.getGia() * (100 - s2.getGiamGia())
							/ 100))
						return 1;
					else
						return -1;
				}
			});
		}

		model.addAttribute("sps", sanphams);

		model.addAttribute("listInt", listInt);
		model.addAttribute("sxInt", sxInt);

		int listTong = 0;
		if (sanphams.size() % 10 == 0) {
			listTong = sanphams.size() / 10;
		} else {
			listTong = sanphams.size() / 10 + 1;
		}
		model.addAttribute("listTong", listTong);

		model.addAttribute("User", UserDangNhap);

		if (UserDangNhap.getUserName().equals("0") == false) {
			hql = "FROM GioHang GH WHERE GH.userGH.UserName = '" + UserDangNhap.getUserName()
					+ "' AND GH.TrangThaiMua = 'false'";
			query = session.createQuery(hql);
			List<GioHang> giohangs = query.list();
			int slgh = giohangs.size();
			model.addAttribute("slgh", slgh);
			int slct = 0;
			int i = 0;
			if (slgh != 0) {
				float tongtien = 0;
				Map<Integer, SanPham_SoLuong> ctghs = new HashMap<>();
				for (GioHang g : giohangs) {
					for (SanPham s : sanphamalls) {
						if (g.getSanpham().getId().equals(s.getId()) == true) {
							SanPham_SoLuong spsl = new SanPham_SoLuong(s, g.getSoLuong(), g.getNgayMua());
							ctghs.put(i, spsl);
							tongtien += (s.getGia() * (100 - s.getGiamGia()) / 100) * g.getSoLuong();
							break;
						}
					}
					slct += g.getSoLuong();
					i++;
					// System.out.print(i + " ");
				}
				model.addAttribute("slct", slct);
				model.addAttribute("ctghs", ctghs);
				model.addAttribute("tongtien", tongtien);
			}
		}
		return "home/index";
	}

	@RequestMapping(value = "dangnhap", method = RequestMethod.GET)
	public String dangnhap(ModelMap model) {
		Users u = new Users();
		model.addAttribute("User", u);
		return "form/dangnhap";
	}
	
	@RequestMapping(value = "dangnhap", method = RequestMethod.POST, params = "btnDangNhap")
	public String dangnhap(ModelMap model, @ModelAttribute("User") Users u,
			// @RequestParam("UserName") String user,
			// @RequestParam("Pass") String pass,
			BindingResult errors, HttpSession h) {
		if (u.getUserName().trim().length() == 0) {
			errors.rejectValue("UserName", "User", "Vui l??ng nh???p t??n ????ng nh???p !");
		} else {
			// System.out.print(u.getUserName() + u.getPass() + "---");
			boolean check = false;
			session = factory.openSession();
			hql = "FROM Users";
			query = session.createQuery(hql);
			List<Users> users = query.list();
			for (Users u1 : users) {
				// System.out.println(u1.getUserName().trim() +
				// u1.getPass().trim() + "---");
				if (u1.getUserName().trim().equals(u.getUserName()) == true) {
					check = true;
					break;
				}
			}
			if (check == false) {
				errors.rejectValue("UserName", "User", "T??n ????ng nh???p kh??ng t???n t???i!");
			}
		}
		boolean checkadmin = false;
		if (u.getPass().trim().length() == 0) {
			errors.rejectValue("Pass", "User", "Vui l??ng nh???p m???t kh???u !");
		} else {
			boolean check = false;
			session = factory.openSession();
			hql = "FROM Users";
			query = session.createQuery(hql);
			List<Users> users = query.list();
			for (Users u1 : users) {
				if (u1.getUserName().trim().equals(u.getUserName()) == true) {
					if (u1.getPass().trim().equals(u.getPass()) == true) {
						check = true;
						if(u1.getAdmin() == true){
							AdminController.UserAdmin = u1;
							checkadmin = true;
						}
						else{
							this.UserDangNhap = u1;
							checkadmin = false;
						}
					}
					break;
				}
			}
			if (check == false) {
				errors.rejectValue("Pass", "User", "M???t kh???u kh??ng ????ng!");
			}
		}

		if (errors.hasErrors()) {
			model.addAttribute("message", "Vui l??ng s???a c??c l???i sau ????y !");
			return "form/dangnhap";
		} else {
			if(checkadmin == true){
				h.setAttribute("admin", AdminController.UserAdmin.getUserName().trim());
				return "redirect:/admin/index.htm";
			}
			else{
				h.setAttribute("user", UserDangNhap.getUserName().trim());
				return "redirect:/home/index.htm";
			}
		}
	}

	@RequestMapping(value = "dangnhap", method = RequestMethod.POST, params = "btnTroLai")
	public String trolaidangnhap() {
		return "redirect:/home/index.htm";
	}

	@RequestMapping("/dangxuat")
	public String dangxuat() {
		this.UserDangNhap.setUserName("0");
		//AdminController.UserAdmin.setUserName("0");
		return "redirect:/home/index.htm";
	}

	@RequestMapping(value = "dangki", method = RequestMethod.GET)
	public String dangki(ModelMap model) {
		Users u = new Users();
		model.addAttribute("user", u);
		model.addAttribute("errorImg", "");
		model.addAttribute("errorPassAgian", "");
		return "form/dangki";
	}

	@RequestMapping(value = "suathongtin", method = RequestMethod.GET)
	public String suathongtin(ModelMap model) {
		model.addAttribute("user", UserDangNhap);
		model.addAttribute("errorImg", "");
		return "form/suathongtin";
	}

	

	@RequestMapping(value = "suathongtin", method = RequestMethod.POST, params = "btnXacNhan")
	public String suathongtin(ModelMap model, @ModelAttribute("user") Users u, BindingResult errors) {

		if (u.getHo().trim().length() == 0) {
			errors.rejectValue("Ho", "user", "Vui l??ng nh???p h??? !");
		}
		if (u.getTen().trim().length() == 0) {
			errors.rejectValue("Ten", "user", "Vui l??ng nh???p t??n !");
		}
		if (u.getGioiTinh() == null) {
			errors.rejectValue("GioiTinh", "user", "Vui l??ng ch???n gi???i t??nh !");
		}
		if (u.getNgaySinh() == null) {
			errors.rejectValue("NgaySinh", "user", "Vui l??ng nh???p ng??y sinh !");
		}

		if (u.getEmail().trim().length() == 0) {
			errors.rejectValue("Email", "user", "Vui l??ng nh???p Email !");
		}

		if (u.getSDT().trim().length() == 0) {
			errors.rejectValue("SDT", "user", "Vui l??ng nh???p s??? ??i???n tho???i !");
		}
		if (u.getDiaChi().trim().length() == 0) {
			errors.rejectValue("DiaChi", "user", "Vui l??ng nh???p ?????a ch??? !");
		}
		if (errors.hasErrors()) {
			model.addAttribute("message", "Vui l??ng s???a c??c l???i sau ????y !");
			return "form/suathongtin";
		} else {
			session = factory.openSession();
			t = session.beginTransaction();
			u.setPass(UserDangNhap.getPass());
			u.setAdmin(false);
			u.setLinkAnh(UserDangNhap.getLinkAnh());
			try {
				session.update(u);
				t.commit();
				model.addAttribute("mess", "S???a th??nh c??ng !");
			} catch (Exception e) {
				t.rollback();
				model.addAttribute("mess", "S???a th???t b???i !");
			} finally {
				session.close();
			}
			return "redirect:/home/index.htm";
		}
	}

	@RequestMapping(value = "suathongtin", method = RequestMethod.POST, params = "btnTroLai")
	public String trolaisuathongtin() {
		return "redirect:/home/index.htm";
	}

	@RequestMapping(value = "dangki", method = RequestMethod.POST, params = "btnTroLai")
	public String trolaidangki() {
		return "redirect:/home/index.htm";
	}

	@RequestMapping(value = "/sanpham/{Idsp}")
	public String chitietsanpham(@PathVariable("Idsp") String IdSP, ModelMap model) {

		session = factory.openSession();
		hql = "FROM SanPham";
		query = session.createQuery(hql);
		List<SanPham> sanphams = query.list();
		model.addAttribute("User", UserDangNhap);
		if (UserDangNhap.getUserName().equals("0") == false) {
			hql = "FROM GioHang GH WHERE GH.userGH.UserName = '" + UserDangNhap.getUserName()
					+ "' AND GH.TrangThaiMua = 'false'";
			query = session.createQuery(hql);
			List<GioHang> giohangs = query.list();
			int slgh = giohangs.size();
			model.addAttribute("slgh", slgh);
			int slct = 0;
			int i = 0;
			if (slgh != 0) {
				float tongtien = 0;
				Map<Integer, SanPham_SoLuong> ctghs = new HashMap<>();
				for (GioHang g : giohangs) {
					for (SanPham s : sanphams) {
						if (g.getSanpham().getId().equals(s.getId()) == true) {
							SanPham_SoLuong spsl = new SanPham_SoLuong(s, g.getSoLuong(), g.getNgayMua());
							ctghs.put(i, spsl);
							tongtien += (s.getGia() * (100 - s.getGiamGia()) / 100) * g.getSoLuong();
							break;
						}
					}
					slct += g.getSoLuong();
					i++;
				}
				model.addAttribute("slct", slct);
				model.addAttribute("ctghs", ctghs);
				model.addAttribute("tongtien", tongtien);
			}
		}
		for (SanPham s : sanphams) {
			if (s.getId().trim().equals(IdSP) == true) {
				model.addAttribute("sanpham", s);
				break;
			}
		}
		int slIcon = 1;
		model.addAttribute("soluongicon", slIcon);
		return "form/chitietsp";
	}

	@RequestMapping(value = "/sanpham/{Idsp}/{soluong}/{btn}")
	public String chitietsanphamtanggiamsoluong(@PathVariable("Idsp") String IdSP, @PathVariable("soluong") int soluong,
			@PathVariable("btn") int btn, ModelMap model) {

		session = factory.openSession();
		hql = "FROM SanPham";
		query = session.createQuery(hql);
		List<SanPham> sanphams = query.list();
		model.addAttribute("User", UserDangNhap);
		if (UserDangNhap.getUserName().equals("0") == false) {
			hql = "FROM GioHang GH WHERE GH.userGH.UserName = '" + UserDangNhap.getUserName()
					+ "' AND GH.TrangThaiMua = 'false'";
			query = session.createQuery(hql);
			List<GioHang> giohangs = query.list();
			int slgh = giohangs.size();
			model.addAttribute("slgh", slgh);
			int slct = 0;
			int i = 0;
			if (slgh != 0) {
				float tongtien = 0;
				Map<Integer, SanPham_SoLuong> ctghs = new HashMap<>();
				for (GioHang g : giohangs) {
					for (SanPham s : sanphams) {
						if (g.getSanpham().getId().equals(s.getId()) == true) {
							SanPham_SoLuong spsl = new SanPham_SoLuong(s, g.getSoLuong(), g.getNgayMua());
							ctghs.put(i, spsl);
							tongtien += (s.getGia() * (100 - s.getGiamGia()) / 100) * g.getSoLuong();
							break;
						}
					}
					slct += g.getSoLuong();
					i++;
				}
				model.addAttribute("slct", slct);
				model.addAttribute("ctghs", ctghs);
				model.addAttribute("tongtien", tongtien);
			}
		}
		for (SanPham s : sanphams) {
			if (s.getId().trim().equals(IdSP) == true) {
				model.addAttribute("sanpham", s);
				break;
			}
		}
		if (btn == 1) {
			model.addAttribute("soluongicon", soluong - 1);
		} else {
			model.addAttribute("soluongicon", soluong + 1);
		}
		return "form/chitietsp";
	}

	@RequestMapping(value = "/sanpham/{Idsp}/{soluong}/{Username}/themgiohang")
	public String chitietsanphamthemgiohang(@PathVariable("Idsp") String IdSP, @PathVariable("soluong") int soluong,
			@PathVariable("Username") String UserName, ModelMap model) {
		if (UserDangNhap.getUserName().equals("0") == true) {
			return "redirect:/home/dangnhap.htm";
		} else {
			session = factory.openSession();
			hql = "FROM GioHang GH WHERE GH.userGH.UserName = '" + UserDangNhap.getUserName()
					+ "' AND GH.TrangThaiMua = 'false'";
			query = session.createQuery(hql);
			List<GioHang> giohangs = query.list();
			hql = "FROM SanPham";
			query = session.createQuery(hql);
			List<SanPham> sanphams = query.list();
			boolean check = false;
			GioHang gh = new GioHang();
			for (GioHang g : giohangs) {
				if (g.getUserGH().getUserName().trim().equals(UserName) == true
						&& g.getSanpham().getId().trim().equals(IdSP) == true) {
					gh.setIdGH(g.getIdGH());
					gh.setSanpham(g.getSanpham());
					gh.setUserGH(g.getUserGH());
					gh.setSoLuong(g.getSoLuong() + soluong);
					gh.setTrangThaiMua(false);
					session = factory.openSession();
					t = session.beginTransaction();
					try {
						session.update(gh);
						t.commit();
						model.addAttribute("mess", "Th??m th??nh c??ng !");
						check = true;
					} catch (Exception e) {
						t.rollback();
						model.addAttribute("mess", "Th??m th???t b???i !");
					} finally {
						session.close();
					}
					break;
				}
			}
			if (check == false) {
				session = factory.openSession();
				hql = "FROM Users";
				query = session.createQuery(hql);
				List<Users> users = query.list();
				SanPham sp = new SanPham();
				Users user = new Users();
				for (SanPham s : sanphams) {
					if (s.getId().trim().equals(IdSP) == true) {
						sp = s;
						break;
					}
				}
				for (Users u : users) {
					if (u.getUserName().trim().equals(UserName) == true) {
						user = u;
						break;
					}
				}
				int maxId = 0;
				session = factory.openSession();
				hql = "FROM GioHang";
				query = session.createQuery(hql);
				List<GioHang> giohangalls = query.list();
				for (GioHang g : giohangalls) {
					if (g.getIdGH() > maxId) {
						maxId = g.getIdGH();
					}
				}
				gh.setIdGH(maxId + 1);
				gh.setSanpham(sp);
				gh.setUserGH(user);
				gh.setSoLuong(soluong);
				gh.setTrangThaiMua(false);
				t = session.beginTransaction();
				try {
					session.save(gh);
					t.commit();
					System.out.print("Th??nh c??ng");
					model.addAttribute("mess", "Th??m th??nh c??ng !");
				} catch (Exception e) {
					t.rollback();
					System.out.print("Th???t b???i");
					model.addAttribute("mess", "Th??m th???t b???i !");
				} finally {
					session.close();
				}
			}

			model.addAttribute("User", UserDangNhap);
			// if(UserDangNhap.getUserName().equals("") == false){
			session = factory.openSession();
			hql = "FROM GioHang GH WHERE GH.userGH.UserName = '" + UserDangNhap.getUserName()
					+ "' AND GH.TrangThaiMua = 'false'";
			query = session.createQuery(hql);
			List<GioHang> giohangafters = query.list();
			int slgh = giohangafters.size();
			model.addAttribute("slgh", slgh);
			int slct = 0;
			int i = 0;
			if (slgh != 0) {
				float tongtien = 0;
				Map<Integer, SanPham_SoLuong> ctghs = new HashMap<>();
				for (GioHang g : giohangafters) {
					for (SanPham s : sanphams) {
						if (g.getSanpham().getId().equals(s.getId()) == true) {
							SanPham_SoLuong spsl = new SanPham_SoLuong(s, g.getSoLuong(), g.getNgayMua());
							ctghs.put(i, spsl);
							tongtien += (s.getGia() * (100 - s.getGiamGia()) / 100) * g.getSoLuong();
							break;
						}
					}
					slct += g.getSoLuong();
					i++;
				}
				model.addAttribute("slct", slct);
				model.addAttribute("ctghs", ctghs);
				model.addAttribute("tongtien", tongtien);
			}
			// }
			for (SanPham s : sanphams) {
				if (s.getId().trim().equals(IdSP) == true) {
					model.addAttribute("sanpham", s);
					break;
				}
			}
			int slic = 1;
			model.addAttribute("soluongicon", slic);
			return "form/chitietsp";
		}

	}

	@RequestMapping(value = "/sanpham/{Username}/xemgiohang")
	public String chitietsanphamxemgiohang(@PathVariable("Username") String UserName, ModelMap model) {
		if (UserDangNhap.getUserName().equals("0") == true) {
			return "redirect:/home/dangnhap.htm";
		} else {
			session = factory.openSession();
			hql = "FROM GioHang GH WHERE GH.userGH.UserName = '" + UserDangNhap.getUserName()
					+ "' AND GH.TrangThaiMua = 'false'";
			query = session.createQuery(hql);
			List<GioHang> giohangs = query.list();
			hql = "FROM SanPham";
			query = session.createQuery(hql);
			List<SanPham> sanphams = query.list();
			model.addAttribute("User", UserDangNhap);

			int slgh = giohangs.size();
			model.addAttribute("slgh", slgh);
			int slct = 0;
			if (slgh != 0) {
				float tongtien = 0;
				Map<Integer, SanPham_SoLuong> ctghs = new HashMap<>();
				for (GioHang g : giohangs) {
					for (SanPham s : sanphams) {
						if (g.getSanpham().getId().equals(s.getId()) == true) {
							SanPham_SoLuong spsl = new SanPham_SoLuong(s, g.getSoLuong(), g.getNgayMua());
							ctghs.put(g.getIdGH(), spsl);
							tongtien += (s.getGia() * (100 - s.getGiamGia()) / 100) * g.getSoLuong();
							break;
						}
					}
					slct += g.getSoLuong();
				}
				model.addAttribute("slct", slct);
				model.addAttribute("ctghs", ctghs);
				model.addAttribute("tongtien", tongtien);
			}
			return "form/giohang";
		}

	}

	@RequestMapping(value = "lichsu")
	public String lichsu(ModelMap model) {
		if (UserDangNhap.getUserName().equals("0") == true) {
			return "redirect:/home/dangnhap.htm";
		} else {
			session = factory.openSession();
			hql = "FROM GioHang GH WHERE GH.userGH.UserName = '" + UserDangNhap.getUserName()
					+ "' AND GH.TrangThaiMua = 'false'";
			query = session.createQuery(hql);
			List<GioHang> giohangs = query.list();
			hql = "FROM GioHang GH WHERE GH.userGH.UserName = '" + UserDangNhap.getUserName()
			+ "' AND GH.TrangThaiMua = 'true'";
			query = session.createQuery(hql);
			List<GioHang> giohangsapxeps = query.list();
			hql = "FROM SanPham";
			query = session.createQuery(hql);
			List<SanPham> sanphams = query.list();
			model.addAttribute("User", UserDangNhap);
			int slgh = giohangs.size();
			model.addAttribute("slgh", slgh);
			int slct = 0;
			if (slgh != 0) {
				float tongtien = 0;
				Map<Integer, SanPham_SoLuong> ctghs = new HashMap<>();
				for (GioHang g : giohangs) {
					for (SanPham s : sanphams) {
						if (g.getSanpham().getId().equals(s.getId()) == true) {
							SanPham_SoLuong spsl = new SanPham_SoLuong(s, g.getSoLuong(), g.getNgayMua());
							ctghs.put(g.getIdGH(), spsl);
							tongtien += (s.getGia() * (100 - s.getGiamGia()) / 100) * g.getSoLuong();
							break;
						}
					}
					slct += g.getSoLuong();
				}
				model.addAttribute("ctghs", ctghs);
				model.addAttribute("slct", slct);
				model.addAttribute("tongtien", tongtien);
			}
			
			float tongtienls = 0;
			
			Map<Integer, SanPham_SoLuong> sortedMaps = new HashMap<>();
			for (GioHang g : giohangsapxeps) {
				for (SanPham s : sanphams) {
					if (g.getSanpham().getId().equals(s.getId()) == true) {
						SanPham_SoLuong spsl = new SanPham_SoLuong(s, g.getSoLuong(), g.getNgayMua());
						sortedMaps.put(g.getIdGH(), spsl);
						tongtienls += (s.getGia() * (100 - s.getGiamGia()) / 100) * g.getSoLuong();
						break;
					}
				}
				slct += g.getSoLuong();
			}
			
			// Kh???i t???o ra m???t Set entries
			Set<Entry<Integer, SanPham_SoLuong>> entries = sortedMaps.entrySet();
			// T???o custom Comparator	
			Comparator<Entry<Integer, SanPham_SoLuong>> comparator = new Comparator<Entry<Integer, SanPham_SoLuong>>() {
			      @Override
			      public int compare(Entry<Integer, SanPham_SoLuong> e1, Entry<Integer, SanPham_SoLuong> e2) {
			    	  SanPham_SoLuong v1 = e1.getValue();
			    	  SanPham_SoLuong v2 = e2.getValue();
			        return v1.getNgayMua().compareTo(v2.getNgayMua());
			      }
			    };
			// Convert Set th??nh List
			List<Entry<Integer, SanPham_SoLuong>> listEntries = new ArrayList<>(entries);
			
			// S???p x???p List
			Collections.sort(listEntries, comparator);
			
			// T???o m???t LinkedHashMap v?? put c??c entry t??? List ???? s???p x???p sang
		    LinkedHashMap<Integer, SanPham_SoLuong> sortedMap = new LinkedHashMap<>(listEntries.size());
		    for (Entry<Integer, SanPham_SoLuong> entry : listEntries) {
		      sortedMap.put(entry.getKey(), entry.getValue());
		    }
		    
		    model.addAttribute("tongtienls", tongtienls);
			model.addAttribute("sortedMaps", sortedMap);
			return "form/lichsumuahang";
		}

	}

	@RequestMapping(value = "giohang/update/{IdGH}/{btn}.htm")
	public String updateGioHang(@PathVariable("IdGH") int IdGH, @PathVariable("btn") int btn, ModelMap model) {
		session = factory.openSession();
		hql = "FROM GioHang";
		query = session.createQuery(hql);
		List<GioHang> giohangs = query.list();
		GioHang gh = new GioHang();
		for (GioHang g : giohangs) {
			if (g.getIdGH() == IdGH) {
				gh = g;
				break;
			}
		}
		if (btn == 1) {
			gh.setSoLuong(gh.getSoLuong() - 1);
			t = session.beginTransaction();
			try {
				session.update(gh);
				t.commit();

			} catch (Exception e) {
				t.rollback();
			} finally {
				session.close();
			}
		} else if (btn == 2) {
			gh.setSoLuong(gh.getSoLuong() + 1);
			t = session.beginTransaction();
			try {
				session.update(gh);
				t.commit();

			} catch (Exception e) {
				t.rollback();
			} finally {
				session.close();
			}
		} else {
			t = session.beginTransaction();
			try {
				session.delete(gh);
				t.commit();

			} catch (Exception e) {
				t.rollback();
			} finally {
				session.close();
			}
		}

		session = factory.openSession();
		hql = "FROM GioHang GH WHERE GH.userGH.UserName = '" + UserDangNhap.getUserName()
				+ "' AND GH.TrangThaiMua = 'false'";
		query = session.createQuery(hql);
		List<GioHang> giohangafters = query.list();
		hql = "FROM SanPham";
		query = session.createQuery(hql);
		List<SanPham> sanphams = query.list();
		model.addAttribute("User", UserDangNhap);

		int slgh = giohangs.size();
		model.addAttribute("slgh", slgh);
		int slct = 0;
		if (slgh != 0) {
			float tongtien = 0;
			Map<Integer, SanPham_SoLuong> ctghs = new HashMap<>();
			for (GioHang g : giohangafters) {
				for (SanPham s : sanphams) {
					if (g.getSanpham().getId().equals(s.getId()) == true) {
						SanPham_SoLuong spsl = new SanPham_SoLuong(s, g.getSoLuong(), g.getNgayMua());
						ctghs.put(g.getIdGH(), spsl);
						tongtien += (s.getGia() * (100 - s.getGiamGia()) / 100) * g.getSoLuong();
						break;
					}
				}
				slct += g.getSoLuong();
			}
			model.addAttribute("slct", slct);
			model.addAttribute("ctghs", ctghs);
			model.addAttribute("tongtien", tongtien);
		}
		return "form/giohang";

	}

	@RequestMapping(value = "dathang/{tongtien}/tien")
	public String dathang(@PathVariable("tongtien") Float tongtien, ModelMap model) {
		model.addAttribute("User", UserDangNhap);
		model.addAttribute("tongtien", tongtien);
		model.addAttribute("message", "");
		return "form/dathang";
	}

	@RequestMapping(value = "dathang/trolai")
	public String dathangtrolai(ModelMap model) {
		return "redirect:/home/sanpham/xichtop/xemgiohang.htm";
	}

	@Autowired
	Mailer mailer;

	@RequestMapping(value = "dathang/xacnhan")
	public String xacnhandathang(ModelMap model) {
		boolean check = false;
		String subject = "X??c nh???n ????n h??ng";
		String EmailWeb = "xichtop99@gmail.com";
		String body = "K??nh g???i Anh/Ch???: " + UserDangNhap.getHo().trim() + " " + UserDangNhap.getTen().trim() + "\n"
				+ "C???m ??n Anh/Ch??? ???? mua h??ng t???i XiToShop. " + "Ch??ng t??i c???m th???y may m???n khi ???????c ph???c v??? Anh/Ch???."
				+ "????n h??ng c???a Anh/Ch??? ??ang ???????c v???n chuy???n.\n" + "\n" + "Xin c???m ??n!"
				+ "B??? ph???n ch??m s??c kh??ch h??ng XiToShop";
		try {
			// G???i mail
			mailer.send(EmailWeb, UserDangNhap.getEmail().trim(), subject, body);
			model.addAttribute("mess", "?????t h??ng th??nh c??ng !");
			check = true;
		} catch (Exception ex) {
			model.addAttribute("mess", "?????t h??ng th???t b???i !" + ex);
			check = false;
		}
		if (check) {
			session = factory.openSession();
			hql = "FROM GioHang GH WHERE GH.userGH.UserName = '" + UserDangNhap.getUserName().trim()
					+ "' AND GH.TrangThaiMua = 'false'";
			query = session.createQuery(hql);
			List<GioHang> giohangs = query.list();
			for (GioHang g : giohangs) {
				g.setTrangThaiMua(true);
				java.util.Date date = new java.util.Date();
				g.setNgayMua(date);
				session = factory.openSession();
				t = session.beginTransaction();
				try {
					session.update(g);
					t.commit();

				} catch (Exception e) {
					t.rollback();
				} finally {
					session.close();
				}
			}
			return "redirect:/home/index.htm";
		} else {
			return "redirect:/home/index.htm";
		}
	}

	@Autowired
	ServletContext context;

	@RequestMapping(value = "dangki", method = RequestMethod.POST, params = "btnDangKi")
	public String dangki(ModelMap model, @ModelAttribute("user") Users u, @RequestParam("PassAgain") String passAgain,
			@RequestParam("Img") MultipartFile img, BindingResult errors) {
		String errorpass = "", errorimg = "", photoPath = "";
		if (u.getUserName().trim().length() == 0) {
			errors.rejectValue("UserName", "user", "Vui l??ng nh???p t??n ????ng nh???p !");
		} else {
			session = factory.openSession();
			hql = "FROM Users";
			query = session.createQuery(hql);
			List<Users> users = query.list();
			for (Users u1 : users) {
				if (u1.getUserName().trim().equals(u.getUserName()) == true) {
					errors.rejectValue("UserName", "user", "T??n ????ng nh???p ???? t???n t???i !");
					break;
				}
			}
		}

		if (u.getPass().trim().length() == 0) {
			errors.rejectValue("Pass", "user", "Vui l??ng nh???p m???t kh???u !");
		} else if (u.getPass().trim().length() < 6) {
			errors.rejectValue("Pass", "user", "M???t kh???u ph???i l???n h??n 6 k?? t??? !");
		}

		if (passAgain.trim().length() == 0) {
			errorpass = "Vui l??ng nh???p l???i m???t kh???u !";
			model.addAttribute("errorPassAgian", errorpass);
		} else if (passAgain.equals(u.getPass().trim()) == false) {
			errorpass = "M???t kh???u kh??ng tr??ng !";
			model.addAttribute("errorPassAgian", errorpass);
		}
		if (u.getHo().trim().length() == 0) {
			errors.rejectValue("Ho", "user", "Vui l??ng nh???p h??? !");
		}
		if (u.getTen().trim().length() == 0) {
			errors.rejectValue("Ten", "user", "Vui l??ng nh???p t??n !");
		}
		if (u.getGioiTinh() == null) {
			errors.rejectValue("GioiTinh", "user", "Vui l??ng ch???n gi???i t??nh !");
		}
		if (u.getNgaySinh() == null) {
			errors.rejectValue("NgaySinh", "user", "Vui l??ng nh???p ng??y sinh !");
		}

		if (u.getEmail().trim().length() == 0) {
			errors.rejectValue("Email", "user", "Vui l??ng nh???p Email !");
		}

		if (u.getSDT().trim().length() == 0) {
			errors.rejectValue("SDT", "user", "Vui l??ng nh???p s??? ??i???n tho???i !");
		}
		if (u.getDiaChi().trim().length() == 0) {
			errors.rejectValue("DiaChi", "user", "Vui l??ng nh???p ?????a ch??? !");
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
			return "form/dangki";
		} else {
			
			boolean check = false;
			String subject = "????ng k?? t??i kho???n th??nh c??ng";
			String EmailWeb = "xichtop99@gmail.com";
			String body = "K??nh g???i Anh/Ch???: " + u.getHo().trim() + " " + u.getTen().trim() + "\n"
					+ "C???m ??n Anh/Ch??? ???? t???o t??i kho???n t???i XiToShop. " + "Ch??ng t??i c???m th???y may m???n khi ???????c ph???c v??? Anh/Ch???."
					+ "T??i kho???n c???a Anh/Ch???.\n" 
					+ "T??i kho???n: " + u.getUserName() 
					+ "M???t kh???u: " + u.getPass()
					+ "\n" + "Xin c???m ??n!"
					+ "B??? ph???n ch??m s??c kh??ch h??ng XiToShop";
			try {
				// G???i mail
				mailer.send(EmailWeb, u.getEmail().trim(), subject, body);
				model.addAttribute("mess", "T???o t??i kho???n th??nh c??ng !");
				check = true;
			} catch (Exception ex) {
				model.addAttribute("mess", "T???o t??i kho???n th???t b???i !" + ex);
				check = false;
			}
			
			if(check){
				session = factory.openSession();
				t = session.beginTransaction();
				u.setLinkAnh(photoPath);
				u.setAdmin(false);
				try {
					session.save(u);
					t.commit();
					model.addAttribute("mess", "????ng k?? th??nh c??ng !");
				} catch (Exception e) {
					t.rollback();
					model.addAttribute("mess", "????ng k?? th???t b???i !");
				} finally {
					session.close();
				}
				return "redirect:/home/index.htm";
			}
			else {
				return "redirect:/home/index.htm";
			}
			
		}
	}
	
	@ModelAttribute("genders")
	public Map<Boolean, String> getGender() {
		Map<Boolean, String> genders = new HashMap<>();
		genders.put(false, "N???");
		genders.put(true, "Nam");
		return genders;
	}

	@ModelAttribute("thongbaos")
	public List<ThongBao> setThongBao() {
		session = factory.openSession();
		hql = "FROM ThongBao";
		query = session.createQuery(hql);
		List<ThongBao> thongbaos = query.list();
		return thongbaos;
	}

}
