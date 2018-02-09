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
		
		// ��ٱ��Ͽ� ���� �� session�� ���ٸ� �����ؼ� �ֱ�
		if( session.getAttribute("cart") == null ) {
			session.setAttribute("cart", new ArrayList<ItemDTO>() );
		}
		
		String itemID = (String) request.getParameter("id");
		ArrayList<ItemDTO> myCart = (ArrayList<ItemDTO>) session.getAttribute("cart");

		// ��ٱ��Ͽ� �������� �����ϴ��� �˻�
		if( existItemInCart(myCart, itemID) != -1 ) {
			System.out.println("�ߺ�");
			return 0;
		}
		
		// add process
		myCart.add( cDAO.getProduct(itemID) );
		session.setAttribute("cart", myCart);
		System.out.println("[�߰��Ϸ�]");
		
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
		
		// ��ٱ��Ͽ� �������� �����ϴ� �� �˻�
		int itemIndex = existItemInCart(myCart, itemID);
		if( itemIndex == -1 ) {
			return 0;
		}
		
		// remove process
		myCart.remove( itemIndex );
		session.setAttribute("cart", myCart);
		System.out.println("[���� �Ϸ�]");
		
		return 1;
	}
	
	// ��ٱ��Ͽ� �������� �����ϴ��� �˻�
	// �������� �����Ѵٸ� index, ���ٸ� -1
	private int existItemInCart(ArrayList<ItemDTO> myCart, String itemID) {
		for(int i = 0; i < myCart.size(); i++) {
			if( myCart.get(i).getId().equals(itemID) ) {
				return i;
			}
		}
		return -1;
	}
}
