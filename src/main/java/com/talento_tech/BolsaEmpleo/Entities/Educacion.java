package com.talento_tech.BolsaEmpleo.Entities;

import java.sql.Date;

public class Educacion {
    private long id;
    private String institucion;
    private String tipo_institucion;
    private String titulo;
    private Date fecha_inicio;
    private Date fecha_fin;
    private boolean cursando_actualmente;
    private String pais;
    private String departamento;
    private String ciudad;
    private long empleado_id;

    public Educacion(long id, String institucion, String tipo_institucion, String titulo, Date fecha_inicio, boolean cursando_actualmente, String pais, String departamento, String ciudad, long empleado_id) {
        this.id = id;
        this.institucion = institucion;
        this.tipo_institucion = tipo_institucion;
        this.titulo = titulo;
        this.fecha_inicio = fecha_inicio;
        this.cursando_actualmente = cursando_actualmente;
        this.pais = pais;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.empleado_id = empleado_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getTipo_institucion() {
        return tipo_institucion;
    }

    public void setTipo_institucion(String tipo_institucion) {
        this.tipo_institucion = tipo_institucion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public boolean getCursando_actualmente() {
        return cursando_actualmente;
    }

    public void setCursando_actualmente(boolean cursando_actualmente) {
        this.cursando_actualmente = cursando_actualmente;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public long getEmpleado_id() {
        return empleado_id;
    }

    public void setEmpleado_id(long empleado_id) {
        this.empleado_id = empleado_id;
    }


}
