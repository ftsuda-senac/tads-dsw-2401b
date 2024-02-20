/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.exemploaula01;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Exemplo dinâmico com Spring MVC + Thymeleaf
 * 
 * @author fernando.tsuda
 */
@Controller
public class DadosPessoaisController {
    
    @GetMapping("/exemplo-dinamico")
    public String mostrarTela(Model model) {
        DadosPessoais dados = new DadosPessoais();
        dados.setNome("Kanye West");
        dados.setDataNascimento(LocalDate.parse("1977-06-08"));
        dados.setEmail("kanye.west@email.com");
        dados.setTelefone("(11) 98765-1234");
        
        model.addAttribute("dados", dados);
        model.addAttribute("dataHora", LocalDateTime.now());
        return "dados-template";
    }

    @GetMapping("/exemplo-dinamico-parametros")
    public String mostrarTelaParametros(
            @RequestParam("nome") String nome, 
            @RequestParam("email") String email,
            @RequestParam("dataNascimento") String dataNascimento,
            @RequestParam("telefone") String telefone,
            HttpServletRequest req,
            Model model) {
        DadosPessoais dados = new DadosPessoais();
        dados.setNome(nome);
        dados.setDataNascimento(LocalDate.parse(dataNascimento));
        dados.setEmail(email);
        dados.setTelefone(telefone);
        
        LocalDateTime dataHoraAcesso = LocalDateTime.now();
        System.out.println(nome + "," + req.getRemoteAddr() + " - " + dataHoraAcesso);
        
        model.addAttribute("dados", dados);
        model.addAttribute("dataHora", dataHoraAcesso);
        return "dados-template";
    }
    
}
