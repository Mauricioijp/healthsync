package security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import security.filter.GenerarTokenFilter;
import security.filter.LeerTokenFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationConfiguration auth;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return auth.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {

        //Se adiciona STATELESS para que no guarde sesion
        http.sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // Para pruebas con Postman/JSON, deshabilitamos CSRF en este API REST
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests((authz) -> {
                    //Logica para traer de la bd los roles y accesos
                    //for( ... ){
                    //    authz.requestMatchers("...").hasRole("...");
                    //}
                    // Endpoints públicos
                    authz.requestMatchers(HttpMethod.GET, "/security").permitAll();
                    authz.requestMatchers(HttpMethod.GET, "/users").permitAll();
                    authz.requestMatchers(HttpMethod.GET, "/users/**").permitAll();
                    //authz.requestMatchers(HttpMethod.GET, "/api/usuarios/test/**").permitAll();
                    //authz.requestMatchers(HttpMethod.GET, "/api/usuarios/validar/**").authenticated();
                    authz.requestMatchers(HttpMethod.GET, "/patients").hasAuthority("ADMIN");
                    authz.requestMatchers(HttpMethod.GET, "/patients/**").hasAuthority("ADMIN");
                    authz.requestMatchers(HttpMethod.POST, "/patients/**").hasAuthority("ADMIN");
                    authz.requestMatchers(HttpMethod.PUT, "/patients/**").hasAuthority("ADMIN"); //ROLE_ADMIN si en el loadUserByUsername usamos "ROLE_"+rol sino solo "ADMIN"
                    authz.requestMatchers(HttpMethod.DELETE, "/patients/**").hasAuthority("ADMIN"); //ROLE_ADMIN si en el loadUserByUsername usamos "ROLE_"+rol sino solo "ADMIN"
                    
                    authz.requestMatchers(HttpMethod.GET, "/appointments").hasAuthority("ADMIN");
                    authz.requestMatchers(HttpMethod.GET, "/appointments/**").hasAuthority("ADMIN");
                    authz.requestMatchers(HttpMethod.POST, "/appointments/**").hasAuthority("ADMIN");
                    authz.requestMatchers(HttpMethod.PUT, "/appointments/**").hasAuthority("ADMIN");
                    authz.requestMatchers(HttpMethod.DELETE, "/appointments/**").hasAuthority("ADMIN");


                    authz.anyRequest().authenticated();
            }
            );
        //Habilitar Basic Auth
        //http.httpBasic(Customizer.withDefaults());
        //Agregar filtro para generar token
        http.addFilterBefore(new GenerarTokenFilter("/security", authenticationManager()), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new LeerTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    //@Bean
    //public BCryptPasswordEncoder passwordEncoder() {
    //    return new BCryptPasswordEncoder();
    //}
}
