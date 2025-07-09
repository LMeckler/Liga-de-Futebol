package com.cefet.Liga.de.Futebol.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.Liga.de.Futebol.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
    boolean existsByLogin(String login);
    
    Optional<Usuario> findByLogin(String login);
}