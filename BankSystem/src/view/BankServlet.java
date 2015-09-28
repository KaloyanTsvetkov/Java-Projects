package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controler.Queries;

/**
 * Servlet implementation class BankServlet
 */
@WebServlet("/BankServlet")
public class BankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BankServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/BankSystem/MainServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
				
		PrintWriter out = response.getWriter();
		
		String tokenRequest = request.getParameter("token");
		int id = Integer.valueOf(request.getParameter("compID"));
		String tokenDB = "";		
		synchronized (this) {
			request.getSession().setAttribute("tokenSesion", tokenRequest);
			request.getSession().setMaxInactiveInterval(10);//10 sec.
			tokenDB = Queries.getTokenByID(id);
		}

		String output = " <!doctype html public \"-//w3c//dtd html 5.0"
				+ "transitional//en\">\n";
		
		if(tokenRequest.equals(tokenDB) && (request.getSession().getAttribute("tokenSesion"))!=null){
			out.println(output + "<html><head><title></title></head>"
					+ "<body bgcolor=\"#f0f0f0\">\n"
					+ "<form action=\"/BankSystem/CalculateDeposit\" method=\"POST\">"
					+ "<CENTER>"
					+ "Please enter the sum:<BR>"
					+ "<INPUT TYPE=\"TEXT\" NAME=\"sum\"><BR>"
					+ "<BR><input type=\"submit\" value=\"Deposit\" />"
					+ "<input type=\"hidden\" name=\"compID\" value=\""+ id +"\" />"
					+ "<input type=\"hidden\" name=\"token\" value=\""+ tokenRequest +"\" />"
					+ "</CENTER>" + "</form>"
					+ "<BR><form action=\"/BankSystem/CalculateWithdraw\" method=\"POST\">"
					+ "<CENTER>"
					+ "Please enter the sum:<BR>"
					+ "<INPUT TYPE=\"TEXT\" NAME=\"sum2\"><BR>"
					+ "<input type=\"submit\" value=\"Withdraw\" />"
					+ "<input type=\"hidden\" name=\"compID\" value=\""+ id +"\" />"
					+ "<input type=\"hidden\" name=\"token\" value=\""+ tokenRequest +"\" />"
					+ "</CENTER>" + "</form>" 
					+ "<BR><form action=\"/BankSystem/GetBalance\" method=\"POST\">"
					+ "<CENTER>"
					+ "<input type=\"submit\" value=\"See Balance\" />"
					+ "<input type=\"hidden\" name=\"compID\" value=\""+ id +"\" />"
					+ "<input type=\"hidden\" name=\"token\" value=\""+ tokenRequest +"\" />"
					+ "</CENTER>" + "</form>" 
					+ "</form></body></html>");
		}else{
			Queries.setToken(id, "");
			response.sendRedirect("/BankSystem/MainServlet");
		}
		
	}

}
