package br.senac.tads.dsw.projetocontatos;

import br.senac.tads.dsw.projetocontatos.security.Permissao;
import br.senac.tads.dsw.projetocontatos.security.PermissaoRepository;
import br.senac.tads.dsw.projetocontatos.security.Usuario;
import br.senac.tads.dsw.projetocontatos.security.UsuarioRepository;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ProjetoContatosApplication {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PermissaoRepository permissaoRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProjetoContatosApplication.class, args);
    }
    
    @EventListener
    public void handleContextRefreshdEvent(ContextRefreshedEvent context) {
        // Se não houver permissao cadastrada, inclui permissões
        if (permissaoRepository.count() == 0) {
            permissaoRepository.save(new Permissao("OPERADOR"));
            permissaoRepository.save(new Permissao("ADMIN"));
            permissaoRepository.save(new Permissao("DEUS"));   
        }
        
        if (usuarioRepository.count() == 0) {
            Usuario u = new Usuario();
            u.setUsername("bryan");
            u.setHashPassword("{noop}Abcd1234");
            u.setPermissoes(Set.of(new Permissao("OPERADOR")));
            usuarioRepository.save(u);
            
            u = new Usuario();
            u.setUsername("mika");
            u.setHashPassword("{noop}Abcd1234");
            u.setPermissoes(Set.of(new Permissao("ADMIN")));
            usuarioRepository.save(u);
            
            u = new Usuario();
            u.setUsername("ryu");
            u.setHashPassword("{noop}Abcd1234");
            u.setPermissoes(Set.of(new Permissao("DEUS")));
            usuarioRepository.save(u);
        }
    }

}
