package com.dh.integrador.martinezMayra.controller;

import com.dh.integrador.martinezMayra.entities.Odontologo;
import com.dh.integrador.martinezMayra.exceptions.ResourceNotFoundException;
import com.dh.integrador.martinezMayra.service.IOdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    IOdontologoService odontologoService;

    private static final Logger logger= Logger.getLogger(OdontologoController.class);

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologos(){
        return ResponseEntity.ok(odontologoService.listarOdontologos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(id);
        if (odontologoBuscado.isPresent()) {
            return ResponseEntity.ok(odontologoService.buscarOdontologo(id));
        }else {
            logger.error("No se encontro al odontologo con id: " + id + ".");
            throw new ResourceNotFoundException("No se encontró al odontólogo con id: " + id + ".");

        }
    }
    @PostMapping
    public ResponseEntity <Odontologo> agregarOdontologo (@RequestBody Odontologo odontologo){
        logger.info("Se agrego al siguiente odontologo: "+odontologo.getApellido()+", "+odontologo.getNombre()+" - Matricula: " +odontologo.getMatricula());
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @PutMapping
    public ResponseEntity <Odontologo> actualizarOdontologo (@RequestBody Odontologo odontologo) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(odontologo.getId());
        if (odontologoBuscado.isPresent()) {
            logger.info("Se actualizaron los datos del siguiente odontologo: "+odontologo.getApellido()+", "+odontologo.getNombre()+" - Matricula: " +odontologo.getMatricula()+" (ID "+odontologo.getId()+")");
            return ResponseEntity.ok(odontologoService.actualizarOdontologo(odontologo));
        } else {
            logger.error("No se encontro al odontologo con id: " + odontologo.getId() + ". No es posible actualizar los datos.");
            throw new ResourceNotFoundException("No se encontró al odontólogo con id: " + odontologo.getId() + ". No es posible actualizar los datos.");

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarOdontologo (@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado=odontologoService.buscarOdontologo(id);
        if(odontologoBuscado.isPresent()){
            String apellido=odontologoBuscado.get().getApellido();
            odontologoService.borrarOdontologo(id);
            logger.warn("Se elimino al odontologo "+apellido+ " con id: "+id);
            return ResponseEntity.ok("Se eliminó al odontólogo "+apellido+ " con id: "+id);
        }
        else
            logger.error("No se encontro al odontologo con id: "+id+". Error al ingresar el id.");
            throw new ResourceNotFoundException("No se encontro al odontólogo con id: "+id+". Error al ingresar el id.");
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el odontólogo con id: " +id);
    }
}
