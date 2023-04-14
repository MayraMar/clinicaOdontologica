

package com.dh.integrador.martinezMayra.repository;

import com.dh.integrador.martinezMayra.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

//@Repository
//@Transactional
public interface UserRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByUsername(String username);
}


