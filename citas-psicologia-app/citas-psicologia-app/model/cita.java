package citas_psicologia_app.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate; // Necesario para mapear DATE [cite: 103]
import java.time.LocalTime; // Necesario para mapear TIME [cite: 104]

/**
 * Clase que mapea la tabla Citas, implementando las relaciones N:1 con Pacientes, Psicologos y Servicios.
 */
@Entity
@Table(name = "Citas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Cita")
    private Integer idCita;

    @Column(name = "Fecha", nullable = false)
    private LocalDate fecha; // Mapea al tipo DATE [cite: 103]

    @Column(name = "Hora", nullable = false)
    private LocalTime hora; // Mapea al tipo TIME [cite: 104]

    @Column(name = "Estado", length = 20)
    private String estado; // Por defecto 'Pendiente' en la BD [cite: 154]

    // --- Mapeo de Llaves Foráneas (Foreign Keys) ---

    // FK: ID_Paciente [cite: 106]
    @ManyToOne(fetch = FetchType.LAZY) // Muchas Citas (Many) tienen un solo Paciente (One)
    @JoinColumn(name = "ID_Paciente", referencedColumnName = "ID_Paciente") // Nombre de la columna FK en la tabla Citas
    private Paciente paciente; 

    // FK: ID_Psicologo [cite: 107]
    @ManyToOne(fetch = FetchType.LAZY) // Muchas Citas (Many) tienen un solo Psicólogo (One)
    @JoinColumn(name = "ID_Psicologo", referencedColumnName = "ID_Psicologo")
    private Psicologo psicologo; 

    // FK: ID_Servicio [cite: 108]
    @ManyToOne(fetch = FetchType.LAZY) // Muchas Citas (Many) tienen un solo Servicio (One)
    @JoinColumn(name = "ID_Servicio", referencedColumnName = "ID_Servicio")
    private Servicio servicio; 
}