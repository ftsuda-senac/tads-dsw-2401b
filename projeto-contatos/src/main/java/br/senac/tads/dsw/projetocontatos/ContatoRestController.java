/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.projetocontatos;

import java.time.LocalDate;
import java.util.ArrayList;
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

@RestController
@RequestMapping("/contatos")
public class ContatoRestController {
    
    @Autowired
    private ContatoService service;
    
    @GetMapping("/lista")
    public List<Contato> listar() {
        return service.findAll();
    }
    
    @GetMapping("/consulta/{id}")
    public Contato consultar(@PathVariable("id") int id) {
        return service.findById(id);
    }
    
    
    @PostMapping("/incluir")
    public ResponseEntity<?> incluir(@RequestBody Contato c) {
        service.save(c);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterar(@PathVariable("id") int id,
            @RequestBody Contato c) {
        c.setId(id); // Ignora o ID informado no JSON - ID da URL tem predominancia
        service.save(c);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") int id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
}
