package citas_psicologia_app.service;

import citas_psicologia_app.model.Cita;
import citas_psicologia_app.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Clase que implementa la lógica de negocio para la gestión de citas.
 */
@Service // Indica a Spring que esta clase es un servicio de negocio
public class CitaService {

    // 1. INYECCIÓN DEL REPOSITORIO
    // Con esta línea, Spring inicializa el objeto CitaRepository
    // y lo deja disponible para usar sus métodos CRUD.
    private final CitaRepository citaRepository;

    @Autowired // Indica inyección por constructor
    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    // 2. LÓGICA DE NEGOCIO - Implementación de Operaciones

    // Método 1: Guardar una nueva cita (la lógica de agendar)
    public Cita agendarCita(Cita nuevaCita) {
        // **Lógica de Negocio:**
        // Aquí podrías añadir validaciones antes de guardar:
        // - if (isPsicologoDisponible(nuevaCita.getPsicologo(), nuevaCita.getFecha(), nuevaCita.getHora()))
        // - if (nuevaCita.getPaciente() == null) throw new RuntimeException("Paciente obligatorio");
        
        // Asignar estado inicial si es necesario (aunque ya lo hace la BD)
        if (nuevaCita.getEstado() == null || nuevaCita.getEstado().isEmpty()) {
            nuevaCita.setEstado("Pendiente");
        }
        
        // Ejecuta el método save() heredado del JpaRepository.
        return citaRepository.save(nuevaCita); 
    }

    // Método 2: Listar todas las citas
    public List<Cita> listarTodasLasCitas() {
        return citaRepository.findAll(); // Usa el método findAll() del JpaRepository.
    }

    // Método 3: Buscar cita por ID
    public Optional<Cita> buscarCitaPorId(Integer id) {
        return citaRepository.findById(id);
    }
    
    // Método 4: Cancelar cita
    public void cancelarCita(Integer id) {
        // En una aplicación real, actualizarías el estado a "Cancelada", no la borrarías.
        // citaRepository.updateEstado(id, "Cancelada");
        
        // Pero para el CRUD básico:
        citaRepository.deleteById(id);
    }
}