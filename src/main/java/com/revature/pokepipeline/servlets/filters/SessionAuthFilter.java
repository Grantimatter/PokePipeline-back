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

    private static String VALIDATE_SESSION_ONLY = "/auth";

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        String sessionURI = null;

        if (req.getSession(false) == null) {
            res.setStatus(401);
        } else {
            sessionURI = req.getRequestURI(); // get session URI

            // if this is only a valid session check, send back a 200
            // since the user has a valid session.
            if (sessionURI.equals(VALIDATE_SESSION_ONLY))
                res.setStatus(200);
            // Otherwise, the user is trying to access a sensitive resource and we need to
            // ensure they not only posses the session
            // but posses the username associated with the session.
            else if (SessionAuthenticateUsers(req)) {
                chain.doFilter(req, res);

            }
        }

    }

    /**
     * Parse Users attempts to parse a Users passed in a request body.
     * 
     * @param req the httpservletrequest of the user
     * @return If not null parsing suceeded. If null, parsing failed. Return 400.
     */
    private Users parseUsers(HttpServletRequest req) {

        Users UsersToAuthenticate = null;
        String body = null;

        try {
            body = IOUtils.toString(req.getReader());
            UsersToAuthenticate = objectParser.readValue(body, Users.class);

        } catch (Exception e) {
        } finally {

        }

        return UsersToAuthenticate;
    }

    /**
     * This method performs username to session authentication. It checks that the
     * user user posses a valid session and the user posseses the username
     * associated with that session. This ensures that one user cannot alter
     * another's details, unless they happen to know the other users session id.
     * 
     * @param the req to authenticate.
     * @returns boolean indicating the succes or failure of authentication.
     */

    private boolean SessionAuthenticateUsers(HttpServletRequest req) {

        boolean authenticated = false;
        HttpSession usersSession = req.getSession(false);
        Users user = parseUsers(req);
        String sessionUsername = (String) usersSession.getAttribute("username");

        if (usersSession != null && user != null) {
            authenticated = user.getUsername().equals(sessionUsername);
        }

        return authenticated;
    }

}
