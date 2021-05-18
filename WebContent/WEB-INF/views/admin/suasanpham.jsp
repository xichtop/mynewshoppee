<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">

<head>
	<base href="${pageContext.servletContext.contextPath}/">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sửa Sản Phẩm</title>
    <link rel="shortcut icon" type="image/png" href="/favicon.png"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/main.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-5.12.1/fontawesome-free-5.12.1-web/css/all.min.css">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap&subset=vietnamese"
        rel="stylesheet">
    <script src="/main.js"></script>
</head>
<body>
	<!-- Modal -->
	<div class="modal">
		<div class="modal--overlay"></div>
			<div class="modal--body">
	<!-- Form đăng kí -->
            <!-- <div class="form"> -->
            <form:form class="form" action = "admin/suasanpham.htm" modelAttribute="sanpham">
                <div class="form--container">
                    <div class="form--header">
                        <h3 class="form--header-heading">Sửa Sản Phẩm</h3>
                    </div>
                    <div class="form--body">
                    	<span style="color: red; font-style: italic;">${message}</span>
                        <div class="form--body-group">
                            <form:input path = "Id" type="text" class="form--body-input1" placeholder="ID sản phẩm" readonly="true"/>
                        </div>
                        <div class="form--body-group">
                            <form:input path = "Ten" type="text" class="form--body-input1" placeholder="Tên sản phẩm"/>
                            <form:errors style="color: red; font-style: italic; padding-top: 10px;" path="Ten"></form:errors>
                        </div>
                        <div class="form--body-group">
                            <form:input path = "Gia" class="form--body-input1" placeholder="Giá sản phẩm"/>
                            <form:errors style="color: red; font-style: italic; padding-top: 10px;" path="Gia"></form:errors>
                        </div>
                        <div class="form--body-group">
                            <form:input path = "ThuongHieu" type="text" class="form--body-input1" placeholder="Thương Hiệu Sản Phẩm"/>
                            <form:errors style="color: red; font-style: italic; padding-top: 10px;" path="ThuongHieu"></form:errors>
                        </div>
                        <div class="form--body-group">
                            <form:input path = "XuatXu" type="text" class="form--body-input1" placeholder="Xuất xứ sản phẩm"/>
                            <form:errors style="color: red; font-style: italic; padding-top: 10px;" path="XuatXu"></form:errors>
                        </div>
                        
                        <div class="form--body-group">
                            <form:input path = "Link" type="text" class="form--body-input1" placeholder="Link ảnh của sản phẩm"/>
                            <form:errors style="color: red; font-style: italic; padding-top: 10px;" path="Link"></form:errors>
                        </div>
                        
                        <div style = "font-size: 1.4rem; padding-top: 10px;" class="form--body-group">
                        	<label style = "padding-right: 10px;">Yêu thích: </label>
                            <form:checkbox path = "YeuThich" class="" placeholder="Xuất xứ sản phẩm"/>
                            <form:errors style="color: red; font-style: italic; padding-top: 10px;" path="YeuThich"></form:errors>
                        </div>
                        
                        <div style = "display: flex; align-items: center;" class="form--body-group">
                        	<label style = "width: 100px; font-size: 1.4rem;">Đánh giá: </label>
                            <form:input path = "DanhGia" class="form--body-input1" placeholder="Đánh giá sản phẩm"/>
                            <form:errors style="color: red; font-style: italic; padding-top: 10px;" path="DanhGia"></form:errors>
                        </div>
                        <div style = "display: flex; align-items: center;" class="form--body-group">
                        	<label style = "width: 100px; font-size: 1.4rem;">Giảm giá: </label>
                            <form:input path = "GiamGia" class="form--body-input1" placeholder="Giảm giá"/>
                            <form:errors style="color: red; font-style: italic; padding-top: 10px;" path="GiamGia"></form:errors>
                        </div>
                        
                        <div class = "form--body-gender" style = "padding-top: 10px; font-size: 1.6rem;">
						<label>Danh mục: </label>
							<form:select path="danhmuc.DanhMucId" items="${danhmucs}" itemLabel = "Ten" itemValue="DanhMucId"/>
							<form:errors style="color: red; font-style: italic; padding-top: 10px;" path="danhmuc"></form:errors>
						</div>
                        
                    </div>
                    <div style = "padding-bottom: 20px;" class="form--control1">
                        <button name = "btnTroLai" class="btn btn-nomal form--control-back">TRỞ LẠI</button>
                        <button name = "btnXacNhan" class="btn btn--primary">XÁC NHẬN</button>
                    </div>
                    
                </div>
           </form:form>
           <!-- </div> -->
           </div>
           </div>
</body>
</html>