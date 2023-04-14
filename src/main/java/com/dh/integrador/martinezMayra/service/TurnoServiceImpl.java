package com.dh.integrador.martinezMayra.service;

import com.dh.integrador.martinezMayra.entities.Turno;
import com.dh.integrador.martinezMayra.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoServiceImpl implements ITurnoService{
    @Autowired
    TurnoRepository turnoRepository;

    @Override
    public Turno guardarTurno(Turno t) {
        return turnoRepository.save(t);
    }

    @Override
    public Optional<Turno> buscarTurno(Long id) {
        return turnoRepository.findById(id);
    }

    @Override
    public List<Turno> listarTurnos() {
        return turnoRepository.findAll();
    }

    @Override
    public void borrarTurno(Long id) {
        turnoRepository.deleteById(id);
    }

    @Override
    public Turno actualizarTurno(Turno t) {
        return turnoRepository.save(t);
    }
}

