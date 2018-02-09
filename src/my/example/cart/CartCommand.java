package my.example.cart;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartCommand implements CartService {
	final static ProductDAO cDAO = new ProductDAO();
	
	String itemID;

	@Override
	public int addCart(HttpServletRequest request, HttpServletResponse response) {
		// Request Check
		HttpSession session = request.getSession();
		if( request.getParameter("id") == null ) {
			return -1;
		}
		
		// 장바구니에 넣을 때 session이 없다면 생성해서 넣기
		if( session.getAttribute("cart") == null ) {
			session.setAttribute("cart", new ArrayList<ItemDTO>() );
		}
		
		String itemID = (String) request.getParameter("id");
		ArrayList<ItemDTO> myCart = (ArrayList<ItemDTO>) session.getAttribute("cart");

		// 장바구니에 아이템이 존재하는지 검사
		if( existItemInCart(myCart, itemID) != -1 ) {
			System.out.println("중복");
			return 0;
		}
		
		// add process
		myCart.add( cDAO.getProduct(itemID) );
		session.setAttribute("cart", myCart);
		System.out.println("[추가완료]");
		
		return 1;
	}

	@Override
	public int removeCart(HttpServletRequest request, HttpServletResponse response) {
		// Request Check
		HttpSession session = request.getSession();
		if( session.getAttribute("cart") == null || 
			request.getParameter("id") == null ) {
			return -1;
		}
		
		ArrayList<ItemDTO> myCart = (ArrayList<ItemDTO>) session.getAttribute("cart");
		String itemID = (String) request.getParameter("id");
		
		// 장바구니에 아이템이 존재하는 지 검사
		int itemIndex = existItemInCart(myCart, itemID);
		if( itemIndex == -1 ) {
			return 0;
		}
		
		// remove process
		myCart.remove( itemIndex );
		session.setAttribute("cart", myCart);
		System.out.println("[삭제 완료]");
		
		return 1;
	}
	
	// 장바구니에 아이템이 존재하는지 검사
	// 아이템이 존재한다면 index, 없다면 -1
	private int existItemInCart(ArrayList<ItemDTO> myCart, String itemID) {
		for(int i = 0; i < myCart.size(); i++) {
			if( myCart.get(i).getId().equals(itemID) ) {
				return i;
			}
		}
		return -1;
	}
}
