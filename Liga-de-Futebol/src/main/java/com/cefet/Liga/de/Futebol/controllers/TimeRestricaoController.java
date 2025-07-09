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

import com.cefet.Liga.de.Futebol.dto.TimeRestricaoDTO;
import com.cefet.Liga.de.Futebol.services.TimeRestricaoService;

@RestController
@RequestMapping("/timerestricoes")
public class TimeRestricaoController {
    @Autowired
    private TimeRestricaoService timeRestricaoService;

    @GetMapping
    public List<TimeRestricaoDTO> listar() {
        return timeRestricaoService.findAll();
    }

    @GetMapping("/time/{timeId}")
    public List<TimeRestricaoDTO> listarPorTime(@PathVariable Long timeId) {
        return timeRestricaoService.findByTimeId(timeId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeRestricaoDTO> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(timeRestricaoService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public TimeRestricaoDTO criar(@RequestBody TimeRestricaoDTO dto) {
        return timeRestricaoService.insert(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeRestricaoDTO> atualizar(@PathVariable Long id, @RequestBody TimeRestricaoDTO dto) {
        try {
            return ResponseEntity.ok(timeRestricaoService.update(id, dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            timeRestricaoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
