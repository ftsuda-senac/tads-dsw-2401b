/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.projetocontatos.security;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authManager;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtEncoder encoder;

    public String generateToken(Usuario usuario, Duration duration) {
        Instant now = Instant.now();

        // Scope é string separada com espaços
        // https://stackoverflow.com/a/62477585
        String scope = usuario.getPermissoes().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        // @formatter:off
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                //.audience(Arrays.asList(clientKey))
                .issuedAt(now)
                .expiresAt(now.plus(duration))
                .subject(usuario.getUsername())
                .claim("nome", usuario.getNome())
                .claim("scope", scope).build();
        // @formatter:on
        JwtEncoderParameters encoderParameters
                = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return encoder.encode(encoderParameters).getTokenValue();
    }

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
            Usuario usuario = (Usuario) auth.getPrincipal();
            // Força uso do bcrypt para gerar hash da senha {noop}
            if (usuario.getHashPassword().startsWith("{noop}")) {
                String hashBcrypt = passwordEncoder.encode(cred.password());
                usuario.setHashPassword(hashBcrypt);
                usuarioRepository.save(usuario);
            }
            String jwt = generateToken(usuario, Duration.ofMinutes(3));
            return ResponseEntity.ok(jwt);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
