package com.sena.citaspsicologia.citas_psicologia_app.controller;

import com.sena.citaspsicologia.citas_psicologia_app.model.Cita;
import com.sena.citaspsicologia.citas_psicologia_app.repository.CitaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que expone los endpoints para la gestión de citas.
 * Maneja las peticiones HTTP y conecta el Frontend con la Base de Datos.
 */
@RestController
@RequestMapping("/api/citas") // Define la URL base: http://localhost:8090/api/citas
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier origen (útil para desarrollo Frontend)
@Tag(name = "Gestión de Citas", description = "Operaciones CRUD para el agendamiento y administración de citas")
public class CitaController {

    // Inyección de dependencias del repositorio para acceder a la base de datos
    @Autowired
    private CitaRepository citaRepository;

    /**
     * Endpoint para LISTAR todas las citas registradas.
     * Método HTTP: GET
     */
    @GetMapping
    @Operation(summary = "Obtener todas las citas", description = "Retorna un listado completo de las citas almacenadas en el sistema.")
    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }

    /**
     * Endpoint para AGENDAR (CREAR) una nueva cita.
     * Método HTTP: POST
     * @param cita Objeto JSON recibido en el cuerpo de la petición.
     */
    @PostMapping
    @Operation(summary = "Agendar nueva cita", description = "Registra una cita con estado inicial 'Pendiente'.")
    public Cita agendarCita(@RequestBody Cita cita) {
        // Se asigna el estado por defecto antes de guardar
        cita.setEstado("Pendiente");
        return citaRepository.save(cita);
    }

    /**
     * Endpoint para CONSULTAR citas de un paciente específico.
     * Método HTTP: GET
     * URL ejemplo: /api/citas/paciente/1
     */
    @GetMapping("/paciente/{id}")
    @Operation(summary = "Consultar citas por Paciente", description = "Filtra las citas asociadas al ID de un usuario específico.")
    public List<Cita> listarPorPaciente(@PathVariable Long id) {
        return citaRepository.findByPacienteId(id);
    }

    /**
     * Endpoint para CANCELAR (ELIMINAR) una cita.
     * Método HTTP: DELETE
     * URL ejemplo: /api/citas/5
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Cancelar Cita", description = "Elimina el registro de una cita mediante su ID.")
    public ResponseEntity<Void> cancelarCita(@PathVariable Long id) {
        // Verificamos si la cita existe antes de intentar borrarla
        if (citaRepository.existsById(id)) {
            citaRepository.deleteById(id);
            return ResponseEntity.ok().build(); // Retorna HTTP 200 OK
        }
        return ResponseEntity.notFound().build(); // Retorna HTTP 404 Not Found si no existe
    }
}