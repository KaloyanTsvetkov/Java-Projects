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
 * Servlet implementation class CalculateWithdraw
 */
@WebServlet("/CalculateWithdraw")
public class CalculateWithdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CalculateWithdraw() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/BankSystem/MainServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String output = " <!doctype html public \"-//w3c//dtd html 5.0" + "transitional//en\">\n";

		String tokenRequest = request.getParameter("token");
		int id = Integer.valueOf(request.getParameter("compID"));
		String sumText = request.getParameter("sum2");

		String tokenDB = Queries.getTokenByID(id);

		if (tokenRequest.equals(tokenDB) && (request.getSession().getAttribute("tokenSesion")) != null) {
			if (!sumText.isEmpty()) {
				try {
					double sum = Double.valueOf(sumText);
					if (sum > 0) {
						boolean result = Queries.withdrawSum(id, sum);
						if (result) {
							JSONObject jsonOb = new JSONObject().put("token", tokenRequest).put("ammount", sum);
							out.println(output + "<html><head><title>Succes Registration!</title></head>"
									+ "<body bgcolor=\"#f0f0f0\"><BR><BR>"
									+ "<form action=\"/BankSystem/BankServlet\" method=\"POST\">" + "<CENTER>"
									+ "<p>" + jsonOb.toString() + "</p><BR><BR>"
									+ "You was successfully withdraw " + sum + "<BR>from your balance." + "<BR>"
									+ "<input type=\"hidden\" name=\"compID\" value=\"" + id + "\" />"
									+ "<input type=\"hidden\" name=\"token\" value=\"" + tokenRequest + "\" />"
									+ "<input type=\"submit\" value=\"Back\" /><BR><BR>" + "</CENTER>"
									+ "</form></body></html>");
						} else {
							out.println(output + "<html><head><title>Succes Registration!</title></head>"
									+ "<body bgcolor=\"#f0f0f0\"><BR><BR>"
									+ "<form action=\"/BankSystem/BankServlet\" method=\"POST\">" + "<CENTER>"
									+ "You can't withdraw " + sum + "<BR>from your balance." + "<BR>"
									+ "<BR>Please check balnce of your account." + "<BR>"
									+ "<input type=\"hidden\" name=\"compID\" value=\"" + id + "\" />"
									+ "<input type=\"hidden\" name=\"token\" value=\"" + tokenRequest + "\" />"
									+ "<input type=\"submit\" value=\"Back\" /><BR><BR>" + "</CENTER>"
									+ "</form></body></html>");
						}
					} else {
						out.println(output + "<html><head><title>Succes Registration!</title></head>"
								+ "<body bgcolor=\"#f0f0f0\"><BR><BR>"
								+ "<form action=\"/BankSystem/BankServlet\" method=\"POST\">" + "<CENTER>"
								+ "To withdraw money, sum must be Greater than 0.00"
								+ "<input type=\"hidden\" name=\"compID\" value=\"" + id + "\" />"
								+ "<input type=\"hidden\" name=\"token\" value=\"" + tokenRequest + "\" />"
								+ "<input type=\"submit\" value=\"Back\" /><BR><BR>" + "</CENTER>"
								+ "</form></body></html>");
					}
				} catch (NumberFormatException e) {
					out.println(output + "<html><head><title></title></head>" + "<body bgcolor=\"#F8ECEE\">\n"
							+ "<form action=\"/BankSystem/BankServlet\" method=\"POST\">" + "<CENTER>"
							+ "Must enter integer to withdraw" + "<BR><BR>"
							+ "<input type=\"hidden\" name=\"compID\" value=\"" + id + "\" />"
							+ "<input type=\"hidden\" name=\"token\" value=\"" + tokenRequest + "\" />"
							+ "<input type=\"submit\" value=\"Back\" />" + "</CENTER>" + "</form></body></html>");
				}

			} else {
				out.println(output + "<html><head><title></title></head>" + "<body bgcolor=\"#F8ECEE\">\n"
						+ "<form action=\"/BankSystem/BankServlet\" method=\"POST\">" + "<CENTER>"
						+ "Must enter sum to withdraw" + "<BR><BR>" + "<input type=\"hidden\" name=\"compID\" value=\""
						+ id + "\" />" + "<input type=\"hidden\" name=\"token\" value=\"" + tokenRequest + "\" />"
						+ "<input type=\"submit\" value=\"Back\" />" + "</CENTER>" + "</form></body></html>");
			}
		} else {
			Queries.setToken(id, "");
			response.sendRedirect("/BankSystem/MainServlet");
		}
	}

}
