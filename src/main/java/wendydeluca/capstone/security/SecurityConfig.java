package wendydeluca.capstone.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // 1. vado a disabilitare il form di login di default
        httpSecurity.formLogin(http ->http.disable());
        // 2. disabilito la protezione da csrf (un tipo di attaccho)
        httpSecurity.csrf(http->http.disable());
        //3. disabilito le sessioni in quanto l'autenticazione con token é stateless
        httpSecurity.sessionManagement(http->http.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // 4. vado ad autorizzare le richieste su tutti gli endpoint, poiché di default non sono autorizzate
        httpSecurity.authorizeHttpRequests(http->http.requestMatchers("/**").permitAll());
        httpSecurity.cors(Customizer.withDefaults());

        return httpSecurity.build();// ritorno un oggetto di tipo SecurityFilterChain

    }

    @Bean
    PasswordEncoder getBCrypt(){
        return new BCryptPasswordEncoder(11);
        // lo utilizzo in fase di registrazione e login, x cui, vado a crearmi il riferimento in UserService
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setAllowedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",config);
        return source;
        // configurazione per collegamento con front end
    }
}
