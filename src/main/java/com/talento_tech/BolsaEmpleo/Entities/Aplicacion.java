package com.talento_tech.BolsaEmpleo.Entities;

public class Aplicacion {
    private Long id;
    private Long idOfertaEmpleo;
    private Long idEmpleado;
    private String estado;
    private String comentario;

    public Aplicacion(Long idOfertaEmpleo, Long idEmpleado, String estado, String comentario) {
        this.idOfertaEmpleo = idOfertaEmpleo;
        this.idEmpleado = idEmpleado;
        this.estado = estado;
        this.comentario = comentario;
    }

    public Aplicacion() {
        idEmpleado = 0L;
        idOfertaEmpleo = 0L;
        estado = "Aplicado";
        comentario = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOfertaEmpleo() {
        return idOfertaEmpleo;
    }

    public void setIdOfertaEmpleo(Long idOfertaEmpleo) {
        this.idOfertaEmpleo = idOfertaEmpleo;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    
}
