package bsuir.soap.client.servlets;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.json.JSONException;
import org.json.JSONObject;

import bsuir.soap.client.servlets.service.ServiceUtil;
import webService.soap.handbook.HandBookJS;
import webService.soap.handbook.model.DefenitionModel;

@WebServlet("/HandlerIndexServlet")
public class HandlerIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_AGENT ="Mozilla/5.0";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String searchParameter = request.getParameter("defenition");
		searchParameter.trim();	

		String defenitionName = null;
		String bodyValue = null;
		StringBuilder inputBuilder = new StringBuilder();
		// Step2: Now pass JSON File Data to REST Service
	try {
			URL url = new URL("http://192.168.137.34:8000/one/"+searchParameter);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("User-Agent", USER_AGENT);
			connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			BufferedReader in = new BufferedReader(new  InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				inputBuilder.append(line);
			}
			//in.reset();
			in.close();

			JSONObject jsonObject = new JSONObject(inputBuilder.toString());
			
			defenitionName = jsonObject.getString("name");
			bodyValue = jsonObject.getString("value");
			System.out.println(jsonObject);
			
		} catch (Exception e) {
			System.out.println("\nError while calling Crunchify REST Service");
			System.out.println(e);
		}

		HttpSession session = request.getSession();
		response.setCharacterEncoding("UTF-8");
		if (defenitionName != null ||  bodyValue != null) {
			session.setAttribute("getDefenition", defenitionName + " - " + bodyValue);
		} else {
			session.setAttribute("getDefenition","не найдено");
		}
		String redirectURL = "/SOAPWebClient/";
		response.sendRedirect(redirectURL);
	}

}
