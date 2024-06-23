package com.pidenis.appclinica.service;

import com.pidenis.appclinica.entity.Odontologo;
import com.pidenis.appclinica.repository.OdontologoRepository;

import java.util.List;
import java.util.Optional;

public class OdontologoService {
    private OdontologoRepository odontologoRepository;

    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    public void actualizar(Odontologo odontologo) {
        odontologoRepository.save(odontologo);
    }

    public void eliminarOdontologo(Long id) {
        odontologoRepository.deleteById(id);
    }

    public Optional<Odontologo> buscarOdontologo(Long id) {
        return odontologoRepository.findById(id);
    }

    public List<Odontologo> buscarTodos() {
        return odontologoRepository.findAll();
    }

    public Optional<Odontologo> buscarPorMatricula(String matricula) {
        return odontologoRepository.findByMatricula(matricula);
    }
}
