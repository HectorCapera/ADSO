package com.sena.citaspsicologia.citas_psicologia_app.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "Psicologos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Psicologo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Psicologo")
    private Long idPsicologo;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Especialidad")
    private String especialidad;

    @Column(name = "Telefono")
    private String telefono;

    @Column(name = "Correo")
    private String correo;
}