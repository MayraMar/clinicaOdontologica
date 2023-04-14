package com.dh.integrador.martinezMayra.controller;

import com.dh.integrador.martinezMayra.entities.Odontologo;
import com.dh.integrador.martinezMayra.entities.Paciente;
import com.dh.integrador.martinezMayra.exceptions.ResourceNotFoundException;
import com.dh.integrador.martinezMayra.service.IOdontologoService;
import com.dh.integrador.martinezMayra.service.IPacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    IPacienteService pacienteService;

    private static final Logger logger= Logger.getLogger(PacienteController.class);

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes(){
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paciente>> buscarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado=pacienteService.buscarPaciente(id);
        if(pacienteBuscado.isPresent()) {
            return ResponseEntity.ok(pacienteService.buscarPaciente(id));
        } else {
           logger.error("No se encontro al paciente con id: "+id+".");
           throw new ResourceNotFoundException("No se encontro al paciente con id: "+id+".");
        }
    }

    @PostMapping
    public ResponseEntity <Paciente> agregarPaciente (@RequestBody Paciente paciente){
        logger.info("Se agrego al siguiente paciente: "+paciente.getApellido()+", "+paciente.getNombre()+" - DNI: "+paciente.getDni()+".");
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @PutMapping
    public ResponseEntity <Paciente> actualizarPaciente (@RequestBody Paciente paciente) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado=pacienteService.buscarPaciente(paciente.getId());
        if(pacienteBuscado.isPresent()){
            logger.info("Se actualizaron los datos del siguiente paciente: "+paciente.getApellido()+", "+paciente.getNombre()+" (ID "+paciente.getId()+")");
            return ResponseEntity.ok(pacienteService.actualizarPaciente(paciente));
        }
        else
            logger.error("No se encontro al paciente con id: "+paciente.getId()+". No es posible actualizar los datos.");
            throw new ResourceNotFoundException("No se encontró al paciente con id: "+paciente.getId()+". No es posible actualizar los datos.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarPaciente (@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado=pacienteService.buscarPaciente(id);
        if(pacienteBuscado.isPresent()){
            String apellido=pacienteBuscado.get().getApellido();
            pacienteService.borrarPaciente(id);
            logger.warn("Se elimino el paciente "+apellido+ " con id: "+id);
            return ResponseEntity.ok("Se eliminó al paciente "+apellido+ " con id: "+id);
        } else {
            logger.error("No se encontro al paciente con id: "+id+". Error al ingresar el id.");
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el paciente con id: " +id);
            throw new ResourceNotFoundException("No se encontró al paciente con id: "+id+". Error al ingresar el id.");
        }
    }
}
