package bsuir.soap.client.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bsuir.soap.client.servlets.service.ServiceUtil;
import webService.soap.handbook.HandBookJS;
import webService.soap.handbook.model.DefenitionModel;

@WebServlet("/HandlerEditServlet")
public class HandlerEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		DefenitionModel defenitionModel = new DefenitionModel();

		defenitionModel.setNameDefenition(
				new String(request.getParameter("editNameDefenition").getBytes("ISO-8859-1"), "UTF-8"));
		defenitionModel
				.setBodyDefenition(new String(request.getParameter("editDefenition").getBytes("ISO-8859-1"), "UTF-8"));
		HandBookJS handbook = ServiceUtil.getServiceSOAP().getPort(HandBookJS.class);

		if (request.getParameter("add") != null) {
			handbook.addDefenition(defenitionModel);

		} else if (request.getParameter("update") != null) {
			handbook.updateDefenition(defenitionModel);
		} else if (request.getParameter("delete") != null) {
			handbook.deleteDefenition(defenitionModel.getNameDefenition());
		}
		String redirectURL = "/SOAPWebClient/edit.html";
		response.sendRedirect(redirectURL);
	}

}
