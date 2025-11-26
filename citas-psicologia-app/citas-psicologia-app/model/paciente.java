package citas_psicologia_app.model; 

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Clase que mapea la tabla Pacientes en la base de datos.
 * Contiene la información de los usuarios que solicitan citas.
 */
@Entity
@Table(name = "Pacientes") // Nombre exacto de la tabla en MySQL
@Data // Genera Getters, Setters, toString, etc.
@NoArgsConstructor // Constructor sin argumentos (necesario para JPA)
@AllArgsConstructor // Constructor con todos los argumentos
public class Paciente {

    @Id // Marca el campo como Llave Primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    @Column(name = "ID_Paciente")
    private Integer idPaciente;

    @Column(name = "Nombre", length = 50, nullable = false) // NOT NULL
    private String nombre;

    @Column(name = "Apellido", length = 50, nullable = false) // NOT NULL
    private String apellido;

    @Column(name = "Correo", length = 100, unique = true) // UNIQUE
    private String correo;

    @Column(name = "Telefono", length = 15)
    private String telefono;

    @Column(name = "Direccion", length = 100)
    private String direccion;
}