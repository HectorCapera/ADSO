package com.sena.citaspsicologia.citas_psicologia_app.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Clase Entidad que representa la tabla 'citas' en la base de datos.
 * Utiliza JPA (Java Persistence API) para el mapeo objeto-relacional (ORM).
 */
@Entity
@Table(name = "citas") // Define el nombre exacto de la tabla en MySQL
public class Cita {

    /**
     * Identificador único de la cita (Llave Primaria).
     * @GeneratedValue indica que el ID se autoincrementa automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // Campo obligatorio
    private LocalDate fecha; // Almacena solo la fecha (AAAA-MM-DD)

    @Column(nullable = false)
    private LocalTime hora;  // Almacena solo la hora (HH:MM:SS)

    @Column(nullable = false, length = 500) // Se amplía el límite de caracteres para el motivo
    private String motivo;

    /**
     * Estado actual de la cita (ej. Pendiente, Confirmada, Cancelada).
     * Se gestionará como texto simple para este alcance del proyecto.
     */
    private String estado;

    /**
     * ID del paciente asociado a la cita.
     * Nota: Para mantener el diseño desacoplado en esta fase, guardamos solo el ID
     * del usuario (Foreign Key lógica) en lugar de la relación completa @ManyToOne.
     */
    @Column(name = "paciente_id")
    private Long pacienteId;

    // ==========================================
    // CONSTRUCTORES
    // ==========================================

    // Constructor vacío requerido por JPA/Hibernate
    public Cita() {}

    // Constructor para crear instancias con datos iniciales
    public Cita(LocalDate fecha, LocalTime hora, String motivo, String estado, Long pacienteId) {
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.estado = estado;
        this.pacienteId = pacienteId;
    }

    // ==========================================
    // GETTERS Y SETTERS
    // Permiten el acceso y modificación encapsulada de los atributos
    // ==========================================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }
}