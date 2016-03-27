package bsuir.soap.client.servlets;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import bsuir.soap.client.servlets.service.ServiceUtil;
import webService.soap.handbook.HandBookJS;
import webService.soap.handbook.model.DefenitionModel;

@WebServlet("/HandlerIndexServlet")
public class HandlerIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String searchParameter = request.getParameter("defenition");

		HandBookJS handbook = ServiceUtil.getServiceSOAP().getPort(HandBookJS.class);

		DefenitionModel defenition = handbook.searchDefenition(searchParameter);

		HttpSession session = request.getSession();
		response.setCharacterEncoding("UTF-8");
		if (defenition != null) {
			session.setAttribute("getDefenition", searchParameter + " - " + defenition.getBodyDefenition());
		} else {
			session.setAttribute("getDefenition", searchParameter + " - не найдено");
		}
		String redirectURL = "/SOAPWebClient/";
		response.sendRedirect(redirectURL);
	}

}
