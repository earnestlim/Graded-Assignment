

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LOGINSERVLET
 */
@WebServlet("/LOGINSERVLET")
public class LOGINSERVLET extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LOGINSERVLET() {
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

		String n = request.getParameter("userName");
		String p = request.getParameter("password");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/userdetails", "root", "password");
			PreparedStatement ps = con.prepareStatement("SELECT name,password,AR from UserDetails where name =?");


			// Step 3: Execute the query or update query
			ps.setString(1, n);
			ResultSet rs = ps.executeQuery();

			// Step 4: Process the ResultSet object

			while (rs.next()) {
				String Db_PW = rs.getString("password");
				int AR = rs.getInt("AR");
				if(p.equals(Db_PW)) {
					if (AR==1){
						response.sendRedirect("http://localhost:8080/HelloWorldJavaEE/UserServlet/dashboard");

//						request.getRequestDispatcher("/UserServlet/dashboard").forward(request, response);
					} else {
						response.sendRedirect("http://localhost:8080/HelloWorldJavaEE/ProductListServlet/dashboard");
					}
					
				} else {
					System.out.println("Error! Wrong Password");
					break;
				}
			}
		}	
		catch (Exception exception) {
			System.out.println(exception);
			out.close();
		}
		doGet(request, response);
	}

}
