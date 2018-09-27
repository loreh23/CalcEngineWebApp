package com.wkfs.calcEngineWebApp.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wkfs.calcEngineWebApp.model.Operation;
import com.wkfs.calcEngineWebApp.util.calcWebAppUtil;
import com.wkfsfrc.ce.AppCore.Calculator;
import com.wkfsfrc.ce.Exception.InvalidStatementException;

/**
 * Servlet implementation class HelloWorld
 */
// RequestDispatcher dispatcher =
// request.getRequestDispatcher("/BookAdmin.jsp");
// dispatcher.forward(request, response);
// we need this two when we want to send to another page

// request.setAttribute("books", books_list);
// this sets the name of the attribute from .jsp to the passed object

// requests are requests that have data that needs to be processed, ex saving a
// book, a req holds the data that needs to be saved
// after that the controller processes that data and
// the response returns a way for us to see that the data has been processed

@RestController
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String result = null;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private Calculator calculator;

	public void init() {
		calculator = new Calculator();
		Properties classes = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(".\\configFile.properties");
			classes.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		calculator.setMathOperationsList(classes);
	}

	public ControllerServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getServletPath();

		try {
			switch (action) {
			case "/CalcWebApp":
				showCalculatorView(request, response);
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showCalculatorView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/calculatorView.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuilder jsonBuilder = new StringBuilder();
		ObjectMapper mapper = new ObjectMapper();

		String JSON = request.getParameter("input");

		JSONArray jsonArray;
		try {
			if (new JSONTokener(JSON).nextValue() instanceof JSONArray) {
				jsonArray = (JSONArray) new JSONTokener(JSON).nextValue();
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jObject = jsonArray.getJSONObject(i);
					calcWebAppUtil.calculate(jObject, calculator, jsonBuilder, mapper);
				}
			} else {
				JSONObject jObject= new JSONObject(JSON);
				calcWebAppUtil.calculate(jObject, calculator, jsonBuilder, mapper);

				
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		result = jsonBuilder.toString();
		request.setAttribute("result", result);
		doGet(request, response);

	}



}
