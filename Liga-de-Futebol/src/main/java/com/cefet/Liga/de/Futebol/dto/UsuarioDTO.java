package com.cefet.Liga.de.Futebol.dto;

import com.cefet.Liga.de.Futebol.entities.NivelAcesso;
import com.cefet.Liga.de.Futebol.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioDTO {

    private Long id; 
    private String login;
    //impede que a senha seja exposta
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
    private NivelAcesso nivelAcesso;

    public UsuarioDTO() {
    }    
    
    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.login = usuario.getLogin();
        this.senha = usuario.getSenha();
        this.nivelAcesso = usuario.getNivelAcesso();
    }
    
	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public NivelAcesso getNivelAcesso() {
		return nivelAcesso;
	}    
}