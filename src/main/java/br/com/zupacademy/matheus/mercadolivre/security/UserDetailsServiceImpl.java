package br.com.zupacademy.matheus.mercadolivre.security;

import br.com.zupacademy.matheus.mercadolivre.usuario.Usuario;
import br.com.zupacademy.matheus.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Usuario> possivelUsuario = usuarioRepository.findByLogin(login);
        if (possivelUsuario.isPresent()) {
            return possivelUsuario.get();
        }
        throw new UsernameNotFoundException("Login " + login + " inválido.");
    }
}
