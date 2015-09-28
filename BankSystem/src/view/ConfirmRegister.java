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
 * Servlet implementation class ConfirmRegister
 */
@WebServlet("/ConfirmRegister")
public class ConfirmRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfirmRegister() {
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
		String pass = request.getParameter("passw");
		String companyNAme = request.getParameter("compName");

		int result = Queries.addUser(companyNAme, usName, pass);
		String output = " <!doctype html public \"-//w3c//dtd html 5.0"
				+ "transitional//en\">";

		if (result>0) {
			String token = Queries.getUniqueToken();
			Queries.setToken(result, token);
			out.println(output
					+ "<html><head><title>Succes Registration!</title></head>"
					+ "<body bgcolor=\"#f0f0f0\"><BR><BR>"
					+ "<form action=\"/BankSystem/BankServlet\" method=\"POST\">"
					+ "<CENTER><BR><BR>"
					+"Your account was Created<BR><BR>"
					+ "<input type=\"hidden\" name=\"compID\" value=\""+ result +"\" />"
					+ "<input type=\"hidden\" name=\"token\" value=\""+ token +"\" />"
					+ "<input type=\"submit\" value=\"Submit\" /><BR><BR>"
					+ "</CENTER>" + "</form></body></html>");
		} else {
			if (result == -3) {
				out.println(output
						+ "<html><head><title></title></head>"
						+ "<body bgcolor=\"#F8ECEE\">\n"
						+ "<form action=\"/BankSystem/RegistrationForm\" method=\"GET\">"
						+ "<CENTER>"
						+ "This username is in use!<BR><BR>" + usName
						+"<BR><BR>"
						+ "<input type=\"submit\" value=\"Back\" />"
						+ "</CENTER>" + "</form></body></html>");
			}else{
				if(result == -1){
					out.println(output
							+ "<html><head><title></title></head>"
							+ "<body bgcolor=\"#F8ECEE\">\n"
							+ "<form action=\"/BankSystem/RegistrationForm\" method=\"GET\">"
							+ "<CENTER>"
							+ "Allready have a company with this name!<BR><BR>"+companyNAme
							+"<BR><BR>"
							+ "<input type=\"submit\" value=\"Back\" />"
							+ "</CENTER>" + "</form></body></html>");
				}else{
					out.println(output
							+ "<html><head><title></title></head>"
							+ "<body bgcolor=\"#F8ECEE\">\n"
							+ "<form action=\"/BankSystem/RegistrationForm\" method=\"GET\">"
							+ "<CENTER>"
							+ "Sorry DataBase error! Try later.\n"
							+"<BR><BR>"
							+ "<input type=\"submit\" value=\"Back\" />"
							+ "</CENTER>" + "</form></body></html>");
				}
			}

		}
	}

}
