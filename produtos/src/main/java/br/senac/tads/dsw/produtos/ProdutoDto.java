/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.produtos;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoDto {
    
    private Integer id;
    
    private String nome;
    
    private BigDecimal preco;
    
    private List<ImagemProdutoDto> imagens;

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

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public List<ImagemProdutoDto> getImagens() {
        return imagens;
    }

    public void setImagens(List<ImagemProdutoDto> imagens) {
        this.imagens = imagens;
    }
    
}
