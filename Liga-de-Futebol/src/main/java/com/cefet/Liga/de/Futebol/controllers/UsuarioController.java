package com.cefet.Liga.de.Futebol.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.Liga.de.Futebol.dto.UsuarioDTO;
import com.cefet.Liga.de.Futebol.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
		UsuarioDTO dto = usuarioService.findById(id);
		return ResponseEntity.ok(dto);
	}	
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<UsuarioDTO> dtos = usuarioService.findAll();
		return ResponseEntity.ok(dtos);
	}	
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO dto) {
		UsuarioDTO novo = usuarioService.insert(dto);
		return ResponseEntity.status(201).body(novo); 
	}	
	
	/*
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
		UsuarioDTO salvo = usuarioService.update(id, dto);
		return ResponseEntity.ok(salvo); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		usuarioService.delete(id);
		return ResponseEntity.noContent().build(); 
	}
	*/
	
    @GetMapping("/existe")
    public ResponseEntity<Boolean> existsByLogin(@RequestParam String login) {
        boolean existe = usuarioService.existsByLogin(login);
        return ResponseEntity.ok(existe);
    }
	
	
}
