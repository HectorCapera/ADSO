package com.sena.citaspsicologia.citas_psicologia_app.repository;

import com.sena.citaspsicologia.citas_psicologia_app.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Interfaz Repositorio para la entidad Cita.
 * Extiende de JpaRepository para heredar operaciones CRUD estándar (Save, FindAll, Delete, etc.)
 * sin necesidad de escribir SQL manual.
 */
@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    /**
     * Método personalizado (Query Method) para buscar citas por el ID del paciente.
     * Spring Data JPA genera automáticamente la consulta SQL basada en el nombre del método:
     * "SELECT * FROM citas WHERE paciente_id = ?"
     *
     * @param pacienteId El ID del usuario/paciente.
     * @return Lista de citas asociadas a ese paciente.
     */
    List<Cita> findByPacienteId(Long pacienteId);
}