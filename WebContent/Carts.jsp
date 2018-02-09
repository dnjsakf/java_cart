<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="my.example.cart.ProductDAO" %>
<%@ page import="my.example.cart.ItemDTO" %>
<%! 
	int itemInCart = 0;
	ArrayList<ItemDTO> myCart = null;;
	HttpSession session = null;
%>
<%
	session = request.getSession();
	if( session.getAttribute("cart") != null ){
		myCart = (ArrayList<ItemDTO>) session.getAttribute("cart");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="/Carts/style/Carts.css">
</head>
<body>
	<a href="/Carts/Products.jsp">�ڷΰ���</a>
	<% if( myCart != null && myCart.size() != 0 ) { %>
	<ol>
		<% for(int i = 0; i < myCart.size(); i++) { %>
		<li class="cart-item" value="<%=i+1%>">
			<div class="">
				<a><%=i+1%></a>
			</div>
			<div class="">
				<a><%=myCart.get(i).getTitle()%></a>
			</div>
			<div class="">
				<a class="cnt-btn decrease" target="<%=myCart.get(i).getId()%>"><</a>
				<a class="count <%=myCart.get(i).getId()%>"><%=myCart.get(i).getCount() %></a>
				<a class="cnt-btn increase" target="<%=myCart.get(i).getId()%>">></a>
			</div>
			<div class="">
				<a class="item-price <%=myCart.get(i).getId()%>" default-price="<%=myCart.get(i).getPrice()%>"><%=myCart.get(i).getTotal(true)%></a><a>��</a>
			</div>
			<div class="">
				<a href="/Carts/cart/remove?id=<%=myCart.get(i).getId()%>" onClick="javascript:return confirm('������ ����?');">����</a>
			</div>
		</li>
		<% } %>
		<li class="cart-item">
			<div></div>
			<div></div>
			<div></div>
			<div>
				<a class="item-total">0</a><a>��</a>
			</div>
			<div></div>
		</li>
	</ol>
	<% } else { %>
	<h3>��ٱ��ϰ� ����ֽ��ϴ�.</h3>
	<% } %>
	<script src="/Carts/js/service.js"></script>
	<script src="/Carts/js/ajaxCart.js"></script>
	<script src="/Carts/js/CartController.js"></script>
	<script>
		// AJAX
		// �ٷ� ��ٱ��Ͽ� �����ϸ� ������ ���ϴ�.....
		ajaxGetCartTotal();
	</script>
</body>
</html>