package com.example.json.config;

import com.example.json.soap.SoapClient;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class AuthFilter implements Filter {

    @Autowired
    private SoapClient soapClient;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest  req = (HttpServletRequest)  request;
        HttpServletResponse res = (HttpServletResponse) response;

        String authHeader = req.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("Token байхгүй байна.");
            return;
        }

        String token = authHeader.substring(7);
        boolean valid = soapClient.validateToken(token);

        if (!valid) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("Token буруу эсвэл хугацаа дууссан.");
            return;
        }

        chain.doFilter(request, response);
    }
}