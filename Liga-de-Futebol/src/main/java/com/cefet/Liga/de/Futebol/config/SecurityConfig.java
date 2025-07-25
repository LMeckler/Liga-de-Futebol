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
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll() // Acesso ao Swagger UI
                        // USUARIOS
                        .requestMatchers(HttpMethod.POST, "/usuarios").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll() // Permitir endpoint de login
                        .requestMatchers(HttpMethod.GET, "/usuarios").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/usuarios/{id}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/usuarios/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasAnyRole("ADMIN")
                        // Pessoas
                        .requestMatchers(HttpMethod.GET, "/pessoas").permitAll()
                        .requestMatchers(HttpMethod.GET, "/pessoas/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/pessoas").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/pessoas/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/pessoas/**").hasAnyRole("ADMIN")
                        // Cidades
                        .requestMatchers(HttpMethod.GET, "/cidades").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cidades/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/cidades").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/cidades/**").hasAnyRole("ADMIN")  
                        .requestMatchers(HttpMethod.DELETE, "/cidades/**").hasAnyRole("ADMIN")
                        // Estadios
                        .requestMatchers(HttpMethod.GET, "/estadios").permitAll()
                        .requestMatchers(HttpMethod.GET, "/estadios/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/estadios").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/estadios/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/estadios/**").hasAnyRole("ADMIN")
                        // Times
                        .requestMatchers(HttpMethod.GET, "/times").permitAll()
                        .requestMatchers(HttpMethod.GET, "/times/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/times").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/times/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/times/**").hasAnyRole("ADMIN")
                        // Contratos
                        .requestMatchers(HttpMethod.GET, "/contratos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/contratos/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/contratos").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/contratos/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/contratos/**").hasAnyRole("ADMIN")
                        // TimeRestricoes
                        .requestMatchers(HttpMethod.GET, "/timerestricoes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/timerestricoes/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/timerestricoes").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/timerestricoes/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/timerestricoes/**").hasAnyRole("ADMIN")
                        // JogadoresRestricoes
                        .requestMatchers(HttpMethod.GET, "/jogadorrestricoes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/jogadorrestricoes/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/jogadorrestricoes").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/jogadorrestricoes/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/jogadorrestricoes/**").hasAnyRole("ADMIN")
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