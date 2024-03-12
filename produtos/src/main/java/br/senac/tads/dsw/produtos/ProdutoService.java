/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.produtos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository repository;
    
    public void incluir(ProdutoDto dto) {
        
        Produto entity = new Produto();
        entity.setNome(dto.getNome());
        entity.setPreco(dto.getPreco());
        
        List<ImagemProduto> imagensEntities = new ArrayList<>();
        if (dto.getImagens() != null && !dto.getImagens().isEmpty()) {
            for (ImagemProdutoDto imgDto : dto.getImagens()) {
                ImagemProduto imgEntity = new ImagemProduto();
                imgEntity.setNomeArquivo(imgDto.getArquivo().getOriginalFilename());
                try {
                    imgEntity.setArquivo(imgDto.getArquivo().getBytes());
                } catch (IOException ex) {
                    // TODO: tratar IOExceptiopn
                }
                imgEntity.setOrdenacao(imgDto.getOrdenacao());
                imgEntity.setPrincipal(imgDto.isPrincipal());
                imgEntity.setProduto(entity);
                imagensEntities.add(imgEntity);
            }
        }
        entity.setImagens(imagensEntities);
        repository.save(entity);
               
    }
    
    public ProdutoDto findById(int id) {
        Produto entity = repository.findById(id).orElseThrow();
        ProdutoDto dto = new ProdutoDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setPreco(entity.getPreco());
        
        List<ImagemProdutoDto> imagens  = new ArrayList<>();
        if (entity.getImagens() != null && !entity.getImagens().isEmpty()) {
            for (ImagemProduto imgEntity : entity.getImagens()) {
                ImagemProdutoDto imgDto = new ImagemProdutoDto();
                imgDto.setId(id);
				imgDto.setArquivoBytes(imgEntity.getArquivo());
				imgDto.setNomeArquivo(imgEntity.getNomeArquivo());
                imgDto.setOrdenacao(imgEntity.getOrdenacao());
                imgDto.setPrincipal(imgEntity.isPrincipal());
                imagens.add(imgDto);
            }
        }
        dto.setImagens(imagens);
        return dto;
    }
    
}
