package ch.es.pl.auth;

import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private LoginBean loginBean;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        LOGGER.info("Auth Request  {} : {}", request.getMethod(), request.getRequestURI());
        final String requestTokenHeader = request.getHeader("Authorization");
        System.out.println("requestTokenHeader: " + requestTokenHeader);
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            String jwtToken = requestTokenHeader.substring(7);
            try {
                String login = jwtTokenUtil.getUsernameFromToken(jwtToken);
                System.out.println("login from filter: "+login);
                loginBean.setLogin(login);
                chain.doFilter(request, response);
                return;
            } catch (IllegalArgumentException e) {
                System.out.println("Impossible de récupérer le token JWT");
            } catch (ExpiredJwtException e) {
                System.out.println("Le token JWT a expiré");
            }
        } else {
            logger.warn("Le token JWT ne commence par avec le string 'Bearer '");
        }
        String error = "JWT Token invalide";
        response.reset();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentLength(error .length());
        response.getWriter().write(error);
    }

}
