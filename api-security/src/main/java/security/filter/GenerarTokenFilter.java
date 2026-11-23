package security.filter;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import security.entity.UserEntity;
import security.util.JwtUtil;

public class GenerarTokenFilter extends AbstractAuthenticationProcessingFilter {

    String role = "";

    public GenerarTokenFilter(String url, AuthenticationManager authenticationManager) {
        super(request -> request.getServletPath().equals(url));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        System.out.println("GenerarTokenFilter/attemptAuthentication");
        InputStream body = request.getInputStream();
        UserEntity user = new ObjectMapper().readValue(body, UserEntity.class);
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUser(), user.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        JwtUtil.generarToken(response, authResult);
    }


}
