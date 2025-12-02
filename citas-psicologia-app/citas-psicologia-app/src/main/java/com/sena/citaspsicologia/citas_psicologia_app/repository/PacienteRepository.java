package com.sena.citaspsicologia.citas_psicologia_app.repository;

import com.sena.citaspsicologia.citas_psicologia_app.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}