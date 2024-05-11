package br.com.marcoshssilva.springbooteureka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity()
@EnableWebSecurity
public class WebSecurityConfiguration {

    static final String[] AUTH_WHITELIST = { "/actuator/**" };

    @Bean
    SecurityFilterChain securityFilterChainConfigure(HttpSecurity http) throws Exception {
        http.csrf(CsrfConfigurer::disable);
        http.sessionManagement(sessionConfigurer -> sessionConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(httpRequestsConfigurer -> httpRequestsConfigurer.requestMatchers(AUTH_WHITELIST).permitAll().anyRequest().authenticated());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

}