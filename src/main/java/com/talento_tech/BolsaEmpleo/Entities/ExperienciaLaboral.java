package com.talento_tech.BolsaEmpleo.Entities;

import java.sql.Date;

public class ExperienciaLaboral {
    private long id;
    private String nombre_empresa;
    private String pais;
    private String modalidad;
    private String cargo;
    private Date fecha_inicio;
    private Date fecha_fin;
    private boolean trabaja_actualmente;
    private String jefe;
    private String telefono;
    private String funcion;
    private String detalles;
    private long id_empleado;

    public ExperienciaLaboral(long id, String nombre_empresa, String pais, String modalidad, String cargo, Date fecha_inicio, boolean trabaja_actualmente, String jefe, String telefono, long id_empleado) {
        this.id = id;
        this.nombre_empresa = nombre_empresa;
        this.pais = pais;
        this.modalidad = modalidad;
        this.cargo = cargo;
        this.fecha_inicio = fecha_inicio;
        this.trabaja_actualmente = trabaja_actualmente;
        this.jefe = jefe;
        this.telefono = telefono;
        this.id_empleado = id_empleado;
    }

    public long getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(long id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getJefe() {
        return jefe;
    }

    public void setJefe(String jefe) {
        this.jefe = jefe;
    }

    public boolean isTrabaja_actualmente() {
        return trabaja_actualmente;
    }

    public void setTrabaja_actualmente(boolean trabaja_actualmente) {
        this.trabaja_actualmente = trabaja_actualmente;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
