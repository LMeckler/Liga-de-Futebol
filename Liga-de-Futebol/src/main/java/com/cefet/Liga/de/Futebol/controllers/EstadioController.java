package com.cefet.Liga.de.Futebol.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.Liga.de.Futebol.dto.EstadioDTO;
import com.cefet.Liga.de.Futebol.entities.Estadio;
import com.cefet.Liga.de.Futebol.repositories.EstadioRepository;

@RestController
@RequestMapping("/estadios")
public class EstadioController {
    @Autowired
    private EstadioRepository estadioRepository;

    @GetMapping
    public List<EstadioDTO> listar() {
        return estadioRepository.findAll().stream().map(EstadioDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadioDTO> buscar(@PathVariable Long id) {
        return estadioRepository.findById(id)
                .map(estadio -> ResponseEntity.ok(new EstadioDTO(estadio)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public EstadioDTO criar(@RequestBody Estadio estadio) {
        return new EstadioDTO(estadioRepository.save(estadio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadioDTO> atualizar(@PathVariable Long id, @RequestBody Estadio estadio) {
        return estadioRepository.findById(id)
                .map(e -> {
                    estadio.setId(id);
                    return ResponseEntity.ok(new EstadioDTO(estadioRepository.save(estadio)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!estadioRepository.existsById(id)) return ResponseEntity.notFound().build();
        estadioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
