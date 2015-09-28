package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import controler.Queries;

/**
 * Servlet implementation class ConfirmLogIn
 */
@WebServlet("/ConfirmLogIn")
public class ConfirmLogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfirmLogIn() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/BankSystem/MainServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String usName = request.getParameter("userName");
		String pass = request.getParameter("pass");
		
		String output = " <!doctype html public \"-//w3c//dtd html 5.0"
				+ "transitional//en\">";

		if (!usName.isEmpty() && !pass.isEmpty()) {
			int result = Queries.logInUser(usName, pass);
			if (result>0) {				
				String token = Queries.getUniqueToken();
				Queries.setToken(result, token);
				JSONObject jsonOb = new JSONObject().put("status", "ok").put("token", token);
				out.println(output
						+ "<html><head><title>Succes Registration!</title></head>"
						+ "<body bgcolor=\"#f0f0f0\"><BR><BR>"
						+ "<form action=\"/BankSystem/BankServlet\" method=\"POST\">"
						+ "<p>" + jsonOb.toString() + "</p>"
						+ "<CENTER>"
						+ "<input type=\"hidden\" name=\"compID\" value=\""+ result +"\" />"
						+ "<input type=\"hidden\" name=\"token\" value=\""+ token +"\" />"
						+ "<input type=\"submit\" value=\"Submit\" /><BR><BR>"
						+ "</CENTER>" + "</form></body></html>");
			} else {
				out.println(output
						+ "<html><head><title></title></head>"
						+ "<body bgcolor=\"#F8ECEE\">\n"
						+ "<form action=\"/BankSystem/MainServlet\" method=\"GET\">"
						+ "<CENTER>" + "Wrong username or password!"
						+ "<BR><BR>"
						+ "<input type=\"submit\" value=\"Back\" />"
						+ "</CENTER>" + "</form></body></html>");
			}
		}else{
			out.println(output
					+ "<html><head><title></title></head>"
					+ "<body bgcolor=\"#F8ECEE\">\n"
					+ "<form action=\"/BankSystem/MainServlet\" method=\"GET\">"
					+ "<CENTER>" + "Wrong username or password!"
					+ "<BR><BR>"
					+ "<input type=\"submit\" value=\"Back\" />"
					+ "</CENTER>" + "</form></body></html>");
		}
	}

}
