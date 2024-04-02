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
import org.springframework.stereotype.Service;

@Service
public class ContatoService {
    
    private Map<Integer, Contato> contatos;
    
    public ContatoService() {
        contatos = new LinkedHashMap<>();
        contatos.put(1, new Contato(1, "Fulano da Silva", 
                "fulano@email.com.br", "(11) 98765-1234", 
                LocalDate.parse("2000-10-20")));
        contatos.put(2, new Contato(2, "Ciclano de Souza", 
                "ciclano@email.com.br", "(11) 99001-3344", 
                LocalDate.parse("1999-05-16")));
        contatos.put(3, new Contato(3, "Beltrana dos Santos", 
                "beltrana@email.com.br", "(11) 91028-5432", 
                LocalDate.parse("2001-07-04")));
    }
    
    public List<Contato> findAll() {
        return new ArrayList<>(contatos.values());
    }
    
    public Contato findById(int id) {
        return contatos.get(id);
    }
    
}
