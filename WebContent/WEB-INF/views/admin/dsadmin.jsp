<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">

<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Danh Sách Admin</title>
<link rel="shortcut icon" type="image/png" href="/favicon.png"/>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
<link rel="stylesheet" href="assets/css/base.css">
<link rel="stylesheet" href="assets/css/main.css">
<link rel="stylesheet" href="./assets/css/add.css">
<link rel="stylesheet"
	href="assets/fonts/fontawesome-free-5.12.1/fontawesome-free-5.12.1-web/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap&subset=vietnamese"
	rel="stylesheet">
<script src="/main.js"></script>
</head>
<body>
	<div class="admin--container">
        <header class="admin--header">
			<div class="grid">
				<nav class="header--navbar">
					<ul class="header--navbar-list">
						<li class="header--navbar-list-item admin--header--name">
							QUẢN TRỊ WEBSITE
						</li>
					</ul>
					<ul class="header--navbar-list">
						<!-- <c:if test="${User.getUserName().length() != 1 }"> -->
						<li class="header--navbar-list-item header--navbar-user">
                            <span class="header--navbar-user-name">Admin: ${User.getTen() }</span>
							<div class="header--navbar-user-menu">
								<ul class="header--navbar-user-list">
									<li class="header--navbar-user-item"><i
										class="far fa-user-circle"></i> <a href = "home/index.htm" style = "text-decoration: none; color: black;"
                                        class="header--navbar-user-label">Vào Trang Web</a>
                                    </li>
									<li class="header--navbar-user-item"><i
										class="fas fa-sign-out-alt"></i> <a href = "home/dangxuat.htm" style = "text-decoration: none; color: black;"
										class="header--navbar-user-label">Đăng Xuất</a></li>
								</ul>
                            </div>
                        </li>
						<!-- </c:if> -->
					</ul>
				</nav>
			</div>
		</header>
        <nav>
            <div class="grid admin--nav">
                <a class = "admin--nav--link" href="admin/listsanpham.htm">Danh Sách Sản Phẩm</a>
                <a class = "admin--nav--link" href="admin/themsanpham.htm">Thêm Sản Phẩm Mới</a>
                <a class = "admin--nav--link" href="admin/listuser.htm">Danh Sách Người Dùng</a>
                <a class = "admin--nav--link" href="admin/listadmin.htm">Danh Sách Admin</a>
                <a class = "admin--nav--link" href="admin/dangki.htm">Tạo Tài Khoản Admin Mới</a>
            </div>
        </nav>
        <article>
            <div class="app-container">
                <div class="grid">
                    <div class="grid--row">
                        <div class="grid--column-2-5">
                            <h3 class="cart--list--header">HÌNH ẢNH</h3>
                        </div>
                        
                        <div class="grid--column-2-5">
                            <h3 class="cart--list--header">USERNAME</h3>
                        </div>
                        
                        <div class="grid--column-2-5">
                            <h3 class="cart--list--header">HỌ</h3>
                        </div>
                        <div class="grid--column-2-5">
                            <h3 class="cart--list--header">TÊN</h3>
                        </div>
    
                        <div class="grid--column-2-5">
                            <h3 class="cart--list--header">EMAIL</h3>
                        </div>
                        <div class="grid--column-2-5">
                            <h3 class="cart--list--header">SĐT</h3>
                        </div>
                    </div>
                    <c:forEach var = "u" items = "${users }" varStatus = "status">
                    <div class="grid--row grid--row1" >
                        <div class="grid--column-2-5 cart--list--contain">
                            <img src="${u.getLinkAnh() }" alt="" class="cart--list-img">
                        </div>
                        <div class="grid--column-2-5 cart--list--contain">
                            <span style = "font-size: 1.4rem;" class="cart--list--contain-label">${u.getUserName() }</span>
                        </div>
                        <div class="grid--column-2-5 cart--list--contain" >
                            <span style = "font-size: 1.4rem;" class="cart--list--contain-label">${u.getHo() }</span>
                        </div>
                        <div class="grid--column-2-5 cart--list--contain">
                            <span style = "font-size: 1.4rem;" class="cart--list--contain-label">${u.getTen() }</span>
                        </div>
                         <div class="grid--column-2-5 cart--list--contain">
                            <span style = "font-size: 1.4rem;" class="cart--list--contain-label">${u.getEmail() }</span>
                        </div>
                         <div class="grid--column-2-5 cart--list--contain">
                            <span style = "font-size: 1.4rem;" class="cart--list--contain-label">${u.getSDT() }</span>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
        </article>
    </div>
</body>
</html>