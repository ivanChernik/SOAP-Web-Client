package bsuir.soap.client.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import bsuir.soap.client.servlets.service.SharedService;

@WebServlet("/HandlerIndexServlet")
public class HandlerIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String searchParameter = new String(request.getParameter("defenition").getBytes("ISO-8859-1"), "UTF-8");
		searchParameter.trim();

		String responseString = doSearch(searchParameter);

		HttpSession session = request.getSession();
		response.setCharacterEncoding("UTF-8");

		session.setAttribute("getDefenition", responseString);
		String redirectURL = "/SOAPWebClient/";
		response.sendRedirect(redirectURL);
	}

	private String doSearch(String inputName) {
		String responseString = null;
		try {
			TTransport transport;

			transport = new TSocket("localhost", 9090);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			SharedService.Client client = new SharedService.Client(protocol);

			responseString = client.getDefenition(inputName);

			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}
		return responseString;
	}

}
