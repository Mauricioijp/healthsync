package apim.util;

import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    private static SecretKey SECRET_KEY;

    public JwtUtil(@Value("${security.jwt.secret-key}") String secretKeyString) {
        this.SECRET_KEY = Keys.hmacShaKeyFor(secretKeyString.getBytes());
    }


    // Método para validar el token enviado por el cliente
    public static Authentication leerToken(String token) {

        try {

            Claims claims = Jwts.parser()
                    .verifyWith((SecretKey) SECRET_KEY)
                    .build()
                    .parseSignedClaims(token.replace("Bearer ", ""))
                    .getPayload();

            String user = claims.getSubject();
            String role = claims.get("role", String.class);

            List<GrantedAuthority> roles =
                    List.of(new SimpleGrantedAuthority(role));

            return new UsernamePasswordAuthenticationToken(user, null, roles);

        } catch (Exception e) {

            return null;
        }
    }
}
