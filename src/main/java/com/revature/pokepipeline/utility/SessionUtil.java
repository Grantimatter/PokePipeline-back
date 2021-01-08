package com.revature.pokepipeline.utility;

import com.revature.pokepipeline.models.Trainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {

    static Logger log = LogManager.getLogger(SessionUtil.class);

    public static boolean setupLoginSession(HttpServletRequest req, Trainer trainer){
        HttpSession session = req.getSession();
        trainer.setPassword(null);
        session.setAttribute("trainerLoggedIn", trainer);
        if(getTrainerFromSession(req) != null) {
            log.debug(String.format("%s logged in!", trainer.getTrainerName()));
            return true;
        }
        return false;
    }

    public static Trainer getTrainerFromSession(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if(session != null) {
            try {
                Trainer trainer = (Trainer) session.getAttribute("trainerLoggedIn");

                if (trainer != null) {
                    log.info(String.format("Trainer \"%s\" retrieved from session", trainer.getTrainerName()));
                    return trainer;
                } else {
                    session.invalidate();
                }
            } catch (NumberFormatException e) {
                log.error(e.getMessage(), e);
            }
        }
        log.warn("No trainer is logged in");
        return null;
    }
}
