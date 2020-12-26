package com.revature.pokepipeline.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthController {

	public void login(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		
	}

	public void logout(HttpServletRequest req, HttpServletResponse res) 
			throws IOException {
		
		if (req.getMethod().equals("PUT")) {
			
			req.getSession().invalidate();
			
			res.setStatus(200);
		}
	}

}
