package com.talento_tech.BolsaEmpleo.Entities;

import java.util.ArrayList;

public class Empresa {
    private long id;
    private String nombre;
    private String descripcion;
    private String logo;
    private String telefono;
    private String direccion;
    private String email;
    private String web;
    private String tipoEmpresa;
    private String NIT;
    private String representante;
    private String identificacionRepresentante;
    private String tipoIdentificacionRepresentante;
    private ArrayList<OfertaEmpleo> ofertas;
    private ArrayList<Empleado> empleados;
    private ArrayList<OfertaEmpleo> ofertasDesactivadas;
    private Long usuaro_id;

    public Empresa(String nombre, String descripcion, String logo, String telefono, String direccion, String email, String web, String tipoEmpresa, String NIT, String representante, String identificacionRepresentante, String tipoIdentificacionRepresentante) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.logo = logo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.web = web;
        this.tipoEmpresa = tipoEmpresa;
        this.NIT = NIT;
        this.representante = representante;
        this.identificacionRepresentante = identificacionRepresentante;
        this.tipoIdentificacionRepresentante = tipoIdentificacionRepresentante;
    }

    public Empresa() {}


    public long getId() {
        return id;
    }

    public void setId(long empresaId) {
        this.id = empresaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getIdentificacionRepresentante() {
        return identificacionRepresentante;
    }

    public void setIdentificacionRepresentante(String identificacionRepresentante) {
        this.identificacionRepresentante = identificacionRepresentante;
    }

    public String getTipoIdentificacionRepresentante() {
        return tipoIdentificacionRepresentante;
    }

    public void setTipoIdentificacionRepresentante(String tipoIdentificacionRepresentante) {
        this.tipoIdentificacionRepresentante = tipoIdentificacionRepresentante;
    }

    public ArrayList<OfertaEmpleo> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<OfertaEmpleo> ofertas) {
        this.ofertas = ofertas;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }

    public ArrayList<OfertaEmpleo> getOfertasDesactivadas() {
        return ofertasDesactivadas;
    }

    public void setOfertasDesactivadas(ArrayList<OfertaEmpleo> ofertasDesactivadas) {
        this.ofertasDesactivadas = ofertasDesactivadas;
    }

    public Long getUsuaro_id(){
        return usuaro_id;
    }

    public void setUsuaro_id(Long usuaro_id){
        this.usuaro_id = usuaro_id;
    }
}
