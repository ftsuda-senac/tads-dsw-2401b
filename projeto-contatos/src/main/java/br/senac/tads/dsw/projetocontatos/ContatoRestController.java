/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.projetocontatos;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/contatos")
@CrossOrigin(origins = "*")
public class ContatoRestController {
    
    @Autowired
    private ContatoService service;
    
    @GetMapping
    public List<Contato> listar() {
        return service.findAll();
    }
    
    @GetMapping("/{id}")
    public Contato consultar(@PathVariable("id") int id) {
//        Contato c = service.findById(id);
//        if (c == null) {
//            // Contato não encontrado
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
//                    "Contato com ID " + id + " não encontrado");
//        }
//        return c;
//        // Versão com optional
//        Optional<Contato> optContato = service.findByIdComOptional(id);
//        if (optContato.isEmpty()) {
//             throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//                     "Contato com ID " + id + " não encontrado");
//        }
//        return optContato.get();
        
        // Versão com optional funcional
        return service.findByIdComOptional(id).orElseThrow(() -> 
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                     "Contato com ID " + id + " não encontrado"));
    }
    
    @PostMapping
    public ResponseEntity<?> incluir(@RequestBody @Valid Contato c) {
        service.save(c);
//        return ResponseEntity.created(
//                URI.create("http://localhost:8080/contatos/consultar/" 
//                        + c.getId())).build();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(c.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable("id") int id,
            @RequestBody @Valid Contato c) {
        Optional<Contato> optContato = service.findByIdComOptional(id);
        if (optContato.isEmpty()) {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                     "Contato com ID " + id + " não encontrado");
        }
        c.setId(id); // Ignora o ID informado no JSON - ID da URL tem predominancia
        service.save(c);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") int id) {
        Optional<Contato> optContato = service.findByIdComOptional(id);
        if (optContato.isEmpty()) {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                     "Contato com ID " + id + " não encontrado");
        }
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
}
