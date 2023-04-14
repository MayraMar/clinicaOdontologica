
package com.dh.integrador.martinezMayra.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table (name="turnos")
public class Turno {

    @Id
    @SequenceGenerator(name = "turno_sequence",sequenceName = "turno_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "turno_sequence")
    private Long id;

    @ManyToOne //(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn (name = "paciente_id")
    private Paciente paciente;

    @ManyToOne //(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn (name = "odontologo_id")
    private Odontologo odontologo;

    @Column
    private LocalDate fecha;

    public Long getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }



}

