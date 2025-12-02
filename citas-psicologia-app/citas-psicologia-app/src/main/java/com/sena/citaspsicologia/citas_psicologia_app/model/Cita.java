package com.sena.citaspsicologia.citas_psicologia_app.model;

import jakarta.persistence.*; // ¡IMPORTANTE! Spring Boot 3 usa Jakarta, no Javax
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Clase que mapea la tabla Citas.
 */
@Entity
@Table(name = "Citas")
@Data // Lombok genera Getters, Setters, toString, etc.
@NoArgsConstructor // Constructor vacío
@AllArgsConstructor // Constructor con argumentos
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Cita")
    private Long idCita; // Recomendable usar Long para IDs en JPA

    @Column(name = "Fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "Hora", nullable = false)
    private LocalTime hora;

    @Column(name = "Estado", length = 20)
    private String estado;

    // --- Relaciones ---

    @ManyToOne(fetch = FetchType.EAGER) // EAGER ayuda a cargar los datos completos al listar
    @JoinColumn(name = "ID_Paciente", referencedColumnName = "ID_Paciente")
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_Psicologo", referencedColumnName = "ID_Psicologo")
    private Psicologo psicologo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_Servicio", referencedColumnName = "ID_Servicio")
    private Servicio servicio;
}