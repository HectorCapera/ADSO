package citas_psicologia_app.repository;

import citas_psicologia_app.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz Repository para la entidad Cita.
 * Extiende de JpaRepository para heredar las operaciones CRUD básicas.
 */
@Repository // Anotación opcional, pero buena práctica para la legibilidad
public interface CitaRepository extends JpaRepository<Cita, Integer> {
    
    // Spring Data JPA provee automáticamente todos los métodos CRUD básicos
    // (findAll, findById, save, delete, etc.)
    
    // Aquí se podrían añadir métodos personalizados si fueran necesarios,
    // por ejemplo: List<Cita> findByPsicologoId(Integer id);
}