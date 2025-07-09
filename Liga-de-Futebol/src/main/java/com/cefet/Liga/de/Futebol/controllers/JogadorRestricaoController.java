package com.cefet.Liga.de.Futebol.controllers;

import java.util.List;

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

import com.cefet.Liga.de.Futebol.dto.JogadorRestricaoDTO;
import com.cefet.Liga.de.Futebol.services.JogadorRestricaoService;

@RestController
@RequestMapping("/jogadorrestricoes")
public class JogadorRestricaoController {
    @Autowired
    private JogadorRestricaoService jogadorRestricaoService;

    @GetMapping
    public List<JogadorRestricaoDTO> listar() {
        return jogadorRestricaoService.findAll();
    }

    @GetMapping("/jogador/{jogadorId}")
    public List<JogadorRestricaoDTO> listarPorJogador(@PathVariable Long jogadorId) {
        return jogadorRestricaoService.findByJogadorId(jogadorId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogadorRestricaoDTO> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(jogadorRestricaoService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public JogadorRestricaoDTO criar(@RequestBody JogadorRestricaoDTO dto) {
        return jogadorRestricaoService.insert(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JogadorRestricaoDTO> atualizar(@PathVariable Long id, @RequestBody JogadorRestricaoDTO dto) {
        try {
            return ResponseEntity.ok(jogadorRestricaoService.update(id, dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            jogadorRestricaoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
