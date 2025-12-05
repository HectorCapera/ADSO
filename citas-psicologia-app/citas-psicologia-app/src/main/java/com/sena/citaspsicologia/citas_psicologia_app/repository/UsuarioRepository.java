package com.sena.citaspsicologia.citas_psicologia_app.repository;

import com.sena.citaspsicologia.citas_psicologia_app.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método mágico de JPA para buscar por email
    Usuario findByEmail(String email);
}