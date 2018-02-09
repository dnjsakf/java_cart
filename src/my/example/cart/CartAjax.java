package my.example.cart;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CartAjax
 */
@WebServlet("/cart/ajax/*")
public class CartAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartAjax() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doAction(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doAction(request, response);
	}
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ctxPath = request.getContextPath();
		String uri = request.getRequestURI();
		String com = uri.substring(ctxPath.length());
		String data = "";
		
		if( com.equals("/cart/ajax/item") ) {
			ArrayList<ItemDTO> myCart = (ArrayList<ItemDTO>) request.getSession().getAttribute("cart");
			String itemID = request.getParameter("itemID");
			int count = Integer.parseInt(request.getParameter("count"));
			for(int i = 0; i < myCart.size(); i++) {
				if( myCart.get(i).getId().equals(itemID) ) {
					int price = Integer.parseInt(myCart.get(i).getPrice());
					myCart.get(i).setCount( count );
					myCart.get(i).setTotal( count * price );
					request.getSession().setAttribute("cart", myCart);
					break;
				}
			}
			checkItem( request, response );
			
		} else if ( com.equals("/cart/ajax/total") ) {
			ArrayList<ItemDTO> myCart = (ArrayList<ItemDTO>) request.getSession().getAttribute("cart");
			int total = 0;
			for(int i = 0; i < myCart.size(); i++) {
				total += Integer.parseInt( myCart.get(i).getTotal(false) );
			}
			data = String.valueOf(total);
		}
		response.getWriter().print(data);
		response.setStatus(200);
	}
	
	void checkItem(HttpServletRequest request, HttpServletResponse response) {
		/**
		 * 제대로 들어갔는지 확인
		 */
		ArrayList<ItemDTO> myCart = (ArrayList<ItemDTO>) request.getSession().getAttribute("cart");
		String itemID = request.getParameter("itemID");
		String count = request.getParameter("count");
		for(int i = 0; i < myCart.size(); i++) {
			if( myCart.get(i).getId().equals(itemID) ) {
				System.out.println("[session-count]" + myCart.get(i).getCount() );
				System.out.println("[session-total]" + myCart.get(i).getTotal(true) );
				break;
			}
		}
	}
}
