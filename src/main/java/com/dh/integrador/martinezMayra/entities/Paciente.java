package com.dh.integrador.martinezMayra.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="pacientes")
public class Paciente {
    @Id
    @SequenceGenerator(name = "paciente_sequence",sequenceName = "paciente_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "paciente_sequence")
    private Long id;
    @Column
    private String apellido;
    @Column
    private String nombre;
    @Column
    private String email;
    @Column
    private int dni;
    @Column
    private LocalDate fechaIngreso;
    @OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn (name = "domicilio_id", referencedColumnName = "id")
    private Domicilio domicilio;

    @OneToMany(mappedBy = "paciente")
    @JsonIgnore
    private Set<Turno> turnos=new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Set<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(Set<Turno> turnos) {
        this.turnos = turnos;
    }

    @Override
    public boolean equals(Object objeto) {
        boolean respuesta=false;
        if (objeto!=null&&objeto instanceof Paciente){
            Paciente pacienteAComparar=(Paciente)objeto;
            respuesta=(id==pacienteAComparar.getId() &&
                    apellido.toUpperCase().equals(pacienteAComparar.getApellido().toUpperCase()) &&
                    dni== pacienteAComparar.getDni());
        }
        return respuesta;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", dni=" + dni +
                ", fechaIngreso=" + fechaIngreso +
                ", domicilio=" + domicilio +
                ", turnos=" + turnos +
                '}';
    }
}
