/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.projetocontatos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    private AuthenticationManager authManager;
    
    public record Credencial(String username, String password) {
        
    }
    
    @PostMapping
    public ResponseEntity<?> fazerLogin(@RequestBody Credencial cred) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        cred.username,
                        cred.password
                ));
        if (auth != null) {
            return ResponseEntity.ok(auth.getPrincipal());
        }
        else  {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
}
