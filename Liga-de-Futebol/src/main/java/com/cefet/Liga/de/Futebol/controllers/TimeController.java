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

import com.cefet.Liga.de.Futebol.dto.TimeDTO;
import com.cefet.Liga.de.Futebol.entities.Time;
import com.cefet.Liga.de.Futebol.repositories.TimeRepository;

@RestController
@RequestMapping("/times")
public class TimeController {
    @Autowired
    private TimeRepository timeRepository;

    @GetMapping
    public List<TimeDTO> listar() {
        return timeRepository.findAll().stream().map(TimeDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeDTO> buscar(@PathVariable Long id) {
        return timeRepository.findById(id)
                .map(time -> ResponseEntity.ok(new TimeDTO(time)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TimeDTO criar(@RequestBody Time time) {
        return new TimeDTO(timeRepository.save(time));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeDTO> atualizar(@PathVariable Long id, @RequestBody Time time) {
        return timeRepository.findById(id)
                .map(t -> {
                    time.setId(id);
                    return ResponseEntity.ok(new TimeDTO(timeRepository.save(time)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!timeRepository.existsById(id)) return ResponseEntity.notFound().build();
        timeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
