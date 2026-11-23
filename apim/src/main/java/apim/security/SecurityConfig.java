package apim.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import apim.filter.LeerTokenFilter;

@Configuration
public class SecurityConfig {



    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return (SecurityWebFilterChain) http
                .csrf(csrf -> csrf.disable())
                .authorizeExchange(exchange -> exchange
                        
                        // Endpoints públicos
                        .pathMatchers(HttpMethod.POST, "/security").permitAll()
                        .pathMatchers(HttpMethod.GET, "/users/**").permitAll()
                        //.pathMatchers(HttpMethod.GET, "/users/**").permitAll()
                        //.pathMatchers(HttpMethod.GET, "/api/usuarios/validar/**").permitAll()
                        //.pathMatchers(HttpMethod.GET, "/api/eventos").permitAll()
                        //.pathMatchers(HttpMethod.GET, "/api/eventos/").permitAll()
                        //.pathMatchers(HttpMethod.GET, "/api/eventos/buscar").permitAll()
                        //.pathMatchers(HttpMethod.GET, "/api/categorias").permitAll()
                        //.pathMatchers(HttpMethod.GET, "/api/tiposentrada").permitAll()
                        // Solo ADMIN
                        //.pathMatchers(HttpMethod.GET, "/users/**").hasAuthority("ADMIN")
                        //.pathMatchers(HttpMethod.GET, "/users").hasAuthority("ADMIN")
                        //.pathMatchers(HttpMethod.POST, "/users/**").hasAuthority("ADMIN")
                        //.pathMatchers(HttpMethod.PUT, "/users/**").hasAuthority("ADMIN")
                        //.pathMatchers(HttpMethod.DELETE, "/users/**").hasAuthority("ADMIN")

                        .pathMatchers(HttpMethod.GET, "/patients/**").hasAuthority("ADMIN")
                        .pathMatchers(HttpMethod.GET, "/patients").hasAuthority("ADMIN")
                        .pathMatchers(HttpMethod.POST, "/patients/**").hasAuthority("ADMIN")
                        .pathMatchers(HttpMethod.PUT, "/patients/**").hasAuthority("ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "/patients/**").hasAuthority("ADMIN")

                        .pathMatchers(HttpMethod.GET, "/appointments/**").hasAuthority("ADMIN")
                        .pathMatchers(HttpMethod.POST, "/appointments/**").hasAuthority("ADMIN")
                        .pathMatchers(HttpMethod.PUT, "/appointments/**").hasAuthority("ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "/appointments/**").hasAuthority("ADMIN")

                        //.pathMatchers(HttpMethod.GET, "/api/categorias/**").hasAuthority("ADMIN")
                        //.pathMatchers(HttpMethod.POST, "/api/categorias").hasAuthority("ADMIN")
                        //.pathMatchers(HttpMethod.POST, "/api/categorias/**").hasAuthority("ADMIN")
                        //.pathMatchers(HttpMethod.PUT, "/api/categorias/**").hasAuthority("ADMIN")
                        //.pathMatchers(HttpMethod.DELETE, "/api/categorias/**").hasAuthority("ADMIN")
                        
                        //.pathMatchers(HttpMethod.GET, "/api/usuarios").hasAuthority("ADMIN")
                        //.pathMatchers(HttpMethod.GET, "/api/usuarios/buscar").hasAuthority("ADMIN")
                        //.pathMatchers(HttpMethod.POST, "/api/usuarios/**").hasAuthority("ADMIN")
                        //.pathMatchers(HttpMethod.PUT, "/api/usuarios/**").hasAuthority("ADMIN")
                        //.pathMatchers(HttpMethod.DELETE, "/api/usuarios/**").hasAuthority("ADMIN")


                        // resto autenticado
                        .anyExchange().authenticated()
                )
                .httpBasic(basic -> basic.disable())      // Desactiva Basic Auth
                .formLogin(form -> form.disable())        // Desactiva Form Login
                .addFilterBefore(new LeerTokenFilter(),SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }

    @Bean //es para poder inyectar la dependencia del BCryptPasswordEncoder en otras clases
    public  BCryptPasswordEncoder passwordEncoder() { //Condificar los datos en la BD
        return new BCryptPasswordEncoder();
    }

}
