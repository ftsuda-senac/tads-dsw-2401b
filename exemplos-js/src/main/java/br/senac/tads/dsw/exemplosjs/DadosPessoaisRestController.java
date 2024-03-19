/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.exemplosjs;

import java.time.LocalDate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DadosPessoaisRestController {
    
    @GetMapping("/dados-pessoais")
    public DadosPessoais findDados() {
        DadosPessoais dados = new DadosPessoais();
        dados.setNome("Seu Madruga");
        dados.setEmail("madruga@teste.com.br");
        dados.setTelefone("(11) 99999-1234");
        dados.setDataNascimento(LocalDate.parse("1940-05-20"));
        return dados;
    }
    
}
