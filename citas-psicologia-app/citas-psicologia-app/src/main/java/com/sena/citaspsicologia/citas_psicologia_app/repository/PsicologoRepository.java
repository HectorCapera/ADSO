package com.sena.citaspsicologia.citas_psicologia_app.repository;

import com.sena.citaspsicologia.citas_psicologia_app.model.Psicologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PsicologoRepository extends JpaRepository<Psicologo, Long> {
}