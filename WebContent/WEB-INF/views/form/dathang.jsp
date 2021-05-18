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
<title>Đặt Hàng</title>
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
    <div class="modal">
		<div class="modal--overlay"></div>
		<div class="modal--body">
            <div class="pay--container">
                <div class="pay--header">
                    <h3 class="pay--header-heading">XÁC NHẬN THÔNG TIN ĐẶT HÀNG</h3>
                </div>
            </div>
            <div class="pay--info">
            	<div class="pay--info--contain">
                    <p class="pay--info-item">Khách hàng: ${User.getHo().concat(" ").concat(User.getTen())}</p>
                </div>
                <div class="pay--info--contain">
                    <p class="pay--info-item">Email: ${User.getEmail() }</p>
                </div>
                <div class="pay--info--contain">
                    <p class="pay--info-item">Số điện thoại: ${User.getSDT() }</p>
                </div>
                <div class="pay--info--contain">
                    <p class="pay--info-item">Địa chỉ: ${User.getDiaChi() }</p>
                </div>
                <p class="pay--info-item">Hình thức thanh toán: Thanh toán sau khi nhận hàng.</p>
                <p class="pay--info-item">Số tiền thanh toán: ${tongtien }đ</p>
            </div>
            <div class="pay--control">
                <button class="btn btn-nomal form--control-back"><a style = "color: black;" href="home/dathang/trolai.htm" class = "pay--control-link">TRỞ LẠI</a></button>
                <button class="btn btn--primary"><a href="home/suathongtin.htm" class = "pay--control-link">THAY ĐỔI THÔNG TIN</a></button>
                <button class="btn btn--primary"><a href="home/dathang/xacnhan.htm" class = "pay--control-link">XÁC NHẬN ĐẶT HÀNG</a></button>
            </div>
        </div>
    </div>
</body>
</html>