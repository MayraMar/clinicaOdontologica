package com.dh.integrador.martinezMayra.service;

import com.dh.integrador.martinezMayra.entities.Odontologo;
import com.dh.integrador.martinezMayra.entities.Turno;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    public Turno guardarTurno(Turno t);
    public Optional<Turno> buscarTurno(Long id);
    public List<Turno> listarTurnos();
    public void borrarTurno(Long id);
    public Turno actualizarTurno(Turno t);
}
