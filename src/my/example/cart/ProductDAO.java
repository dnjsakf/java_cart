package my.example.cart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductDAO {
	
	private Connection conn;
	
	public ProductDAO(){
		String url = "jdbc:mysql://localhost:3306/bbs";
		String user = "heo";
		String password = "wjddns1";
		String driver = "com.mysql.jdbc.Driver";
		
		try {
			try {
				Class.forName(driver);
			} catch ( Exception e ) {
				e.printStackTrace();
			}
			conn = DriverManager.getConnection(url, user, password);
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	
	public ItemDTO getProduct(String productID) {
		String SQL = "SELECT * FROM cart WHERE id = ?";
		try {
			PreparedStatement pd = conn.prepareStatement(SQL);
			pd.setString(1, productID);
			
			ResultSet result = pd.executeQuery();
			if( result.next() ){
				return new ItemDTO( result.getString(1), result.getString(2), result.getString(3), result.getString(4), 1);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<ItemDTO> getProductList() {
		String SQL = "SELECT * FROM cart";
		try {
			PreparedStatement pd = conn.prepareStatement(SQL);
			ResultSet result = pd.executeQuery();
			
			ArrayList<ItemDTO> productList = new ArrayList<ItemDTO>();
			while( result.next() ) {
				ItemDTO item = new ItemDTO(result.getString(1), result.getString(2), result.getString(3), result.getString(4));
				productList.add(item);
			}
			return productList;
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return null;
	}
}
