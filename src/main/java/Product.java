
public class Product {

	 private String material;
	public Product(String name, int quantity, String size , String color, String material) {

	 public Product(String name, int quantity, String size , String color, String material) {

		super();
		this.name = name;
		this.quantity = quantity;
		this.size = size;
		this.color = color;
		this.material = material;
	
		
	}
	protected String name;
	protected int quantity;
	protected String size;
	protected String color;
	protected String material;
		// TODO Auto-generated constructor stub
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getquantity() {
		return quantity;
	}
	public void setquantity(int quantity) {
		this.quantity = quantity;
	}
	public String getsize() {
		return size;
	}
	public void setsize(String size) {
		this.size = size;
	}
	public String getcolor() {
		return size;
	}
	public void setcolor(String color) {
		this.color = color;
	}
		public String getmaterial() {
			return size;
		}
		public void setmaterial(String material) {
			this.material = material;
	
	}
}
	 
	 
