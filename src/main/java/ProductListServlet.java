import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
/**
 * Servlet implementation class ProductListServlet
 */
@WebServlet("/ProductListServlet")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//Step 1: Prepare list of variables used for database connections

	private String jdbcURL = "jdbc:mysql://localhost:3306/userdetails";
	private String jdbcProductname = "root";
	private String jdbcPassword = "password";

	//Step 2: Prepare list of SQL prepared statements to perform CRUD to our database

	private static final String INSERT_ProductS_SQL = "INSERT INTO productinformation" + " (Name, quantity, size, color) VALUES " + " (?, ?, ?,?);";
	private static final String SELECT_Product_BY_ID = "SELECT Name, quantity, size, color from productinformation where name =?";
	private static final String SELECT_ALL_ProductS = "select Name, quantity, size, color from productinformation";
	private static final String DELETE_ProductS_SQL = "delete from productinformation where name = ?;";
	private static final String UPDATE_ProductS_SQL = "update productinformation set name = ?,quantity= ?, size =? where color = ?;";

	//Step 3: Implement the getConnection method which facilitates connection to the database via JDBC

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcProductname, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	//Step 5: listProducts function to connect to the database and retrieve all Products records

	private void listProducts(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Product> Products = new ArrayList<>();
		try (Connection connection = getConnection();

				// Step 5.1: Create a statement using connection object

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ProductS);) {

			// Step 5.2: Execute the query or update query

			ResultSet rs = preparedStatement.executeQuery();

			// Step 5.3: Process the ResultSet object.

			while (rs.next()) {
				String name = rs.getString("name");
				int quantity = rs.getInt("quantity");
				String size = rs.getString("size");
				String color = rs.getString("color");
				String material = rs.getString("material");
				Products.add(new Product(name, quantity, size, color,material));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// Step 5.4: Set the Products list into the listProducts attribute to be pass to the
		// ProductManagement.jsp

		request.setAttribute("listProducts", Products);
		request.getRequestDispatcher("/ProductList.jsp").forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		// get parameter passed in the URL

		String name = request.getParameter("name");
		Product existingProduct = new Product("", 0, "","","");

		// Step 1: Establishing a Connection

		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_Product_BY_ID);) {
			preparedStatement.setString(1, name);

			// Step 3: Execute the query or update query

			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object

			while (rs.next()) {
				name = rs.getString("name");
				int quantity = rs.getInt("quantity");
				String size = rs.getString("size");
				String color = rs.getString("color");
				String material = rs.getString("material");
				existingProduct = new Product(name, quantity,size, color,material);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		// Step 5: Set existingProduct to request and serve up the ProductEdit form

		request.setAttribute("Product", existingProduct);
		request.getRequestDispatcher("/ProductEdit.jsp").forward(request, response);
	}

	// method to update the Product table base on the form data

	private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

		// Step 1: Retrieve value from the request

		String oriName = request.getParameter("oriName");
		String name = request.getParameter("ProductName");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		// Step 2: Attempt connection with database and execute update Product SQL query

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ProductS_SQL);) {
			statement.setString(1, name);
			statement.setString(2, password);
			statement.setString(3, email);
			statement.setString(4, oriName);
			int i = statement.executeUpdate();
		}

		// Step 3: redirect back to ProductListServlet (note: remember to change the url to your project name)

		response.sendRedirect("http://localhost:8080/HelloWorldJavaEE/ProductListServlet/dashboard");
	}

	// method to delete Product

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// Step 1: Retrieve value from the request

		String name = request.getParameter("name");

		// Step 2: Attempt connection with database and execute delete Product SQL query

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ProductS_SQL);) {
			statement.setString(1, name);
			int i = statement.executeUpdate();
		}

		// Step 3: redirect back to ProductListServlet dashboard (note: remember to change the url to your project name)

		response.sendRedirect("http://localhost:8080/HelloWorldJavaEE/ProductListServlet/dashboard");
	}

	// method to trigger RegisterServlet

	private void addNewProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		String n = request.getParameter("name");
		String p = request.getParameter("quantity");
		String e = request.getParameter("color");
		String c = request.getParameter("size");
		try (Connection connection = getConnection();

				// Step 5.1: Create a statement using connection object

			PreparedStatement ps = connection.prepareStatement(INSERT_ProductS_SQL);) 
		{
//		try {
//			 Class.forName("com.mysql.jdbc.Driver");
//			 Connection con = DriverManager.getConnection(
//			 "jdbc:mysql://localhost:3306/productinformation", "root", "password");
//			 PreparedStatement ps = con.prepareStatement(INSERT_ProductS_SQL);
		
			 ps.setString(1, n);
			 ps.setString(2, p);
			 ps.setString(3, e);
			 ps.setString(4, c);

			 int i = ps.executeUpdate();

			 if (i > 0){
				 PrintWriter writer = response.getWriter();
				 writer.println("<h1>" + "You have successfully added a product!" +
				 "</h1>");
				 writer.close();
				 }
		}
			 catch (Exception exception) {
				 System.out.println(exception);
				 out.close();
				}
//				doGet(request, response);
	}


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductListServlet() {
		super();
		// TODO Auto-generated constructor stub

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//Step 4: Depending on the request servlet path, determine the function to invoke using the follow switch statement.

		String action = request.getServletPath();
		System.out.println(action);
		try {
			switch (action) {

			case "/ProductListServlet/delete":
				deleteProduct(request, response);
				break;

			case "/ProductListServlet/edit":
				showEditForm(request, response);
				break;

			case "/ProductListServlet/update":
				updateProduct(request, response);
				break;

			case "/ProductListServlet/dashboard":
				listProducts(request, response);
				break;

			case "/ProductListServlet/insert":
				addNewProduct(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}