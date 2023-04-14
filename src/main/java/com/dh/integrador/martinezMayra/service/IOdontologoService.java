package com.dh.integrador.martinezMayra.service;

import com.dh.integrador.martinezMayra.entities.Odontologo;
import com.dh.integrador.martinezMayra.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    public Odontologo guardarOdontologo(Odontologo o);
    public Optional<Odontologo> buscarOdontologo(Long id);
    public List<Odontologo> listarOdontologos();
    public void borrarOdontologo (Long id);
    public Odontologo actualizarOdontologo(Odontologo o);

}
