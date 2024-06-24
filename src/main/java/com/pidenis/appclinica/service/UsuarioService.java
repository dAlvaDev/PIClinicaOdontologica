package com.pidenis.appclinica.service;

import com.pidenis.appclinica.entity.Usuario;
import com.pidenis.appclinica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioBuscado = usuarioRepository.findByEmail(username);
        if (usuarioBuscado.isPresent()) {
            return usuarioBuscado.get();
        } else {
            throw new UsernameNotFoundException("No existe el usuario: " + username);
        }
    }
}
