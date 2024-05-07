/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.senac.tads.dsw.projetocontatos;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ContatoRepository extends 
        JpaRepository<Contato, Integer> {
    
    Optional<Contato> findByNome(String nome);
    
    Optional<Contato> findByEmail(String email);
    
    List<Contato> findByDataNascimentoGreaterThanEqual(LocalDate data);
    
    @Query(nativeQuery = true, value = "SELECT * FROM contato WHERE id > ?1")
    List<Contato> obterIdsMaioresQueX(int x);
}
