package citas_psicologia_app.controller;

import citas_psicologia_app.model.Cita;
import citas_psicologia_app.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar las peticiones HTTP relacionadas con el módulo de Citas.
 */
@RestController // Combina @Controller y @ResponseBody (indica que retorna datos, no vistas)
@RequestMapping("/api/citas") // Define el URL base para todos los métodos de este controlador
@CrossOrigin(origins = "http://localhost:5173") // Permite solicitudes desde el puerto 5173
public class CitaController {

    // 1. INYECCIÓN DEL SERVICIO
    // Spring se encarga de crear e inyectar una instancia de CitaService.
    private final CitaService citaService;

    @Autowired
    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    // 2. ENDPOINT PARA CREAR/AGENDAR UNA CITA (POST)
    // URL: POST /api/citas
    @PostMapping
    public ResponseEntity<Cita> agendarCita(@RequestBody Cita nuevaCita) {
        // Llama al método de la capa Service
        Cita citaAgendada = citaService.agendarCita(nuevaCita);
        
        // Retorna la cita creada y el código de estado 201 (CREATED)
        return new ResponseEntity<>(citaAgendada, HttpStatus.CREATED); 
    }

    // 3. ENDPOINT PARA LISTAR TODAS LAS CITAS (GET)
    // URL: GET /api/citas
    @GetMapping
    public ResponseEntity<List<Cita>> obtenerTodas() {
        // Llama al método de la capa Service
        List<Cita> citas = citaService.listarTodasLasCitas();
        
        // Retorna la lista de citas y el código de estado 200 (OK)
        return ResponseEntity.ok(citas); 
    }
    
    // NOTA: Para cumplir el CRUD, se deben añadir los métodos PUT/PATCH y DELETE.
    
    // 4. ENDPOINT PARA CANCELAR CITA POR ID (DELETE)
    // URL: DELETE /api/citas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarCita(@PathVariable Integer id) {
        citaService.cancelarCita(id);
        // Retorna el código de estado 204 (NO CONTENT)
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
    }
}