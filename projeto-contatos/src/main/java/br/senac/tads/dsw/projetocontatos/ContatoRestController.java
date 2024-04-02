/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.projetocontatos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contatos")
public class ContatoRestController {
    
    @GetMapping("/lista")
    public List<Contato> listar() {
        List<Contato> lista = new ArrayList<>();
        lista.add(new Contato(1, "Fulano da Silva", "fulano@email.com.br",
        "(11) 98765-1234", LocalDate.parse("2000-10-20")));
        lista.add(new Contato(2, "Ciclano de Souza", "ciclano@email.com.br",
        "(11) 99001-3344", LocalDate.parse("1999-05-16")));
        lista.add(new Contato(3, "Beltrana dos Santos", "beltrana@email.com.br",
        "(11) 91028-5432", LocalDate.parse("2001-07-04")));
        return lista;
    }
    
    @GetMapping("/consulta/{id}")
    public Contato consultar(@PathVariable("id") int id) {
        List<Contato> lista = new ArrayList<>();
        lista.add(new Contato(1, "Fulano da Silva", "fulano@email.com.br",
        "(11) 98765-1234", LocalDate.parse("2000-10-20")));
        lista.add(new Contato(2, "Ciclano de Souza", "ciclano@email.com.br",
        "(11) 99001-3344", LocalDate.parse("1999-05-16")));
        lista.add(new Contato(3, "Beltrana dos Santos", "beltrana@email.com.br",
        "(11) 91028-5432", LocalDate.parse("2001-07-04")));
        for (Contato c : lista) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
    
}
