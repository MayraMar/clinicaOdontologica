package com.dh.integrador.martinezMayra.controller;

import com.dh.integrador.martinezMayra.entities.Odontologo;
import com.dh.integrador.martinezMayra.entities.Paciente;
import com.dh.integrador.martinezMayra.entities.Turno;
import com.dh.integrador.martinezMayra.exceptions.BadRequestException;
import com.dh.integrador.martinezMayra.exceptions.ResourceNotFoundException;
import com.dh.integrador.martinezMayra.service.IOdontologoService;
import com.dh.integrador.martinezMayra.service.IPacienteService;
import com.dh.integrador.martinezMayra.service.ITurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    ITurnoService turnoService;
    @Autowired
    IPacienteService pacienteService;
    @Autowired
    IOdontologoService odontologoService;

    private static final Logger logger= Logger.getLogger(TurnoController.class);

    @GetMapping
    public List<Turno> listarTurnos (){
        return turnoService.listarTurnos();
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Optional<Turno>> buscarTurno (@PathVariable Long id){
        return ResponseEntity.ok(turnoService.buscarTurno(id));
    }

    @PostMapping
    public ResponseEntity<Turno> agregarTurno (@RequestBody Turno turno) throws BadRequestException, ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(turno.getPaciente().getId());

        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(turno.getOdontologo().getId());

        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
            if (pacienteBuscado.get().equals(turno.getPaciente()) && odontologoBuscado.get().equals(turno.getOdontologo())) {
                turnoService.guardarTurno(turno);
                logger.info("Se ha tomado un nuevo turno");
                return ResponseEntity.ok(turno);
            } else {
                logger.error("Los datos del paciente u odontologo ingresados no son correctos. Intente reservar el turno nuevamente.");
                throw new BadRequestException("Los datos del paciente u odontologo ingresados no son correctos. Intente reservar el turno nuevamente.");

            }
        } else {
            logger.error("El paciente (ID="+turno.getPaciente().getId()+") u odontólogo (ID="+turno.getOdontologo().getId()
                    +") ingresados no existen. Verifique los ID e intente nuevamente.");
            throw new ResourceNotFoundException("El paciente u odontólogo ingresados no existen. Verifique los ID e intente nuevamente.");
        }

    }

    @PutMapping
    public ResponseEntity <Turno> actualizarTurno (@RequestBody Turno turno) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado=turnoService.buscarTurno(turno.getId());
        if(turnoBuscado.isPresent()){
            logger.info("Se actualizaron los datos del turno con ID: "+turno.getId()+", del paciente "+turno.getPaciente().getApellido()+".");
            return ResponseEntity.ok(turnoService.actualizarTurno(turno));
        }
        else
            logger.error("No se encontro al turno con id: "+turno.getId()+". No es posible actualizarlo.");
        throw new ResourceNotFoundException("No se encontró al paciente con id: "+turno.getId()+". No es posible actualizarlo.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarTurno (@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado=turnoService.buscarTurno(id);
        if(turnoBuscado.isPresent()){

            turnoService.borrarTurno(id);
            logger.warn("Se elimino el turno con id: "+id);
            return ResponseEntity.ok("Se eliminó al turno con id: "+id);
        } else {
            logger.error("No se encontro al turno con id: "+id+". Error al ingresar el id.");
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el paciente con id: " +id);
            throw new ResourceNotFoundException("No se encontró al turno con id: "+id+". Error al ingresar el id.");
        }
    }

}
