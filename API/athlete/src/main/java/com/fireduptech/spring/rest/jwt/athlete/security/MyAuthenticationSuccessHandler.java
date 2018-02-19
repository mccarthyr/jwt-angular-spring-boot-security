package com.fireduptech.spring.rest.jwt.athlete.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();


    @Override
    public void onAuthenticationSuccess( final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication ) throws ServletException, IOException {

        final SavedRequest savedrequest = requestCache.getRequest( request, response );

        if ( savedrequest == null ) {
            clearAuthenticationAttributes( request );
            return;
        }

        final String targetUrlParameter = getTargetUrlParameter();
        if ( isAlwaysUseDefaultTargetUrl() || ( targetUrlParameter != null && StringUtils.hasText( request.getParameter( targetUrlParameter ) ) ) ) {
            requestCache.removeRequest( request, response );
            clearAuthenticationAttributes( request );
            return;
        }

        clearAuthenticationAttributes( request );

        // NOTE: The parent class would have redirection logic here that is not required in a REST system
    }


    public void setRequestCache( final RequestCache requestCache ) {
        this.requestCache = requestCache;
    }

}
