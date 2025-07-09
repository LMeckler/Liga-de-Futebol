package com.cefet.Liga.de.Futebol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cefet.Liga.de.Futebol.entities.Usuario;
import com.cefet.Liga.de.Futebol.repositories.UsuarioRepository;
import com.cefet.Liga.de.Futebol.security.UsuarioDetails;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado: " + login));
        return new UsuarioDetails(usuario);
    }
}