package com.wkfs.calcEngineWebApp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.wkfsfrc.ce.AppCore.Calculator;

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

public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	




	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private Calculator calculator;
	public void init() {
		calculator= new Calculator();
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
			String JSON=request.getParameter("input");
			System.out.println(JSON);
			JSONObject jsonObjectCalc;
			try {
				jsonObjectCalc= new JSONObject(JSON);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
		//out.println("This is the doPost() method!");
		doGet(request, response);

	}

}
