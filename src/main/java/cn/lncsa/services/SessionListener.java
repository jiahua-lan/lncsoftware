package cn.lncsa.services;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by catten on 16/4/25.
 */
public class SessionListener implements HttpSessionListener {
    private static int sessionCount = 0;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        sessionCount++;

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        sessionCount--;
    }

    public static int getSessionCount(){
        return sessionCount;
    }
}
