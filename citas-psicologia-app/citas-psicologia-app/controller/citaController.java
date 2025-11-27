package com.sena.citaspsicologia.citas_psicologia_app.controller;

import com.sena.citaspsicologia.citas_psicologia_app.dto.CitaRequestDTO;
import com.sena.citaspsicologia.citas_psicologia_app.model.Cita;
import com.sena.citaspsicologia.citas_psicologia_app.model.Paciente;
import com.sena.citaspsicologia.citas_psicologia_app.model.Psicologo;
import com.sena.citaspsicologia.citas_psicologia_app.model.Servicio;
import com.sena.citaspsicologia.citas_psicologia_app.repository.CitaRepository;
import com.sena.citaspsicologia.citas_psicologia_app.repository.PacienteRepository;
import com.sena.citaspsicologia.citas_psicologia_app.repository.PsicologoRepository;
import com.sena.citaspsicologia.citas_psicologia_app.repository.ServicioRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "http://localhost:5173") // Vite
public class CitaController {

    private final CitaRepository citaRepository;
    private final PacienteRepository pacienteRepository;
    private final PsicologoRepository psicologoRepository;
    private final ServicioRepository servicioRepository;

    public CitaController(
            CitaRepository citaRepository,
            PacienteRepository pacienteRepository,
            PsicologoRepository psicologoRepository,
            ServicioRepository servicioRepository
    ) {
        this.citaRepository = citaRepository;
        this.pacienteRepository = pacienteRepository;
        this.psicologoRepository = psicologoRepository;
        this.servicioRepository = servicioRepository;
    }

    // GET: listar todas las citas
    @GetMapping
    public List<Cita> listar() {
        return citaRepository.findAll();
    }

    // POST: crear una cita desde el DTO
    @PostMapping
    public Cita crear(@RequestBody CitaRequestDTO dto) {
        Cita cita = new Cita();

        // Asumiendo que en tu entidad Cita tienes LocalDate y LocalTime
        cita.setFecha(LocalDate.parse(dto.getFecha()));   // "2025-11-26"
        cita.setHora(LocalTime.parse(dto.getHora()));     // "10:30:00"
        cita.setEstado(dto.getEstado());

        Paciente paciente = pacienteRepository.findById(dto.getIdPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        Psicologo psicologo = psicologoRepository.findById(dto.getIdPsicologo())
                .orElseThrow(() -> new RuntimeException("Psicólogo no encontrado"));

        Servicio servicio = servicioRepository.findById(dto.getIdServicio())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        cita.setPaciente(paciente);
        cita.setPsicologo(psicologo);
        cita.setServicio(servicio);

        return citaRepository.save(cita);
    }
}