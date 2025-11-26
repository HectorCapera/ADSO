package citas_psicologia_app.model; 

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Clase que mapea la tabla Psicologos.
 */
@Entity
@Table(name = "Psicologos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Psicologo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Psicologo")
    private Integer idPsicologo;

    @Column(name = "Nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "Especialidad", length = 100)
    private String especialidad;

    @Column(name = "Telefono", length = 15)
    private String telefono;

    @Column(name = "Correo", length = 100, unique = true) // Mapea la restricción UNIQUE 
    private String correo;
}