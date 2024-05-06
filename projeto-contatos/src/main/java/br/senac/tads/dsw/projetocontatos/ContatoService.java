/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.projetocontatos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ContatoService {
    
    private static int sequenciaId = 0;
    
    private Map<Integer, ContatoDto> contatos;
    
    public ContatoService() {
        contatos = new LinkedHashMap<>();
        int id = ++sequenciaId;
        contatos.put(id, new ContatoDto(id, "Fulano da Silva", 
                "fulano@email.com.br", "(11) 98765-1234", 
                LocalDate.parse("2000-10-20")));
        id = ++sequenciaId;
        contatos.put(id, new ContatoDto(id, "Ciclano de Souza", 
                "ciclano@email.com.br", "(11) 99001-3344", 
                LocalDate.parse("1999-05-16")));
        id = ++sequenciaId;
        contatos.put(id, new ContatoDto(id, "Beltrana dos Santos", 
                "beltrana@email.com.br", "(11) 91028-5432", 
                LocalDate.parse("2001-07-04")));
    }
    
    public List<ContatoDto> findAll() {
        return new ArrayList<>(contatos.values());
    }
    
    public ContatoDto findById(int id) {
        return contatos.get(id);
    }
    
    public Optional<ContatoDto> findByIdComOptional(int id) {
        return Optional.ofNullable(contatos.get(id));
    }
    
    
    
    
    public void save(ContatoDto c) {
        if (c.getId() == null) {
            // Considera inclusão de novo contato
            int id = ++sequenciaId;
            c.setId(id);
            contatos.put(id, c);
        } else {
            // Se ID existir, substitui dados do contato existente
            contatos.put(c.getId(), c);
        }
    }
    
   public void deleteById(int id) {
       contatos.remove(id);
   }
    
}
