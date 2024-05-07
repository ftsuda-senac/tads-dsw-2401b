/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.projetocontatos;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

/**
 * Representa a Entity Contato que é responsável pela
 * integração com as tabelas do banco de dados via JPA
 * @author fernando.tsuda
 */
@Entity
public class Contato {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Uso do sequence abaixo
    //@SequenceGenerator(name = "seq_contato")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_contato")
    private Integer id;
    
    @NotBlank
    @Size(max = 100)
    private String nome;
    
    @NotBlank
    @Size(max = 200)
    @Email
    private String email;
    
    @NotBlank
    @Size(max = 15)
    private String telefone;
    
    @PastOrPresent
    private LocalDate dataNascimento;
    
    @OneToMany(mappedBy = "contato", cascade = CascadeType.ALL)
    private Set<Foto> fotos;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "contato_interesse",
            joinColumns = @JoinColumn(name = "contato_id"),
            inverseJoinColumns = @JoinColumn(name = "interesse_id"))
    private Set<Interesse> interesses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Set<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(Set<Foto> fotos) {
        this.fotos = fotos;
    }

    public Set<Interesse> getInteresses() {
        return interesses;
    }

    public void setInteresses(Set<Interesse> interesses) {
        this.interesses = interesses;
    }

    
}
