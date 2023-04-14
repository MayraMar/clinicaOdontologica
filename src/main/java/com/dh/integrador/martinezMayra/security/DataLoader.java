
package com.dh.integrador.martinezMayra.security;

import com.dh.integrador.martinezMayra.entities.UserRoles;
import com.dh.integrador.martinezMayra.entities.Usuario;
import com.dh.integrador.martinezMayra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passEncoder=new BCryptPasswordEncoder();
        String hash=passEncoder.encode("MAYRA");
        Usuario user= new Usuario("mayra","mayra","mayra@correo",hash, UserRoles.ROLE_USER);
        userRepository.save(user);

       String hashAdmin=passEncoder.encode("ADMIN");
        Usuario admin= new Usuario("admin","admin","admin@correo",hashAdmin, UserRoles.ROLE_ADMIN);
        userRepository.save(admin);
    }
}

