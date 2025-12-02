package com.sena.citaspsicologia.citas_psicologia_app.controller;

// Importamos tus modelos (que ahora están en la ruta correcta)
import com.sena.citaspsicologia.citas_psicologia_app.model.Cita;
import com.sena.citaspsicologia.citas_psicologia_app.model.Paciente;
import com.sena.citaspsicologia.citas_psicologia_app.model.Psicologo;
import com.sena.citaspsicologia.citas_psicologia_app.model.Servicio;

// Importamos tus repositorios
import com.sena.citaspsicologia.citas_psicologia_app.repository.CitaRepository;
import com.sena.citaspsicologia.citas_psicologia_app.repository.PacienteRepository;
import com.sena.citaspsicologia.citas_psicologia_app.repository.PsicologoRepository;
import com.sena.citaspsicologia.citas_psicologia_app.repository.ServicioRepository;

// Importamos el DTO
import com.sena.citaspsicologia.citas_psicologia_app.dto.CitaRequestDTO;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "http://localhost:5173")
public class CitaController {

    private final CitaRepository citaRepository;
    private final PacienteRepository pacienteRepository;
    private final PsicologoRepository psicologoRepository;
    private final ServicioRepository servicioRepository;

    public CitaController(CitaRepository citaRepository, PacienteRepository pacienteRepository,
                          PsicologoRepository psicologoRepository, ServicioRepository servicioRepository) {
        this.citaRepository = citaRepository;
        this.pacienteRepository = pacienteRepository;
        this.psicologoRepository = psicologoRepository;
        this.servicioRepository = servicioRepository;
    }

    @GetMapping
    public List<Cita> listar() {
        return citaRepository.findAll();
    }

    @PostMapping
    public Cita crear(@RequestBody CitaRequestDTO dto) {
        Cita cita = new Cita();
        
        // Convertimos los Strings del DTO a Fecha/Hora de Java
        cita.setFecha(LocalDate.parse(dto.getFecha())); 
        cita.setHora(LocalTime.parse(dto.getHora()));
        cita.setEstado(dto.getEstado());

        // Buscamos las entidades relacionadas usando los IDs
        Paciente paciente = pacienteRepository.findById(dto.getIdPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        
        Psicologo psicologo = psicologoRepository.findById(dto.getIdPsicologo())
                .orElseThrow(() -> new RuntimeException("Psicologo no encontrado"));
        
        Servicio servicio = servicioRepository.findById(dto.getIdServicio())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        cita.setPaciente(paciente);
        cita.setPsicologo(psicologo);
        cita.setServicio(servicio);

        return citaRepository.save(cita);
    }
    
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        citaRepository.deleteById(id);
    }
}