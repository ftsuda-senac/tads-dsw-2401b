/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.senac.tads.dsw.produtos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagemProdutoRepository extends
        JpaRepository<ImagemProduto, Integer> {

	Optional<ImagemProduto> findByProduto_IdAndNomeArquivo(int produtoId, String nomeArquivo);
    
}
