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
    <title>Sửa Thông Tin</title>
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
            <form:form class="form" action = "home/suathongtin.htm" modelAttribute="user">
                <div class="form--container">
                    <div class="form--header">
                        <h3 class="form--header-heading">Chỉnh sửa thông tin khách hàng</h3>
                    </div>
                    <div class="form--body">
                    	<span style="color: red; font-style: italic;">${message}</span>
                    	<div class="form--body-group">
                            <form:input path = "UserName" type="text" class="form--body-input1" readonly = "true"/>
                        </div>
                        <div class="form--body-group">
                            <form:input path = "Ho" type="text" class="form--body-input1" placeholder="Họ"/>
                            <form:errors style="color: red; font-style: italic; padding-top: 10px;" path="Ho"></form:errors>
                        </div>
                        <div class="form--body-group">
                            <form:input path = "Ten" type="text" class="form--body-input1" placeholder="Tên"/>
                            <form:errors style="color: red; font-style: italic; padding-top: 10px;" path="Ten"></form:errors>
                        </div>
                        <div class = "form--body-gender" style = "padding-top: 10px;">
							<label style = "padding-right: 10px;">Giới tính: </label>
							<form:radiobuttons class = "form--body-gender-item" path="GioiTinh" items="${genders}" />
							<form:errors style="color: red; font-style: italic; padding-top: 10px;" path="GioiTinh"></form:errors>
						</div>
						<div class="form--body-group">
                            <form:input path = "NgaySinh" type="text" class="form--body-input1" placeholder="Ngày Sinh"/>
                            <form:errors style="color: red; font-style: italic; padding-top: 10px;" path="NgaySinh"></form:errors>
                        </div>
                        <div class="form--body-group">
                            <form:input path = "Email" type="text" class="form--body-input1" placeholder="Email"/>
                            <form:errors style="color: red; font-style: italic; padding-top: 10px;" path="Email"></form:errors>
                        </div>
                        <div class="form--body-group">
                            <form:input path = "SDT" type="text" class="form--body-input1" placeholder="Số điện thoại"/>
                            <form:errors style="color: red; font-style: italic; padding-top: 10px;" path="SDT"></form:errors>
                        </div>
                        <div class="form--body-group">
                            <form:input path = "DiaChi" type="text" class="form--body-input1" placeholder="Địa Chỉ"/>
                            <form:errors style="color: red; font-style: italic; padding-top: 10px;" path="DiaChi"></form:errors>
                        </div>
                    </div>
                    <div class="form--control1">
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