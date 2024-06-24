package com.pidenis.appclinica.security;

import com.pidenis.appclinica.entity.Usuario;
import com.pidenis.appclinica.entity.UsuarioRole;
import com.pidenis.appclinica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosIniciales implements ApplicationRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String passSinCifrar = "admin";
        String passCifrado = passwordEncoder.encode(passSinCifrar);
        Usuario usuario = new Usuario("user", UsuarioRole.ROLE_USER, passCifrado, "user", "user");
        Usuario usuario2 = new Usuario("admin", UsuarioRole.ROLE_ADMIN, passCifrado, "admin", "admin");
        System.out.println("pass cifrado: " + passCifrado);
        usuarioRepository.save(usuario);
        usuarioRepository.save(usuario2);
    }
}
