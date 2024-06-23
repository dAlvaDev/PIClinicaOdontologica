package com.pidenis.appclinica.entity;
import java.time.LocalDate;

public class Turno {
    private Long id;
    private Paciente paciente;
    private Odontologo odontologo;
    private LocalDate fechaHora;

    public Turno(Paciente paciente, Odontologo odontologo, LocalDate fechaHora) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaHora = fechaHora;
    }
}
