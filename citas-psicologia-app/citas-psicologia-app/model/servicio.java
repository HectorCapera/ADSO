package citas_psicologia_app.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal; // Necesario para mapear el tipo DECIMAL 

/**
 * Clase que mapea la tabla Servicios.
 */
@Entity
@Table(name = "Servicios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Servicio")
    private Integer idServicio;

    @Column(name = "Nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "Descripcion", columnDefinition = "TEXT") // Mapeo para el tipo TEXT [cite: 99]
    private String descripcion;

    // Usamos BigDecimal para garantizar precisión con DECIMAL(10,2) 
    @Column(name = "Precio", precision = 10, scale = 2, nullable = false)
    private BigDecimal precio; 
}
