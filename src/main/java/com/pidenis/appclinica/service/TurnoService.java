package com.pidenis.appclinica.service;

import com.pidenis.appclinica.entity.Turno;
import com.pidenis.appclinica.repository.OdontologoRepository;
import com.pidenis.appclinica.repository.PacienteRepository;
import com.pidenis.appclinica.repository.TurnoRepository;

import java.util.List;
import java.util.Optional;

public class TurnoService {
    private TurnoRepository turnoRepository;
    private OdontologoRepository odontologoRepository;
    private PacienteRepository pacienteRepository;

    public Turno guardarTurno(Turno turno) {
        return turnoRepository.save(turno);
    }

    public void actualizarTurno(Turno turno) {
        turnoRepository.save(turno);
    }

    public void eliminarTurno(Long id) {
        turnoRepository.deleteById(id);
    }

    public Optional<Turno> buscarTurno(Long id) {
        return turnoRepository.findById(id);
    }

    public List<Turno> buscarTodos() {
        return turnoRepository.findAll();
    }



}
