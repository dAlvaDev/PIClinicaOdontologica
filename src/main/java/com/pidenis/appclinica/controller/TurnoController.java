package com.pidenis.appclinica.controller;

import com.pidenis.appclinica.dto.TurnoDTO;
import com.pidenis.appclinica.entity.Odontologo;
import com.pidenis.appclinica.entity.Paciente;
import com.pidenis.appclinica.entity.Turno;
import com.pidenis.appclinica.exception.BadRequestException;
import com.pidenis.appclinica.exception.ResourceNotFoundException;
import com.pidenis.appclinica.service.OdontologoService;
import com.pidenis.appclinica.service.PacienteService;
import com.pidenis.appclinica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<TurnoDTO> guardarPaciente(@RequestBody Turno turno) throws BadRequestException {
        Optional<Paciente> paciente = pacienteService.buscarPaciente(turno.getPaciente().getId());
        Optional<Odontologo> odontologo = odontologoService.buscarOdontologo(turno.getOdontologo().getId());
        if (odontologo.isPresent() && paciente.isPresent()) {
            turno.setPaciente(paciente.get());
            turno.setOdontologo(odontologo.get());
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        }
        throw new BadRequestException("No existe Paciente / odontologo");
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno) throws ResourceNotFoundException {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(turno.getId());
        Optional<Paciente> paciente = pacienteService.buscarPaciente(turno.getPaciente().getId());
        Optional<Odontologo> odontologo = odontologoService.buscarOdontologo(turno.getOdontologo().getId());
        if (turnoBuscado.isPresent() && odontologo.isPresent() && paciente.isPresent()) {
            turno.setPaciente(paciente.get());
            turno.setOdontologo(odontologo.get());
            turnoService.actualizarTurno(turnoService.convertirATurnoDTO(turno));
            return ResponseEntity.ok("Turno actualizado con exito");
        } else {
            throw new ResourceNotFoundException("No existe el turno, paciente / odontologo");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurnoPorId(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<TurnoDTO> turno = turnoService.buscarTurno(id);
        if (turno.isPresent()) {
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Eliminado con exito");
        } else {
            throw new ResourceNotFoundException("No existe el id : " + id);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarTurnoPorId(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<TurnoDTO> turno = turnoService.buscarTurno(id);
        if (turno.isPresent()) {
            return ResponseEntity.ok(turno.get());
        } else {
            throw new ResourceNotFoundException("No existe el turno con el id: " + id);
        }
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> buscarTodosTurnos() {
        return ResponseEntity.ok(turnoService.buscarTodos());
    }
}
