package my.example.cart;

public class ItemDTO {
	private String id;
	private String title;
	private String price;
	private String date;

	private int count = 0;
	private int total = 0;
	
	public ItemDTO(String id, String title, String price, String date){
		this.id = id;
		this.title = title;
		this.price = price;
		this.date = date;

		this.total = Integer.parseInt(price);	// 원래는 price도 Integer형이여야되는데, 바꾸면 에러나서 일단은 그냥 진행하는 걸로..
	}
	public ItemDTO(String id, String title, String price, String date, int count) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.date = date;
		this.count = count;
		
		this.total = Integer.parseInt(price);
	}

	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	public String getTotal(boolean pattern) {
		return pattern ? getPatternPrice( this.total ) : String.valueOf(this.total);
	}
	public String getPatternPrice( int price ){
		String nonePattern = String.valueOf(price);
		if( price == -1 ) {
			nonePattern = this.price;
		}
		String result = "";
		String reverse = "";
		int cnt = 0;
		for(int i = nonePattern.length(); i > 0; i--){
		    cnt += 1;
		    reverse += nonePattern.charAt(i-1);
			if( cnt % 3 == 0 && i != 1 ){
				reverse += ",";
		    }	
		}
		for(int i = reverse.length()-1; i >= 0; i--){
			result += reverse.charAt(i);
		}
		return result;
	}
}
