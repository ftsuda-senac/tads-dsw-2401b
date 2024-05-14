/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.projetocontatos.security;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class Permissao implements GrantedAuthority {
    
    // Exemplos: ADMIN, OPERADOR, CHEFE, etc
    @Id
    private String nome;
    
    private Permissao() {
    }

    public Permissao(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getAuthority() {
        return nome;
    }
    
}
