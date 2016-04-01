package bsuir.soap.client.servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import bsuir.soap.client.servlets.service.SharedService;



@WebServlet("/HandlerEditServlet")
public class HandlerEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String inputName = new String(request.getParameter("editNameDefenition").getBytes("ISO-8859-1"), "UTF-8");
		String inputBody = new String(request.getParameter("editDefenition").getBytes("ISO-8859-1"), "UTF-8");

		if (request.getParameter("add") != null) {
			doAdd(inputName,inputBody);
		} else if (request.getParameter("update") != null) {
			doUpadate(inputName,inputBody);
		} else if (request.getParameter("delete") != null) {
			doDelete(inputName);
		}
		String redirectURL = "/SOAPWebClient/edit.html";
		response.sendRedirect(redirectURL);
	}
	
	private void doAdd(String inputName, String inputBody) {
		try {
			TTransport transport;

			transport = new TSocket("localhost", 9090);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			SharedService.Client client = new SharedService.Client(protocol);

			client.addDefenition(inputName, inputBody);

			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}
	}
	
	private void doUpadate(String inputName, String inputBody) {
		try {
			TTransport transport;

			transport = new TSocket("localhost", 9090);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			SharedService.Client client = new SharedService.Client(protocol);

			client.updateDefenition(inputName, inputBody);
			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}
	}
	
	private void doDelete(String inputName) {
		try {
			TTransport transport;

			transport = new TSocket("localhost", 9090);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			SharedService.Client client = new SharedService.Client(protocol);

			client.deleteDefenition(inputName);
			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}
	}
	

}
