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
<title>XiTo Shop</title>
<link rel="shortcut icon" type="image/png" href="assets/img/favicon.png"/>
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
	<div class="app">
		<!-- Header -->
		<header class="header">
			<div class="grid">
				<nav class="header--navbar">
					<ul class="header--navbar-list">
						<li
							class="header--navbar-list-item header--navbar-list-item--qr header--navbar-list-item--separate">
							Vào cửa hàng trên ứng dụng ShopTop
							<div class="header--qr">
								<img src="assets/img/QR.png" alt="QR Code">
								<div class="header--apps">
									<a href="" class="header--apps-link"> <img
										src="assets/img/GGPlay.png" alt="Google Play"
										class="header--apps-img1">
									</a> <a href="" class="header--apps-link"> <img
										src="assets/img/AppStore.png" alt="App Store"
										class="header--apps-img2">
									</a>
								</div>
							</div>
						</li>
						<li class="header--navbar-list-item "><span
							class="header--navbar-list-icon-item--no-pointer"> Kết Nối
						</span> <a href="https://www.facebook.com/xichtop/"
							class="header--navbar-list-item-icon"> <i
								class="fab fa-facebook"></i>
						</a> <a href="https://www.facebook.com/xichtop/"
							class="header--navbar-list-item-icon"> <i
								class="fab fa-instagram"></i>
						</a></li>
					</ul>
					<ul class="header--navbar-list">
						<li
							class="header--navbar-list-item header--navbar-list-item--notify">
							<a href="" class="header--navbar-list-item-link"> <i
								class="fas fa-bell"></i> Thông báo
						</a>
							<div class="header--notify">
								<header class="header--notify-header">
									<span>Thông Báo Mới Nhất</span>
								</header>
								<ul class="header--notify-list">
									<c:forEach var = "t" items = "${thongbaos }">
										<!-- header--notify--unview -->
										<li class="header--notify-item"><a
										href="" class="header--notify-link"> <img
											src="${t.getLinkAnh() }" alt="Thông báo 1"
											class="header--notify-img">
											<div class="header--notify-info">
												<span class="header--notify-name">
													<h3>${t.getTieuDe() }</h3>
												</span> <span class="header--notify-description">
													<h4>${t.getNoiDung() }</h4>
												</span>
											</div>
									</a></li>
									</c:forEach>
								</ul>
								<footer class="header-notify-footer">
									<a href="" class="header-notify-footer-btn">Xem Tất Cả</a>
								</footer>

							</div>
						</li>
						<li class="header--navbar-list-item"><a href=""
							class="header--navbar-list-item-link"> <i
								class="far fa-question-circle"></i> Trợ giúp
						</a></li>
						<c:if test="${User.getUserName().length() == 1 }">
							<li><a href = "home/dangki.htm" style = "text-decoration: none" 
							class="header--navbar-list-item header--navbar-list-item--strong header--navbar-list-item--separate">
							Đăng Ký</a></li>
                            <li><a href = "home/dangnhap.htm" style = "text-decoration: none" 
                            class="header--navbar-list-item header--navbar-list-item--strong ">
                            Đăng Nhập</a></li>
						</c:if>
						<c:if test="${User.getUserName().length() != 1 }">
							<li class="header--navbar-list-item header--navbar-user"><img
							src="${User.getLinkAnh() }" alt=""
							class="header--navbar-user-img"> <span
							class="header--navbar-user-name">${User.getTen() }</span>
							<div class="header--navbar-user-menu">
								<ul class="header--navbar-user-list">
									<li class="header--navbar-user-item"><i
										class="far fa-user-circle"></i> <a href = "home/suathongtin.htm" style = "text-decoration: none; color: black;"
										class="header--navbar-user-label">Sửa Thông Tin</a></li>
									<li class="header--navbar-user-item"><i class="fas fa-history"></i>
										<a href = "home/lichsu.htm" style = "text-decoration: none; color: black;" class="header--navbar-user-label">Lịch Sử Mua Hàng</a></li>
									<li class="header--navbar-user-item"><i
										class="fas fa-sign-out-alt"></i> <a href = "home/dangxuat.htm" style = "text-decoration: none; color: black;"
										class="header--navbar-user-label">Đăng Xuất</a></li>
								</ul>
							</div></li>
						</c:if>
						
					</ul>
				</nav>
				<div class="header-with-search">
					<div class="header-logo">
						<a href="home/index.htm" class="header--logo-link"> <img
							src="assets/img/Logo.png" alt="Logo" class="header-logo-img"></a>
					</div>
					<form action = "home/search.htm" class="header-search">
						<div class="header-search-input-wrap">
							<input name = "timkiem" type="text" class="header-search-input"
								placeholder="Nhập để tìm kiếm sản phẩm">
						</div>
						<div class="header-search-select">
							<span class="header-search-select-label">Trong Shop</span> <i
								class="header-search-select-icon fas fa-angle-down"></i>
							<ul class="header-search-option">
								<li
									class="header-search-option-item header-search-option-item--checked">
									<span>Trong Shop</span> <i class="fas fa-check"></i>
								</li>
								<li class="header-search-option-item"><span>Ngoài
										Shop</span> <i class="fas fa-check"></i></li>
							</ul>
						</div>
						<button name = "btnSearch" class="header-search-btn">
								<i class="header-search-btn-icon fas fa-search"></i>
						</button>
					</form>
					<div class="header-cart">
						<div class="header-cart-wrap">
							<i class="header-cart-icon fas fa-cart-plus"></i>
							<c:if test="${slgh == 0 || User.getUserName().length() == 1}">
								<div class="header-cart-list header-cart-no-cart">
								<img src="assets/img/no-cart.png" alt=""
									class="header-cart-no-cart-img"> 
								<span class="header-cart-no-cart-msg">Chưa có sản phẩm</span> 
								</div>
							</c:if>
							
							<c:if test="${slgh != 0 && User.getUserName().length() != 1}">
								 <span class="header-cart-notice">${slct }</span>
								<!-- No cart -> header-cart-no-cart -->
								<div class=" header-cart-list">
								<span class="header-cart-head">Sản phẩm đã thêm</span>
								<ul class="header-cart-ul">
									<c:forEach var = "ctgh" items = "${ctghs }" varStatus = "status">
										<li class="header-cart-item">
										<img src="${ctgh.getValue().getS().getLink() }" alt=""
										class="header-cart-item-img">
										<div class="header-cart-item-info">
											<div class="header-cart-info-head">
												<h5 class="header-cart-info-name">${ctgh.getValue().getS().getTen() }</h5>
												<div class="header-cart-info-price-wrap">
													<span class="header-cart-info-price">
													<fmt:formatNumber value="${ctgh.getValue().getS().getGia() * ((100 - ctgh.getValue().getS().getGiamGia()) / 100) }" type="currency" pattern = "###,###,### đ"/>
													</span> 
													<span
														class="header-cart-info-multify">x</span>
													<span class="header-cart-info-quantify">${ctgh.getValue().getSoluong() }</span>
												</div>
											</div>
											<div class="header-cart-info-body">
												<span class="header-cart-info-description">Loại hàng: ${ctgh.getValue().getS().getThuongHieu() }</span> 
											</div>
										</div></li>
									</c:forEach>
								</ul>
								<div class="header-cart-money">
									<div class="header-cart-money-all">
										<span>Tổng tiền:</span> 
										<span>
											<fmt:formatNumber value="${tongtien }" type="currency" pattern = "###,###,### đ"/>
										</span>
									</div>
								</div>
								<a href="home/sanpham/${User.getUserName().trim()}/xemgiohang.htm" class="btn btn--primary btn-cart"> Xem Giỏ Hàng </a>
							</div>
							</c:if>
							
						</div>
					</div>
				</div>
			</div>
		</header>
		<div class="app-container">
			<div class="grid">
				<div class="grid--row app-content">
					<div class="grid--column-2">
						<div class="category">
							<h3 class="category-heading">
								<i class="category-heading-icon fas fa-list"></i> Danh mục
							</h3>
							<ul class="category-list">
									<c:forEach var="d" items="${dms }" varStatus = "status">
									<!-- category-item-active -->
										<c:if test="${status.index == dmInt }">
											<li class="category-item category-item-active">
											<a href="home/sapxep/${status.index}/0/1.htm"
											class="category-item-link">${d.getTen() }</a></li>
										</c:if>
										
										<c:if test="${status.index != dmInt }">
											<li class="category-item"><a href="home/sapxep/${status.index}/0/1.htm"
											class="category-item-link">${d.getTen() }</a></li>
										</c:if>
									</c:forEach>
								
							</ul>
						</div>
					</div>
					<div class="grid--column-10">
						<div class="home-filter">
							<span class="home-filter-label">Sắp xếp theo</span>
							<!-- <button class="home-filter-btn btn btn--primary">Phổ
								biến</button>
							<button class="home-filter-btn btn">Mới nhất</button>
							<button class="home-filter-btn btn">Bán chạy</button> -->
							<div class="select-input">
								<span class="select-input-label">Giá</span> <i
									class="select-input-icon fas fa-angle-down"></i>
								<ul class="select-input-list">
									<li class="select-input-item"><a href="home/sapxep/${dmInt }/1/1.htm"
										class="select-input-link">Giá: Từ thấp đến cao</a></li>
									<li class="select-input-item"><a href="home/sapxep/${dmInt }/0/1.htm"
										class="select-input-link">Giá: Từ cao đến thấp</a></li>
								</ul>
							</div>
							<!-- <div class="home-filter-page">
								<div class="home-filter-page-num">
									<span class="home-filter-page-current">1</span>/14
								</div>
								<div class="home-filter-page-control">
									<a href=""
										class="home-filter-page-control-btn home-filter-page-control-disable">
										<i class="home-filter-page-control-icon fas fa-angle-left"></i>
									</a> <a href="" class="home-filter-page-control-btn"> <i
										class="home-filter-page-control-icon fas fa-angle-right"></i>
									</a>
								</div>
							</div> -->
						</div>
						<div class="home-product">
							<div class="grid--row">
								<!-- Product-item -->
								<c:forEach begin = "${(listInt - 1) * 10}" end = "${listInt * 10 - 1}" var="s" items="${sps }">
									<div class="grid--column-2-4">
		 								<a href = "home/sanpham/${s.getId().trim()}.htm" style = "text-decoration: none;" >
											<div class="home-product-item">
											<img src="${s.getLink() }" alt=""
												class="home-product-item-img">
											<h4 class="home-product-item-name">${s.getTen() }</h4>
											<div class="home-product-item-price">
												<span class="home-product-item-price-old">
													<fmt:formatNumber value="${s.getGia() }" type="currency" pattern = "###,###,### đ"/>
												</span>
												<span class="home-product-item-price-current">
													<fmt:formatNumber value="${s.getGia() * ((100 - s.getGiamGia())/100) }" type="currency" pattern = "###,###,### đ"/>
												</span>
											</div>
											<div class="home-product-item-action">
												<!-- Liked home-product-item-action-like-liked -->
												<div
													class="home-product-item-action-like home-product-item-action-like-liked">

													<c:if test="${s.getYeuThich() == true }">
														<i class="home-product-item-action-like-fill fas fa-heart"></i>
													</c:if>
													<c:if test="${s.getYeuThich() == false }">
														<i
															class="home-product-item-action-like-empty far fa-heart"></i>
													</c:if>
												</div>
												<div class="home-product-item-action-rating">
													<!-- Rating  home-product-item-action-star-gold-->
													<c:forEach begin="1" end="${s.getDanhGia() }">
														<i class="home-product-item-action-star-gold fas fa-star"></i>
													</c:forEach>

												</div>
												<!-- <div class="home-product-item-action-sold">100 đã bán</div> -->
											</div>
											<div class="home-product-item-origin">
												<span class="home-product-item-origin-brand">${s.getThuongHieu() }</span>
												<span class="home-product-item-origin-name">${s.getXuatXu() }</span>
											</div>
											<c:if test="${s.getYeuThich() == true }">
												<div class="home-product-item-favorite">
													<i class="fas fa-check"></i> <span
														class="home-product-item-favorite-label">Yêu thích</span>
												</div>
											</c:if>

											<div class="home-product-item-sell-off">
												<div class="home-product-item-sell-off-value">${s.getGiamGia() }%</div>
												<div class="home-product-item-sell-off-label">GIẢM</div>
											</div>
										</div>
										</a>
									</div>
								</c:forEach>

							</div>
						</div>
						<ul class="pagination home-product--pagination">
							<li class="pagination-item"><a href=""
								class="pagination-item__link"> <i
									class="pagination-item__icon fas fa-angle-left"></i>
							</a></li>
							
							<c:forEach begin = "1" end = "${listTong }" varStatus = "status" >
								<c:if test="${status.index == listInt }">
									<li class="pagination-item pagination-item--active">
									<a href="home/sapxep/${dmInt }/${sxInt }/${status.index }.htm" class="pagination-item__link">${status.index }</a></li>
								</c:if>
								<c:if test="${status.index != listInt }">
									<li class="pagination-item">
									<a href="home/sapxep/${dmInt }/${sxInt }/${status.index }.htm" class="pagination-item__link">${status.index }</a></li>
								</c:if>
							</c:forEach>
							
							<!-- <li class="pagination-item pagination-item--active"><a
								href="" class="pagination-item__link">1</a></li>

							<li class="pagination-item"><a href=""
								class="pagination-item__link">2</a></li>
							<li class="pagination-item"><a href=""
								class="pagination-item__link">3</a></li>
							<li class="pagination-item"><a href=""
								class="pagination-item__link">4</a></li>
							<li class="pagination-item"><a href=""
								class="pagination-item__link">5</a></li>
							<li class="pagination-item"><a href=""
								class="pagination-item__link">...</a></li>
							<li class="pagination-item"><a href=""
								class="pagination-item__link">10</a></li> -->

							<li class="pagination-item"><a href=""
								class="pagination-item__link"> <i
									class="pagination-item__icon fas fa-angle-right"></i>
							</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<footer class="footer">
			<div class="grid">
				<div class="grid--row">
					<div class="grid--column-2-4">
						<h3 class="footer--header">Chăm sóc khách hàng</h3>
						<ul class="footer--list">
							<li class="footer--item"><a href=""
								class="footer--item__link">Trung tâm trợ giúp</a></li>
							<li class="footer--item"><a href=""
								class="footer--item__link">Hướng dẫn mua hàng</a></li>
							<li class="footer--item"><a href=""
								class="footer--item__link">XiToShop Mall</a></li>
						</ul>
					</div>

					<div class="grid--column-2-4">
						<h3 class="footer--header">Danh mục</h3>
						<ul class="footer--list">
							<li class="footer--item"><a href=""
								class="footer--item__link">Theo dõi</a></li>
							<li class="footer--item"><a href=""
								class="footer--item__link">Vào cửa hàng</a></li>
							<li class="footer--item"><a href=""
								class="footer--item__link">XiToShop Mall</a></li>
						</ul>
					</div>

					<div class="grid--column-2-4">
						<h3 class="footer--header">Giới thiệu</h3>
						<ul class="footer--list">
							<li class="footer--item"><a href=""
								class="footer--item__link">Giới thiệu về XiToShop</a></li>
							<li class="footer--item"><a href=""
								class="footer--item__link">Tuyển dụng</a></li>
							<li class="footer--item"><a href=""
								class="footer--item__link">Điều khoản</a></li>
						</ul>
					</div>

					<div class="grid--column-2-4">
						<h3 class="footer--header">Theo dõi chúng tôi trên</h3>
						<ul class="footer--list">
							<li class="footer--item"><a href=""
								class="footer--item__link"> <i
									class=" footer--item__icon fab fa-facebook"></i> Facebook
							</a></li>
							<li class="footer--item"><a href=""
								class="footer--item__link"> <i
									class=" footer--item__icon fab fa-instagram"></i> Instagram
							</a></li>
							<li class="footer--item"><a href=""
								class="footer--item__link"> <i
									class=" footer--item__icon fab fa-youtube"></i> Youtube
							</a></li>
						</ul>
					</div>

					<div class="grid--column-2-4">
						<h3 class="footer--header">Vào cửa hàng trên ứng dụng</h3>
						<div class="footer--download">
							<img src="./assets/img/QR.png" alt="QR Code"
								class="footer--download__qr">
							<div class="footer--apps">
								<a href="" class="footer--apps-link"> <img
									src="./assets/img/GGPlay.png" alt="Google Play"
									class="footer--apps-img">
								</a> <a href="" class="footer--apps-link"> <img
									src="./assets/img/AppStore.png" alt="App Store"
									class="footer--apps-img">
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- <div class="footer--copyright">
                <div class="grid">
                    <p class="footer--copyright__text">2020 Bản quyền thuộc công ty Xích Tốp</p>
                </div>
            </div> -->
		</footer>
	</div>

</body>

</html>