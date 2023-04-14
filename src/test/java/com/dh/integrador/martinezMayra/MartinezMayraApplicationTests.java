package com.dh.integrador.martinezMayra;

import com.dh.integrador.martinezMayra.entities.Domicilio;
import com.dh.integrador.martinezMayra.entities.Odontologo;
import com.dh.integrador.martinezMayra.entities.Paciente;
import com.dh.integrador.martinezMayra.entities.Turno;
import com.dh.integrador.martinezMayra.service.IOdontologoService;
import com.dh.integrador.martinezMayra.service.IPacienteService;
import com.dh.integrador.martinezMayra.service.ITurnoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.sql.rowset.spi.TransactionalWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MartinezMayraApplicationTests {
	@Autowired
	private IOdontologoService odontologoService;
	@Autowired
	private IPacienteService pacienteService;
	@Autowired
	private ITurnoService turnoService;

	// TESTS DE ODONTOLOGO SERVICE

	@Test
	@Order(1)
	void agregarYBuscarOdontologo() {
		Odontologo odonto1= new Odontologo();
		odonto1.setApellido("Dentista");
		odonto1.setNombre("Mauro");
		odonto1.setMatricula(321);
		odontologoService.guardarOdontologo(odonto1);
		Odontologo oBuscado=odontologoService.buscarOdontologo(1L).get();
		assertEquals("Dentista",oBuscado.getApellido());

	}

	@Test
	@Order(2)
	void actualizarOdontologo() {
		Odontologo odonto1= new Odontologo();
		odonto1.setApellido("Muelitas");
		odonto1.setNombre("Mauro");
		odonto1.setMatricula(456);
		odontologoService.guardarOdontologo(odonto1);
		odonto1.setMatricula(333);
		odontologoService.actualizarOdontologo(odonto1);
		Odontologo oBuscado=odontologoService.buscarOdontologo(2L).get();
		assertEquals(333,oBuscado.getMatricula());

	}

	@Test
	@Order(3)
	void listarOdontologos() {
		List<Odontologo> listaOdontologos =odontologoService.listarOdontologos();
		assertTrue(listaOdontologos.size()==2);

	}

	@Test
	@Order(4)
	void eliminarOdontologo() {
		odontologoService.borrarOdontologo(1L);
		assertEquals(Optional.empty(),odontologoService.buscarOdontologo(1L));
		assertTrue(odontologoService.listarOdontologos().size()==1);
	}

	// TESTS DE PACIENTE SERVICE

	@Test
	@Order(5)
	void agregarYBuscarPaciente() {
		Paciente paciente1= new Paciente();
		Domicilio domic=new Domicilio();
		paciente1.setApellido("Martinez");
		paciente1.setNombre("Prudencia");
		paciente1.setDni(3232);
		paciente1.setEmail("prude@correo");
		paciente1.setFechaIngreso(LocalDate.now());
		domic.setCalle("Main Street");
		domic.setNumero(123);
		domic.setLocalidad("CABA");
		domic.setProvincia("Buenos Aires");
		paciente1.setDomicilio(domic);
		pacienteService.guardarPaciente(paciente1);
		Paciente pBuscado=pacienteService.buscarPaciente(1L).get();
		assertEquals("Martinez",pBuscado.getApellido());
		assertTrue(pBuscado.getDomicilio().getNumero()==123);

	}

	@Test
	@Order(6)
	void actualizarPaciente() {

	  	Paciente paciente1=pacienteService.buscarPaciente(1L).get();
		paciente1.setEmail("nuevocorreo@gmail");
		pacienteService.actualizarPaciente(paciente1);
		Paciente pBuscado=pacienteService.buscarPaciente(1L).get();
		assertEquals("nuevocorreo@gmail",pBuscado.getEmail());

	}

	@Test
	@Order(7)
	void listarPacientes() {
		List<Paciente> listapacientes =pacienteService.listarPacientes();
		assertTrue(listapacientes.size()==1);

	}

	@Test
	@Order(8)
	void eliminarPaciente() {
		pacienteService.borrarPaciente(1L);
		assertEquals(Optional.empty(),pacienteService.buscarPaciente(1L));
		assertTrue(pacienteService.listarPacientes().size()==0);
	}

	// TEST DE TURNO SERVICE

	@Test
	@Order(9)
	void agregarYBuscarTurno() {
		Turno turno=new Turno();
		turno.setFecha(LocalDate.of(2022,7,7));

		Paciente paciente2= new Paciente();
		Domicilio domic=new Domicilio();
		paciente2.setApellido("Paciencia");
		paciente2.setNombre("Juancito");
		paciente2.setDni(222);
		paciente2.setEmail("espera@correo");
		paciente2.setFechaIngreso(LocalDate.now());
		domic.setCalle("Calle Lenta");
		domic.setNumero(666);
		domic.setLocalidad("Springdfield");
		domic.setProvincia("Catamarca");
		paciente2.setDomicilio(domic);
		pacienteService.guardarPaciente(paciente2);

		turno.setOdontologo(odontologoService.buscarOdontologo(2L).get());
		turno.setPaciente(paciente2);
		turnoService.guardarTurno(turno);

		Turno turnoBuscado=turnoService.buscarTurno(1L).get();

		assertEquals(2L,turnoBuscado.getOdontologo().getId());
		assertTrue("Paciencia"==turnoBuscado.getPaciente().getApellido());
	}

	@Test
	@Order(10)
	void actualizarTurno() {
		Turno turno1 = turnoService.buscarTurno(1L).get();
		turno1.setFecha(LocalDate.of(2022,07,15));
		turnoService.actualizarTurno(turno1);
		assertEquals(LocalDate.of(2022,07,15),turnoService.buscarTurno(1L).get().getFecha());
	}

	@Test
	@Order(11)
	void eliminarTurno() {

		turnoService.borrarTurno(1L);
		assertEquals(0,turnoService.listarTurnos().size());
	}

}
