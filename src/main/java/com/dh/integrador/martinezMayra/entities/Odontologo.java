package com.dh.integrador.martinezMayra.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="odontologos")
public class Odontologo {
    @Id
    @SequenceGenerator(name = "odontologo_sequence",sequenceName = "odontologo_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "odontologo_sequence")
    private Long id;
    @Column
    private String apellido;
    @Column
    private String nombre;
    @Column
    private int matricula;

    @OneToMany(mappedBy = "odontologo")
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

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Set<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(Set<Turno> turnos) {
        this.turnos = turnos;
    }

    @Override
    public boolean equals(Object objeto){
        boolean respuesta=false;
        if (objeto!=null&&objeto instanceof Odontologo){
            Odontologo odontologoAComparar=(Odontologo)objeto;
            respuesta=(id==odontologoAComparar.getId() &&
                    apellido.toUpperCase().equals(odontologoAComparar.getApellido().toUpperCase()));
        }
        return respuesta;
    }

    @Override
    public String toString() {
        return "Odontologo{" +
                "id=" + id +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", matricula=" + matricula +
                ", turnos=" + turnos +
                '}';
    }
}
