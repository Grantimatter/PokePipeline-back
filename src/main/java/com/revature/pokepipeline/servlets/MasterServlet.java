package com.revature.pokepipeline.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.pokepipeline.controllers.AuthController;

public class MasterServlet extends HttpServlet {
	
	private final AuthController authController = new AuthController();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		res.setContentType("application/json");
		res.setStatus(404);

		final String URI = req.getRequestURI().replace("/pokepipeline/", "");
		
		switch (URI) {
		case "login":
			authController.login(req, res);
			break;
			
		case "logout":
			authController.logout(req, res);
			break;
			
		default:
			break;
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doGet(req, res);
	}
	
	protected void doPut(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doGet(req, res);
	}

}
