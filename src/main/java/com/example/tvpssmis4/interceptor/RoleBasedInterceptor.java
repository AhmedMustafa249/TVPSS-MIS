package com.example.tvpssmis4.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RoleBasedInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        String requestURI = request.getRequestURI();

        // If not logged in, redirect to login
        if (role == null) {
            response.sendRedirect("/login");
            return false;
        }

        // Check if user has access to the requested path
        if (requestURI.startsWith("/gpm/") && !role.equals("GPM") ||
                requestURI.startsWith("/ppd/") && !role.equals("PPD") ||
                requestURI.startsWith("/jpnj/") && !role.equals("JPNJ")) {
            response.sendRedirect("/access-denied");
            return false;
        }

        return true;
    }
}
