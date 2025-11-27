package com.sena.citaspsicologia.citas_psicologia_app.dto;

public class CitaRequestDTO {

    private String fecha;
    private String hora;
    private String estado;
    private Long idPaciente;
    private Long idPsicologo;
    private Long idServicio;

    public CitaRequestDTO() {
    }

    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Long getIdPsicologo() {
        return idPsicologo;
    }
    public void setIdPsicologo(Long idPsicologo) {
        this.idPsicologo = idPsicologo;
    }

    public Long getIdServicio() {
        return idServicio;
    }
    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }
}
