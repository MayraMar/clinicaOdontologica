

package com.dh.integrador.martinezMayra.service;

import com.dh.integrador.martinezMayra.entities.Usuario;
import com.dh.integrador.martinezMayra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioBuscado = userRepository.findByUsername(username);
        if (usuarioBuscado.isPresent()) {
            return usuarioBuscado.get();
        } else {
            throw new UsernameNotFoundException("El username ingresado no se ha encontrado");
        }

    }
}
/*
        // FORMA DE PLAYGROUND
        return usuarioBuscado.orElseThrow(()
                -> new UsernameNotFoundException("No se encontró al usuario con username= "+username));*//*

    }
}

*/
