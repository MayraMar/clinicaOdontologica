package com.dh.integrador.martinezMayra.service;

import com.dh.integrador.martinezMayra.entities.Odontologo;
import com.dh.integrador.martinezMayra.entities.Paciente;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    public Paciente guardarPaciente(Paciente p);
    public Optional<Paciente> buscarPaciente(Long id);
    public List<Paciente> listarPacientes();
    public void borrarPaciente (Long id);
    public Paciente actualizarPaciente(Paciente p);
}
