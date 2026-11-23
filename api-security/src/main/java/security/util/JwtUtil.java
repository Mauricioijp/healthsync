package security.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtUtil {
    
    private static SecretKey SECRET_KEY;

    public JwtUtil(@Value("${security.jwt.secret-key}") String secretKey) {
        SECRET_KEY = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    //method to generate JWT token and add it to the response header
    public static void generarToken(HttpServletResponse response, Authentication authResult) {
        String token = Jwts.builder()
                .subject("User Data")
                .claim("username", authResult.getName())
                .claim("role", authResult.getAuthorities().stream()
                                .findFirst()
                                .map(GrantedAuthority::getAuthority)
                                .orElse(""))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .signWith(SECRET_KEY, Jwts.SIG.HS256)
                .compact();
        response.addHeader("Authorization", "Bearer " + token);
    }

    //method to validate JWT token sent by the client
    public static Authentication leerToken(HttpServletRequest request){
        String user;
        String role="USER";
        String token = request.getHeader("Authorization");
        if(token != null){
            try{
                user=Jwts.parser()
                        .verifyWith((SecretKey)SECRET_KEY).build()
                        //token
                        .parseSignedClaims(token.replace("Bearer ", "").trim())
                        .getPayload()
                        .get("userName", String.class);

                role = Jwts.parser()
                        .verifyWith((SecretKey)SECRET_KEY).build()
                        //token
                        .parseSignedClaims(token.replace("Bearer ", "").trim())
                        .getPayload()
                        .get("role", String.class);
            } catch (Exception ex) {
                System.out.println("Error : " + ex.getMessage());
                user=null;
            }
            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority(role));
            return user != null ? new UsernamePasswordAuthenticationToken(user, null, roles) : null;
        }
        return null;
    }
}
