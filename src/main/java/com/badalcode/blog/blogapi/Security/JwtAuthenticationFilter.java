package com.badalcode.blog.blogapi.Security;

//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String requestToken = getJwtFromRequest(request);
            System.out.println();
            System.out.println(requestToken);

            if (requestToken != null && requestToken.startsWith("Bearer ")) {
                // Proceed with token validation and authentication
                System.out.println("Token: " + requestToken);
                // Add your token validation logic here
            } else {
                // Log and handle invalid or missing token
                if (requestToken == null) {
                    logger.warn("Missing JWT token");
                } else {
                    logger.warn("Invalid JWT token format");
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            logger.error("Error processing JWT token", ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing JWT token");
        }
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }


}
