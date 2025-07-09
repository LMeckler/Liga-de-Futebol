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

import com.cefet.Liga.de.Futebol.dto.ContratoDTO;
import com.cefet.Liga.de.Futebol.entities.Contrato;
import com.cefet.Liga.de.Futebol.repositories.ContratoRepository;

@RestController
@RequestMapping("/contratos")
public class ContratoController {
    @Autowired
    private ContratoRepository contratoRepository;

    @GetMapping
    public List<ContratoDTO> listar() {
        return contratoRepository.findAll().stream().map(ContratoDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratoDTO> buscar(@PathVariable Long id) {
        return contratoRepository.findById(id)
                .map(contrato -> ResponseEntity.ok(new ContratoDTO(contrato)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ContratoDTO criar(@RequestBody Contrato contrato) {
        return new ContratoDTO(contratoRepository.save(contrato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContratoDTO> atualizar(@PathVariable Long id, @RequestBody Contrato contrato) {
        return contratoRepository.findById(id)
                .map(c -> {
                    contrato.setId(id);
                    return ResponseEntity.ok(new ContratoDTO(contratoRepository.save(contrato)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!contratoRepository.existsById(id)) return ResponseEntity.notFound().build();
        contratoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
