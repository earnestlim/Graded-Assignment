

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		String na = request.getParameter("Name");
		int pa = Integer.parseInt(request.getParameter("quantity"));
		String s = request.getParameter("size");
		String co = request.getParameter("color");
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection con = DriverManager.getConnection(
			 "jdbc:mysql://localhost:3306/ProductInformation", "root", "password");
			 PreparedStatement ps = con.prepareStatement("insert into PRODUCTINFORMATION values(?,?,?,?)");
		
			 ps.setString(1, na);
			 ps.setInt(2, pa);
			 ps.setString(3, s);
			 ps.setString(4, co);

			 int i = ps.executeUpdate();

			 if (i > 0){
				 request.getRequestDispatcher("/ProductList.jsp").forward(request, response);
				 }
		}
			 catch (Exception exception) {
				 System.out.println(exception);
				 out.close();
				}
				doGet(request, response);
	}

}
