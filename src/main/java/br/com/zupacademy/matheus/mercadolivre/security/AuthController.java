package br.com.zupacademy.matheus.mercadolivre.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Profile("prod")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> authenticate(@RequestBody @Valid CredenciaisDTO credenciaisDTO) {
        UsernamePasswordAuthenticationToken usuarioLogado = new UsernamePasswordAuthenticationToken(
                                                credenciaisDTO.getEmail(), credenciaisDTO.getSenha());
        Authentication authenticate = authenticationManager.authenticate(usuarioLogado);
        String token = tokenService.gerarToken(authenticate);
        return ResponseEntity.ok(new TokenDTO(token));
    }
}
