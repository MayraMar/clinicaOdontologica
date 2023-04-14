package com.dh.integrador.martinezMayra.service;

import com.dh.integrador.martinezMayra.entities.Paciente;
import com.dh.integrador.martinezMayra.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements IPacienteService{
    @Autowired
    PacienteRepository pacienteRepository;

    @Override
    public Paciente guardarPaciente(Paciente p) {
        return pacienteRepository.save(p);
    }

    @Override
    public Optional<Paciente> buscarPaciente(Long id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    @Override
    public void borrarPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public Paciente actualizarPaciente(Paciente p) {
        return pacienteRepository.save(p);
    }
}
