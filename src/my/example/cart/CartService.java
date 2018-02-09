package my.example.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CartService {
	public int addCart(HttpServletRequest request, HttpServletResponse response);
	public int removeCart(HttpServletRequest request, HttpServletResponse response);
}
