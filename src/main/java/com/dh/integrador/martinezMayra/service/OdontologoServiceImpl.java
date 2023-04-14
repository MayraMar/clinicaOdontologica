package com.dh.integrador.martinezMayra.service;

import com.dh.integrador.martinezMayra.entities.Odontologo;
import com.dh.integrador.martinezMayra.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoServiceImpl implements IOdontologoService{
    @Autowired
    OdontologoRepository odontologoRepository;

    @Override
    public Odontologo guardarOdontologo(Odontologo o) {
        return odontologoRepository.save(o);

    }

    @Override
    public Optional<Odontologo> buscarOdontologo(Long id) {
        return odontologoRepository.findById(id);
    }

    @Override
    public List<Odontologo> listarOdontologos() {

        return odontologoRepository.findAll();
    }

    @Override
    public void borrarOdontologo(Long id) {
        odontologoRepository.deleteById(id);
    }

    @Override
    public Odontologo actualizarOdontologo(Odontologo o) {

        return odontologoRepository.save(o);
    }
}
