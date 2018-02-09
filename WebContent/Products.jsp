<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="my.example.cart.ProductDAO" %>
<%@ page import="my.example.cart.ItemDTO" %>
<%! 
	ArrayList<ItemDTO> products = new ProductDAO().getProductList();
	int itemInCart = 0;
	HttpSession session;
%>
<%
	session = request.getSession();
	if( session.getAttribute("cart") != null ){
		ArrayList<ItemDTO> myCart = (ArrayList<ItemDTO>) session.getAttribute("cart");
		
		System.out.println("[MyCartList]");
		for(ItemDTO item : myCart){
			System.out.println( item.getTitle() );
		}
		
		itemInCart = myCart.size();
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
	<link rel="stylesheet" href="/Carts/style/Products.css"/>
</head>
<body>
	<div>
		<a href="/Carts/Carts.jsp">장바구니</a>
		<a style="color:red;"><%=itemInCart%></a>
	</div>
	<ul id="product-list">
		<% for(int i = 0; i < products.size(); i ++ ) { %>
		<li class="item <%=products.get(i).getId()%>">
			<ul class="item-information">
				<li class="item-number">
					<a><%=i+1%></a>
				</li>
				<li class="item-image">
					<img src="/Carts/image/products/n0001.jpg"/>
				</li>
				<li class="item-name">
					<a href="#"><%=products.get(i).getTitle()%></a>
				</li>
				<li class="item-price">
					<a href="#"><%=products.get(i).getPatternPrice(-1)%>원</a>
				</li>
				<li class="item-date">
					<a href="#"><%=products.get(i).getDate()%></a>
				</li>
				<li class="item-cart">
				  	 <a href="/Carts/cart/add?id=<%=products.get(i).getId()%>" onClick="javascript:return confirm('<%= products.get(i).getTitle() %>을 담으시겠습니까?')">장바구니 담기</a>
				</li>
			</ul>
		</li>
		<% } %>
	</ul>
</body>
</html>