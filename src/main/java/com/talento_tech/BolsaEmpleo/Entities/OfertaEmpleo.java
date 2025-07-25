package com.talento_tech.BolsaEmpleo.Entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

public class OfertaEmpleo {
    private long id;
    private long empleador_id;
    private long empresa_id;
    private long aplicacion_id;
    private String cargo;
    private String ubicacion;
    private String descripcion;
    private String nombreEmpresa;
    private String requisitos;
    private Double salario;
    private Timestamp fechaPublicacion;
    private Date fechaExpiracion;
    private String tipoContrato;
    private String modalidad;
    private String pais;
    private String ciudad;
    private String departamento;
    private String nivelEstudios;
    private String experienciaLaboral;
    private String idiomas;
    private String[] idiomasArray;

    private ArrayList<Empleado> postulados;

    public OfertaEmpleo() {

    }

    public OfertaEmpleo(long id, long empleador_id, long empresa_id, String cargo, String ubicacion, String descripcion, String nombreEmpresa, String requisitos, Double salario, Timestamp fechaPublicacion, Date fechaExpiracion, String tipoContrato, String modalidad, String pais, String ciudad, String departamento, String nivelEstudios, String experienciaLaboral) {
        this.id = id;
        this.empleador_id = empleador_id;
        this.empresa_id = empresa_id;
        this.cargo = cargo;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.nombreEmpresa = nombreEmpresa;
        this.requisitos = requisitos;
        this.salario = salario;
        this.fechaPublicacion = fechaPublicacion;
        this.fechaExpiracion = fechaExpiracion;
        this.tipoContrato = tipoContrato;
        this.modalidad = modalidad;
        this.pais = pais;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.nivelEstudios = nivelEstudios;
        this.experienciaLaboral = experienciaLaboral;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEmpleador_id() {
        return empleador_id;
    }

    public void setEmpleador_id(long empleador_id) {
        this.empleador_id = empleador_id;
    }

    public long getEmpresa_id() {
        return empresa_id;
    }

    public void setEmpresa_id(long empresa_id) {
        this.empresa_id = empresa_id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Timestamp getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Timestamp fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getNivelEstudios() {
        return nivelEstudios;
    }

    public void setNivelEstudios(String nivelEstudios) {
        this.nivelEstudios = nivelEstudios;
    }

    public String getExperienciaLaboral() {
        return experienciaLaboral;
    }

    public void setExperienciaLaboral(String experienciaLaboral) {
        this.experienciaLaboral = experienciaLaboral;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public String[] getIdiomasArray(){
        idiomasArray = idiomas.split(",");
        return idiomasArray;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public ArrayList<Empleado> getPostulados() {
        return postulados;
    }

    public void setPostulados(ArrayList<Empleado> postulados) {
        this.postulados = postulados;
    }

    public long getAplicacion_id(){
        return aplicacion_id;
    }

    public void setAplicacion_id(long aplicacion_id) {
        this.aplicacion_id = aplicacion_id;
    }
}
