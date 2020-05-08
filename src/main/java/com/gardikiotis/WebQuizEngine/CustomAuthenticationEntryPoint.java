package com.gardikiotis.WebQuizEngine;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

public class CustomAuthenticationEntryPoint  extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        res.setContentType("application/json;charset=UTF-8");
        res.addHeader("WWW-Authenticate","Basic realm=\"\" + getRealmName() + \"\"");
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        res.getWriter().write("{   \"timestamp\":" +timestamp+",\n"+
                " \"status\":401," +
                "\"error\": \"Unauthorized\",\n" +
                "\"message\": \"Wrong username and/or password.\",   }"

        );
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("WebQuiz");
        super.afterPropertiesSet();
    }

}