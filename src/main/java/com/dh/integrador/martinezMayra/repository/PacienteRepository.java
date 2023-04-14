package com.dh.integrador.martinezMayra.repository;

import com.dh.integrador.martinezMayra.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {
}
