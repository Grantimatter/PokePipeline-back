package com.revature.pokepipeline.servlets.filters;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokepipeline.models.Users;

import org.apache.commons.io.IOUtils;

/**
 * This is an all in one authentication filter which can be used to validate
 * sessions and validate session and username combinations
 */
public class SessionAuthFilter extends HttpFilter {

    private static ObjectMapper objectParser;

    private static String VALIDATE_SESSION_ONLY = "/PokePipeline/auth";

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("using auth filter");
        String sessionURI = req.getRequestURI();
        HttpSession reqSession = req.getSession(false);

        if (reqSession == null) {
            res.setStatus(401);
        } else {

            res.setStatus(200);

        }

    }

}
