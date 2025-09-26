package app.security;

import app.GlobalConfig;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    @Autowired
    GlobalConfig globalConfig;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();

        if (HttpMethod.OPTIONS.matches(request.getMethod()) ||
                this.globalConfig.SECURED_PATHS.stream().noneMatch(servletPath::startsWith)){
            filterChain.doFilter(request, response);
            return;
        }
        // get the encrypted token string from the authorization request header
        String encryptedToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        // block the request if no token was found
        if (encryptedToken == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No token provided. You need to login first.");
            return;
        }

        JWToken jwToken = null;
        try {
            jwToken = JWToken.decode(encryptedToken.replace("Bearer ", ""), this.globalConfig.getPassphrase());
        } catch (RuntimeException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage() + " You need to logon first.");
            return;
        }

        request.setAttribute(JWToken.JWT_ATTRIBUTE_NAME, jwToken);

        filterChain.doFilter(request, response);

    }
}
