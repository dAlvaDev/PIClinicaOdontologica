package com.pidenis.appclinica.controller;

import com.pidenis.appclinica.entity.Paciente;
import com.pidenis.appclinica.exception.BadRequestException;
import com.pidenis.appclinica.exception.ResourceNotFoundException;
import com.pidenis.appclinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        Optional<Paciente> buscarPaciente = pacienteService.buscarPorEmail(paciente.getEmail());
        if (buscarPaciente.isPresent()) {
            throw new BadRequestException("El email ya existe, no se puede crear el paciente");
        }
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(paciente.getId());
        if (pacienteBuscado.isPresent()) {
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Paciente actualizado con exito");
        } else {
            throw new ResourceNotFoundException("No existe el paciente");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Paciente> paciente = pacienteService.buscarPaciente(id);
        if (paciente.isPresent()) {
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Paciente eliminado con exito");
        } else {
            throw new ResourceNotFoundException("No existe el id : " + id);
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Paciente> paciente = pacienteService.buscarPaciente(id);
        if (paciente.isPresent()) {
            return ResponseEntity.ok(paciente.get());
        } else {
            throw new ResourceNotFoundException("No existe el paciente con el id: " + id);
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodosPaciente() {
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Paciente> buscarPorEmail(@PathVariable String email) throws ResourceNotFoundException {
        Optional<Paciente> buscarPaciente = pacienteService.buscarPorEmail(email);
        if (buscarPaciente.isPresent()) {
            return ResponseEntity.ok(buscarPaciente.get());
        } else {
            throw new ResourceNotFoundException("No se encontró el paciente");
        }
    }
}
