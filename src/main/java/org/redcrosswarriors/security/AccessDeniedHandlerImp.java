/*
Implementation for our access denied handler

 */
package org.redcrosswarriors.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessDeniedHandlerImp implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException, ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String redirectLink = "/accessDenied";
        if(auth != null){
            boolean isUnverified = auth.getAuthorities().stream().anyMatch(
                    r -> r.getAuthority().equals("ROLE_UNVERIFIED")
            );
            boolean isAdmin = auth.getAuthorities().stream().anyMatch(
                    r -> r.getAuthority().equals("ROLE_ADMIN")
            );

            if(isUnverified){
                redirectLink +="?notVerified=true";
            }
            else if(!isAdmin){
                redirectLink +="?notAdmin=true";
            }
        }
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + redirectLink);
    }
}
