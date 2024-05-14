/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.projetocontatos.security;

import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        var passwordEncoder = new DelegatingPasswordEncoder("noop", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(NoOpPasswordEncoder.getInstance());
        return passwordEncoder;
    }

    @Bean
    AuthenticationManager authenticationManager(UsuarioService usuarioService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usuarioService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authProvider);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // @formatter:off
        return http
                .cors(cors -> Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(fo -> fo.sameOrigin()))
                .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/swagger-ui.html",
                        "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .anyRequest().authenticated())
                .build();
        // @formatter:on

    }
}
