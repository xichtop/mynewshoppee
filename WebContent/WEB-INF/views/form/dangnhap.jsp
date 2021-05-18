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
<title>Đăng Nhập</title>
<link rel="shortcut icon" type="image/png" href="/favicon.png"/>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
<link rel="stylesheet" href="assets/css/base.css">
<link rel="stylesheet" href="assets/css/main.css">
<link rel="stylesheet"
	href="assets/fonts/fontawesome-free-5.12.1/fontawesome-free-5.12.1-web/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap&subset=vietnamese"
	rel="stylesheet">
<script src="/main.js"></script>
</head>
<body>
	<!-- Modal -->
	<div class="modal">
		<div class="modal--overlay"></div>
			<div class="modal--body">

				<!-- Form đăng nhập -->
				<!-- <div class="form1"> -->
				<form:form class="form1" action = "home/dangnhap.htm" modelAttribute = "User">
					<div class="form--container">
						<div class="form--header">
							<h3 class="form--header-heading">Đăng Nhập</h3>
							<a href ="home/dangki.htm" style = "text-decoration: none" class="form--header-btn">Đăng Ký</a>
						</div>
						<div class="form--body">
							<span style="color: red; font-style: italic;">${message}</span>
							<div class="form--body-group">
								<form:input path = "UserName" type="text" class="form--body-input"
									placeholder="Tên đăng nhập của bạn"/>
								<form:errors style="color: red; font-style: italic; padding-top: 10px;" path="UserName"></form:errors>
							</div>
							<div class="form--body-group">
								<form:input path = "Pass" type="text" class="form--body-input"
									placeholder="Mật khẩu của bạn"/>
								<form:errors style="color: red; font-style: italic; padding-top: 10px;" path="Pass"></form:errors>
							</div>

						</div>
						<div class="form--aside-login">
							<a href="" class="form--aside--link">Quên mật khẩu</a> <span
								class="form--aside-separate"></span> <a href=""
								class="form--aside--link form--aside-help">Cần trợ giúp?</a>
						</div>
						<div class="form--control">
							<button name = "btnTroLai" class="btn btn-nomal form--control-back">TRỞ LẠI</button>
							<button name = "btnDangNhap" class="btn btn--primary">ĐĂNG NHẬP</button>
						</div>

					</div>
					<div class="form--social">
						<a href="https://www.facebook.com/xichtop/"
							class="btn btn-icon .btn-sizeS form--social-facebook"> <i
							class="fab fa-facebook-square form-icon"></i> <span
							class="form--social--text">Đăng nhập với Facebook</span>
						</a> <a href="https://www.facebook.com/xichtop/"
							class="btn btn-icon .btn-sizeS form--social-google"> <i
							class="fab fa-google form-icon"></i> <span
							class="form--social--text"> Đăng nhập với Google </span>

						</a>
					</div>
				<!-- </div> -->
				</form:form>
			</div>
		</div>
</body>
</html>