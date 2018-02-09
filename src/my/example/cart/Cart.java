package my.example.cart;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Cart
 */
@WebServlet("/cart/*")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		
		// default dispatcher
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Products.jsp");;
		
		String cPath = request.getContextPath();
		String uri = request.getRequestURI();
		String com = uri.substring(cPath.length());

		System.out.println("[com]"+com);
		if( com.equals("/cart/add") ) {
			CartCommand cmd = new CartCommand();
			cmd.addCart(request, response);
			// set default dispatcher
		} else if ( com.equals("/cart/remove") ) {
			CartCommand cmd = new CartCommand();
			cmd.removeCart(request, response);
			dispatcher = request.getRequestDispatcher("/Carts.jsp");
		}
		dispatcher.forward(request, response);
	}

}
