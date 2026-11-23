package security.filter;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.servlet.FilterChain;
import jakarta.servlet.GenericFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import security.util.JwtUtil;

public class LeerTokenFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        System.out.println("LeerTokenFilter/doFilter");
        Authentication auth = JwtUtil.leerToken((HttpServletRequest) request);
        SecurityContextHolder.getContext().setAuthentication(auth);
        chain.doFilter(request, response);
    }
    
}
