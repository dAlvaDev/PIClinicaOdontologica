package com.pidenis.appclinica.controller;

import com.pidenis.appclinica.entity.Odontologo;
import com.pidenis.appclinica.service.OdontologoService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public class OdontologoController {
    private OdontologoService odontologoService;

    public ResponseEntity<Odontologo> registrarUnOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorMatricula(odontologo.getMatricula());
        if (odontologoBuscado.isPresent()) {
            throw new BadRequestException("La matricula ya existe, no se puede crear el odontologo");
        }
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }
}
