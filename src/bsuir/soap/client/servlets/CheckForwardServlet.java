package bsuir.soap.client.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CheckForwardServlet")
public class CheckForwardServlet extends DispetcherServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("search") != null) {
			super.forward("/HandlerIndexServlet", request, response);
		} else if (request.getParameter("add") != null || request.getParameter("update") != null
				|| request.getParameter("delete") != null) {
			// request.setCharacterEncoding("UTF-8");
			super.forward("/HandlerEditServlet", request, response);
		}
	}

}
