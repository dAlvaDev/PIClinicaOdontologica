package com.pidenis.appclinica.controller;

import com.pidenis.appclinica.entity.Odontologo;
import com.pidenis.appclinica.exception.BadRequestException;
import com.pidenis.appclinica.exception.ResourceNotFoundException;
import com.pidenis.appclinica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {
        Optional<Odontologo> buscarOdontologo = odontologoService.buscarPorMatricula(odontologo.getMatricula());
        if (buscarOdontologo.isPresent()) {
            throw new BadRequestException("La matricula ya existe, no se puede crear el odontologo");
        }
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(odontologo.getId());
        if (odontologoBuscado.isPresent()) {
            odontologoService.actualizar(odontologo);
            return ResponseEntity.ok("Odontólogo actualizado con exito");
        } else {
            throw new ResourceNotFoundException("No existe el odontologo");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologo = odontologoService.buscarOdontologo(id);
        if (odontologo.isPresent()) {
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("Odontologo eliminado con exito");
        } else {
            throw new ResourceNotFoundException("No existe el id : " + id);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologo = odontologoService.buscarOdontologo(id);
        if (odontologo.isPresent()) {
            return ResponseEntity.ok(odontologo.get());
        } else {
            throw new ResourceNotFoundException("No existe el odontologo con el id: " + id);
        }
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodosOdontologos() {
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<Odontologo> buscarPorMatricula(@PathVariable String matricula) throws ResourceNotFoundException {
        Optional<Odontologo> buscarOdontologo = odontologoService.buscarPorMatricula(matricula);
        if (buscarOdontologo.isPresent()) {
            return ResponseEntity.ok(buscarOdontologo.get());
        } else {
            throw new ResourceNotFoundException("No se encontró el odontologo");
        }
    }
}
