package com.talento_tech.BolsaEmpleo.Entities;

import java.sql.Date;
import java.sql.Timestamp;

public class Usuario {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private Timestamp fechaRegistro;
    private Timestamp ultimaModificacion;
    private String identificacion;
    private tipoID tipoID;
    private Date fechaNacimiento;

    public Usuario() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public tipoID getTipoID() {
        return tipoID;
    }

    public void setTipoID(tipoID tipoID) {
        this.tipoID = tipoID;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }


    public String nacimiento(){
        String nacimiento = fechaNacimiento.getMonth() + "/" + fechaNacimiento.getDate() + "/" + fechaNacimiento.getYear();
        return nacimiento;
    }

    public Timestamp getUltimaModificacion() {
        return ultimaModificacion;
    }

    public void setUltimaModificacion(Timestamp ultimaModificacion) {
        this.ultimaModificacion = ultimaModificacion;    
    }
    
}
