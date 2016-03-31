package bsuir.soap.client.servlets;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import bsuir.soap.client.servlets.service.ServiceUtil;
import webService.soap.handbook.HandBookJS;
import webService.soap.handbook.model.DefenitionModel;

@WebServlet("/HandlerEditServlet")
public class HandlerEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_AGENT = "Mozilla/5.0";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		DefenitionModel defenitionModel = new DefenitionModel();

		defenitionModel.setNameDefenition(
				new String(request.getParameter("editNameDefenition").getBytes("ISO-8859-1"), "UTF-8"));
		defenitionModel
				.setBodyDefenition(new String(request.getParameter("editDefenition").getBytes("ISO-8859-1"), "UTF-8"));
		// HandBookJS handbook =
		// ServiceUtil.getServiceSOAP().getPort(HandBookJS.class);

		if (request.getParameter("add") != null) {
			String str = new String("{\"value\": \"" + defenitionModel.getBodyDefenition() + "\", \"name\": \""
					+ defenitionModel.getNameDefenition() + "\"}");

			try {
				URL url = new URL("http://192.168.137.34:8000/one/");
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setRequestProperty("User-Agent", USER_AGENT);
				connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
				//connection.setConnectTimeout(5000);
				//connection.setReadTimeout(5000);

				DataOutputStream out = new DataOutputStream(connection.getOutputStream());
				out.writeBytes(str);
				out.flush();
				out.close();
				System.out.println(connection.getResponseCode());

			} catch (Exception e) {
				System.out.println("\nError while calling Crunchify REST Service");
				System.out.println(e);
			}

		} else if (request.getParameter("update") != null) {
			// handbook.updateDefenition(defenitionModel);
		} else if (request.getParameter("delete") != null) {
			// handbook.deleteDefenition(defenitionModel.getNameDefenition());
		}
		String redirectURL = "/SOAPWebClient/edit.html";
		response.sendRedirect(redirectURL);
	}

}
