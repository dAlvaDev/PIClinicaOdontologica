package com.pidenis.appclinica.service;

import com.pidenis.appclinica.dto.TurnoDTO;
import com.pidenis.appclinica.entity.Odontologo;
import com.pidenis.appclinica.entity.Paciente;
import com.pidenis.appclinica.entity.Turno;
import com.pidenis.appclinica.repository.OdontologoRepository;
import com.pidenis.appclinica.repository.PacienteRepository;
import com.pidenis.appclinica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private OdontologoRepository odontologoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    public TurnoDTO guardarTurno(Turno turno) {
        return convertirATurnoDTO(turnoRepository.save(turno));
    }

    public void actualizarTurno(TurnoDTO turnoDTO) {
        turnoRepository.save(convertirATurno(turnoDTO));
    }

    public void eliminarTurno(Long id) {
        turnoRepository.deleteById(id);
    }

    public Optional<TurnoDTO> buscarTurno(Long id) {
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        if (turnoBuscado.isPresent()) {
            return Optional.of(convertirATurnoDTO(turnoBuscado.get()));
        }
        return Optional.empty();
    }

    public List<TurnoDTO> buscarTodos() {
        List<Turno> listaTurnos = turnoRepository.findAll();
        List<TurnoDTO> listaDTO = new ArrayList<>();
        for (Turno turno : listaTurnos) {
            listaDTO.add(convertirATurnoDTO(turno));
        }
        return listaDTO;
    }

    public TurnoDTO convertirATurnoDTO(Turno turno) {
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setId(turno.getId());
        turnoDTO.setFecha(turno.getFechaHora());
        turnoDTO.setPacienteId(turno.getPaciente().getId());
        turnoDTO.setOdontologoId(turno.getOdontologo().getId());
        return turnoDTO;
    }

    public Turno convertirATurno(TurnoDTO turnoDTO) {
        Turno turno = new Turno();
        Odontologo odontologo = odontologoRepository.findById(turnoDTO.getOdontologoId()).get();
        Paciente paciente = pacienteRepository.findById(turnoDTO.getPacienteId()).get();
        turno.setId(turnoDTO.getId());
        turno.setFechaHora(turnoDTO.getFecha());
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        return turno;
    }

}
