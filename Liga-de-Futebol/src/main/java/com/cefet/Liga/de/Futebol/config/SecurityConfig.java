package com.cefet.Liga.de.Futebol.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cefet.Liga.de.Futebol.security.JwtAuthenticationFilter;
import com.cefet.Liga.de.Futebol.services.UsuarioDetailsService;

@Configuration
public class SecurityConfig {
    @Autowired
    private UsuarioDetailsService usuarioDetailsService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            //Desabilita verificação CSRF para permitir POST com token JWT
                .cors().and()  
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // Acesso ao H2 Console
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll() // Acesso
                                                                                                              // ao
                                                                                                              // Swagger
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll() // Permitir criação de usuário
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll() // Permitir endpoint de login
                        .requestMatchers(HttpMethod.GET, "/usuarios").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/usuarios/{id}").hasAnyRole("ADMIN", "USER")
                        // Pessoas
                        .requestMatchers(HttpMethod.GET, "/pessoas").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/pessoas/{id}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/pessoas").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/pessoas/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/pessoas/**").hasRole("ADMIN")
                        // Cidades
                        .requestMatchers(HttpMethod.GET, "/cidades/{id}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/cidades").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/cidades/**").hasRole("ADMIN")  
                        .requestMatchers(HttpMethod.DELETE, "/cidades/**").hasRole("ADMIN")
                        // Estadios
                        .requestMatchers(HttpMethod.GET, "/estadios").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/estadios/{id}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/estadios").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/estadios/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/estadios/**").hasRole("ADMIN")
                        // Times
                        .requestMatchers(HttpMethod.GET, "/times").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/times/{id}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/times").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/times/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/times/**").hasRole("ADMIN")
                        // Contratos
                        .requestMatchers(HttpMethod.GET, "/contratos").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/contratos/{id}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/contratos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/contratos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/contratos/**").hasRole("ADMIN")
                        // TimeRestricoes
                        .requestMatchers(HttpMethod.GET, "/timerestricoes").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/timerestricoes/{id}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/timerestricoes").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/timerestricoes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/timerestricoes/**").hasRole("ADMIN")
                        // JogadoresRestricoes
                        .requestMatchers(HttpMethod.GET, "/jogadorrestricoes").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/jogadorrestricoes/{id}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/jogadorrestricoes").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/jogadorrestricoes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/jogadorrestricoes/**").hasRole("ADMIN")
                        .anyRequest().authenticated() // Todos os outros endpoints exigem autenticação
                )
                .headers(headers -> headers.frameOptions().disable()) // Para H2 Console
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usuarioDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

     @Bean
	  public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurer() {
	      @Override
	      public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	          .allowedOrigins("http://localhost:4200")
	          .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
	          .allowedHeaders("*")
	          .allowCredentials(true);
	      }
	    };
	  }        
}