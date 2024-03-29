/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.produtos;

import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author fernando.tsuda
 */
public class ImagemProdutoDto {
    
    private Integer id;
    
    private String nomeArquivo;
    
    private MultipartFile arquivo;
    
	private byte[] arquivoBytes;

    private int ordenacao;
    
    private boolean principal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public MultipartFile getArquivo() {
        return arquivo;
    }

    public void setArquivo(MultipartFile arquivo) {
        this.arquivo = arquivo;
    }

	public byte[] getArquivoBytes() {
		return arquivoBytes;
	}

	public String getArquivoBytesBase64() {
		return Base64.getEncoder().encodeToString(this.arquivoBytes);
	}

	public void setArquivoBytes(byte[] arquivoBytes) {
		this.arquivoBytes = arquivoBytes;
	}

	public int getOrdenacao() {
        return ordenacao;
    }

    public void setOrdenacao(int ordenacao) {
        this.ordenacao = ordenacao;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }
}
