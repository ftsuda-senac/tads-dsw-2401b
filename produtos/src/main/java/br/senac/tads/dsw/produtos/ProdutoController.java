/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.produtos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoService service;
    
    @GetMapping
    public String listar(Model model,
            @RequestParam(name="qtde", defaultValue = "10") int qtdeItens,
            @RequestParam(name="pagina", defaultValue = "0")int numPag) {
        return "produtos/lista";
    }
    
    @GetMapping("/incluir")
    public String abrirFormInclusao(Model model) {
        model.addAttribute("produto", new ProdutoDto());
        return "produtos/form";
    }

    @PostMapping("/incluir")
    public String salvarInclusao(@ModelAttribute("produto") ProdutoDto dto, RedirectAttributes redirectAttributes) {
        service.incluir(dto);
        return "redirect:/produtos/incluir";
    }
    
    
    @GetMapping("/visualizar/{id}")
    public String visualizar(Model model, @PathVariable int id) {
        ProdutoDto dto  = service.findById(id);
        model.addAttribute("produto", dto);
        return "produtos/visualizar";
    }
    
}
